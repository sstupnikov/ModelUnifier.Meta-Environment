package unifier.asfsdf;

import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;

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
public class SdfDefinition {

    private Map<String, Module> modules = new HashMap<String, Module>();

    public boolean moduleExists(String moduleName){
        return modules.containsKey(moduleName);
    }

    public Module getModule(String moduleName) throws SdfDefinitionException {
        if(modules.containsKey(moduleName)) {
            return modules.get(moduleName);
        } else {
            throw new SdfDefinitionException("SDF module " + moduleName + " does not exists.");
        }
    }

    public SdfDefinition addModule(Module module) throws SdfDefinitionException {
        if(module.getName().trim().compareTo("") == 0){
            throw new SdfDefinitionException("Module with empty name has not been added to SDF definition.");
        } else {
            modules.put(module.getName(), module);
        }

        return this;
    }
}
