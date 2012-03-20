package unifier.similarity;

import java.util.Set;
import java.util.HashSet;

/**
 * <p>Title: Unifier</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: IPI RAS</p>
 *
 * @author Sergey A. Stupnikov
 * @version 1.0
 */
public class TestSimilarityCreator {

    public static Set<Similarity> createSimilarities(){
            // Create sims
            Set<Similarity> sims = new HashSet<Similarity>();

            sims.add(new Similarity(new Element("OwlID"), new Element("Synthesis-Id")));

            sims.add(new Similarity(new Element("Ontology"), new Element("Module-Def")));

            sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Module-Def", "Type-Specification", -1)));
            sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Module-Def", "Class-Declarator", -1)));

            // assoc test 1
            sims.add(new Similarity(new Element("Ontology"), new Element("Type-Section")));
            sims.add(new Similarity(new Element("Directive"), new Element("Type-Specification")));
            sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("Type-Section", "Type-Specification", -1)));
            //sims.add(new Similarity(new Element("NamespaceDef"), new Element("Type-Specification")));
            //sims.add(new Similarity(new Element("ClassAxiom"), new Element("Type-Specification")));

            // assoc test 2
            sims.add(new Similarity(new Element("Ontology"), new Element("IRS-Section")));
            sims.add(new Similarity(new Element("Directive"), new Element("Class-Declarator")));
            sims.add(new Similarity(new Association("Ontology", "Directive", -1), new Association("IRS-Section", "Class-Declarator", -1)));
            //sims.add(new Similarity(new Element("NamespaceDef"), new Element("Type-Specification")));
            //sims.add(new Similarity(new Element("ClassAxiom"), new Element("Type-Specification")));




            sims.add(new Similarity(new Element("ClassAxiom"), new Element("Abstract-Type")));

            sims.add(new Similarity(new Association("ClassAxiom", "Description", -1), new Association("Abstract-Type", "Synthesis-Id", -1)));
            sims.add(new Similarity(new Association("ClassAxiom", "Description", -1), new Association("Abstract-Type", "Attribute-Specification", -1)));

            sims.add(new Similarity(new Element("Restriction"), new Element("Attribute-Specification")));
            sims.add(new Similarity(new Element("Description"), new Element("Attribute-Specification")));
            sims.add(new Similarity(new Element("ObjectPropertyAxiom"), new Element("Attribute-Specification")));
            sims.add(new Similarity(new Association("ObjectPropertyAxiom", "ObjectPropertyRange", -1), new Association("Attribute-Specification", "Attribute-Type", 1)));
            sims.add(new Similarity(new Association("Restriction", "RestrictionComponent", -1), new Association("Attribute-Specification", "Attribute-Metaslot", 1)));

            sims.add(new Similarity(new Element("Cardinality"), new Element("Metaframe-Slot")));
            sims.add(new Similarity(new Association("Cardinality", "Unsigned-Int", 1), new Association("Metaframe-Slot", "Slot-Value", -1)));

            sims.add(new Similarity(new Element("ObjectPropertyAxiom"), new Element("Association-Metaclass")));
            sims.add(new Similarity(new Association("ObjectPropertyAxiom", "SuperProperty", -1), new Association("Association-Metaclass", "Synthesis-Id", -1)));


            return sims;
    }
}
