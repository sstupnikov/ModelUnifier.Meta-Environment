package unifier.similarity;

import meta.ModuleDef;
import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;
import meta.TypeDef;
import java.sql.SQLException;
import meta.ADTDef;
import meta.RelevanceDef;
import meta.ElementDef;
import meta.AttributeDef;
import meta.FrameDef;
import meta.CollectionDef;

/**
 * <p>Title: Unifier</p>
 *
 * <p>Description: Creates set of similarities on the base of relevances between
 * source and target MuduleDefs. Relevances are extracted from types and their
 * attributes.
 * </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: IPI RAS</p>
 *
 * @author Sergey A. Stupnikov
 * @version 1.0
 */
public class SimilarityCreator {

    public SimilarityCreator(ModuleDef source, ModuleDef target){
        this.source = source;
        this.target = target;
    }

    private ModuleDef source;
    private ModuleDef target;

    private Logger logger = Logger.getLogger("unifier.SimilarityCreator");

    private Set<Similarity> sims = new HashSet<Similarity>();





    public Set<Similarity> createSimilarities() throws SQLException {
        sims.clear();

        if(source == null || target == null){
            logger.info("Source or target module is null.");
            return sims;
        }

        for(Object t: source.get_containedTypes()){
            if(!(t instanceof ADTDef)) continue;
            createAdtSimilarities((ADTDef)t);
        }

        return sims;
    }

    private void createAdtSimilarities(ADTDef adt) throws SQLException {
        for(Object r: adt.get_relevances()){
            RelevanceDef relev = (RelevanceDef)r;
            ElementDef elm = null;
            Similarity sim = null;

            elm = relev.get_relevant();

            // Rejected relevance
            if(relev.get_rejected()) continue;
            // Inconsistent relevance
            if(relev.get_relevantFor() != adt){
                logger.info("Relevance self-reference inconsistency in type " + adt.get_name());
                continue;
            }
            if(!elm.is_adt()){
                logger.info("Relevance relevant is not ADT in type " + adt.get_name());
                continue;
            }
            // Relevance with the other model
            if(!typeIsInModule((ADTDef)elm, target)){
                logger.info("Type " + elm.get_name() + " is not in module " + target.get_name());
                continue;
            }

            // Create similarity for relevance
            sim = new Similarity(new Element(adt.get_name()), new Element(elm.get_name()));
            sims.add(sim);

            // Create sims for attributes
            for(Object a: adt.get_attributes()){
                if(!(a instanceof AttributeDef)) continue;
                createAttributeSimilarities((AttributeDef)a);
            }
        }
    }

    private void createAttributeSimilarities(AttributeDef attr) throws  SQLException {
        int card = 0;

        if(attr.get_attributeOf() == null || attr.get_type() == null){
            logger.info("Attribute " + attr.get_name() + " has incomplete specification.");
            return;
        }

        if(!attr.isNull_cardinality()) card = attr.get_cardinality();

        for(Object r: attr.get_relevances()){
            RelevanceDef relev = (RelevanceDef)r;
            ElementDef elm = null;
            AttributeDef relAttr = null;
            int relAttrCard = 0;
            Similarity sim = null;
            Association of = null;
            Association to = null;
            TypeDef attrType = null, relAttrType = null;

            elm = relev.get_relevant();


            if(attr.get_type().is_set())
                attrType = ((CollectionDef)attr.get_type()).get_ofType();
            else
                attrType = attr.get_type();


            // Rejected relevance
            if(relev.get_rejected()) continue;
            // Inconsistent relevance
            if(relev.get_relevantFor() != attr){
                logger.info("Relevance self-reference inconsistency in attribute " + attr.get_name());
                continue;
            }
            if(!elm.is_attribute()){
                logger.info("Relevance relevant is not attribute in attribute " + attr.get_name());
                continue;
            }

            relAttr = (AttributeDef)elm;
            if(relAttr.get_type().is_set())
                relAttrType = ((CollectionDef)relAttr.get_type()).get_ofType();
            else
                relAttrType = relAttr.get_type();

            // Relevance with the other model
            if(!typeIsInModule(relAttrType, target)){
                logger.info("Type " + elm.get_name() + " is not in module " + target.get_name());
                continue;
            }

            // Relevant attribute analysis
            if(relAttr.get_attributeOf() == null || relAttr.get_type() == null){
                logger.info("Attribute " + relAttr.get_name() + " has incomplete specification.");
                continue;
            }
            if(!relAttr.isNull_cardinality()) relAttrCard = relAttr.get_cardinality();


            // Create similarity for relevance
            of = new Association(attr.get_attributeOf().get_name(), attrType.get_name(), card);
            to = new Association(relAttr.get_attributeOf().get_name(), relAttrType.get_name(), relAttrCard);

            sim = new Similarity(of, to);
            sims.add(sim);
        }
    }

    private boolean typeIsInModule(TypeDef type, ModuleDef module) throws SQLException {
        boolean result = false;

        logger.info("typeIsInModule, type " + type.get_name() + ", module " + module.get_name());

        if(type.get_typeInModule() == module)
            result = true;
        else
            for(Object obj: module.get_imports()){
                FrameDef frame = (FrameDef)obj;

                if(!result && frame.is_module())
                    result = typeIsInModule(type, (ModuleDef)frame);
            }

        return result;
    }
}
