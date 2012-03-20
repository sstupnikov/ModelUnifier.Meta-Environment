package unifier.util;

import java.util.*;
import javax.swing.*;
import java.sql.*;
import db.*;
import meta.*;
import java.util.logging.Logger;


public class RepositoryCleaner {

    static DB db = null;
    private static Logger logger = Logger.getLogger("unifier.RepositoryCleaner");

    public static void cleanup(DB adb) throws SQLException {
        Set s;
        Iterator i;

        db = adb;

        s = db.get_actualParameter();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_parameter();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_set();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_function();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_script();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_state();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_transition();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_timeConstant();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_conceptRel();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_associationMetaclass();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_attribute();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

        s = db.get_adt();
        i = s.iterator();
        while (i.hasNext()) {
            delete((Def) i.next());
        }

    }

    public static void delete(Def def) throws SQLException {
        Set s;
        Iterator i;

        if (def == null)
            return;
        else {
            logger.info("Start Deleting " + "\n");
        }

        if (db == null)
            db = (DB) def._db();

        // Schema
        if (def.is_schema()) {
            s = ((SchemaDef) def).get_modules();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = db.get_relevance(new Query(new Eq(RelevanceDef._schema, def)));
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = db.get_path(new Query(new Eq(PathDef._schema, def)));
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = db.get_mpathsRelev(new Query(new Eq(MPathsRelevDef._schema, def)));
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = db.get_spathsRelev(new Query(new Eq(SPathsRelevDef._schema, def)));
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
        }
        // Module
        else if (def.is_module()) {
            s = ((ModuleDef) def).get_containedTypes();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = ((ModuleDef) def).get_containedClasses();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = ((ModuleDef) def).get_containedFunctions();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = ((ModuleDef) def).get_containedFrames();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            Def d = ((ModuleDef) def).get_diagram();
            if (d != null) {
                delete(d);
            }
        }
        // ADT
        else if (def.is_adt()) {
            s = ((ADTDef) def).get_attributes();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
            s = ((ADTDef) def).get_invariants();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
        }
        // Attribute
        else if (def.is_attribute()) {
            Def d = ((AttributeDef) def).get_type();
            if (d != null) {
                if (d.is_function() || d.is_set()) {
                    delete(d);
                }
            }
            s = ((AttributeDef) def).get_classes();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
        }
        // Class
        if (def.is_class()) {
            delete(((ClassDef) def).get_instanceType());
        }
        // Frame
        if (def.is_frame()) {
            s = ((FrameDef) def).get_concretizations();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }

            s = ((FrameDef) def).get_actualParameters();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }

            s = ((FrameDef) def).get_parameters();
            i = s.iterator();
            while (i.hasNext()) {
                delete((Def) i.next());
            }
        }
        // Actual Parameter
        if (def.is_actualParameter()) {
            delete(((ActualParameterDef) def).get_value());
        }

        // Script
        if (def.is_script()) {
            s = ((ScriptDef) def).get_states();
            i = s.iterator();
            while (i.hasNext())
                delete((Def) i.next());

            s = ((ScriptDef) def).get_gates();
            i = s.iterator();
            while (i.hasNext())
                delete((Def) i.next());

            s = ((ScriptDef) def).get_transitions();
            i = s.iterator();
            while (i.hasNext())
                delete((Def) i.next());
        }
        // State
        if (def.is_state()) {
            delete(((StateDef) def).get_initialValue());
            delete(((StateDef) def).get_tokenType());
        }
        // Transition
        if (def.is_transition()) {
            s = ((TransitionDef) def).get_bind_from();
            i = s.iterator();
            while (i.hasNext())
                delete((Def) i.next());

            s = ((TransitionDef) def).get_bind_to();
            i = s.iterator();
            while (i.hasNext())
                delete((Def) i.next());

            delete(((TransitionDef) def).get_action());
        }

        // AbstrModelReg
        if (def.is_abstrModelReg()) {
            AbstrModelRegDef model = (AbstrModelRegDef)def;

            for (Object fc: model.get_abstrSyntax()){
                ((Def)fc).delete();
            }

            if(model.get_refSchema() != null) delete(model.get_refSchema());
        }

        // ModelReg
        if (def.is_modelReg()) {
            ModelRegDef model = (ModelRegDef)def;

            for (Object sample: model.get_samples())  delete((Def)sample);
            for (Object sim: model.get_similarities())  delete((Def)sim);
            for (Object fc: model.get_transTargetModel()) delete((Def)fc);
            for (Object fc: model.get_transToAmn()) delete((Def)fc);
        }

        // SampleReg
        if (def.is_sampleReg()) {
            SampleRegDef sample = (SampleRegDef)def;

            for (Object fc: sample.get_amnSpec())  delete((Def)fc);
            for (Object fc: sample.get_targetAmnSpec())  delete((Def)fc);
            for (Object fc: sample.get_recAmnSpec())  delete((Def)fc);
            for (Object fc: sample.get_recTargetAmnSpec())  delete((Def)fc);
        }


        // Other
        if (!def.isPredefined()) {
            logger.info("Deleting " + def.toString() + "\n");
            def.delete();
            logger.info("After Deleting " + def.toString() + "\n");
        }

    }

}
