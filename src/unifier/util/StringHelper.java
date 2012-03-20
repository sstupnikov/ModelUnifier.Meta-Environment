package unifier.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
public class StringHelper {

    public static String removeLastSymbol(String s){
        if(s == null) return s;

        String result = s;
        int length = result.length();

        if (length != 0) {
            result = result.substring(0, length-1);
        }

        return result;
    }

    public static boolean isNullEmptyOrBlank(String s){
        if (s == null || s.trim().compareTo("") == 0){
            return true;
        } else {
            return false;
        }
    }

    // add external quotation marks if they are not presented
    public static String toQuotation(String s){
        if(s == null) return s;

        if (s.charAt(0) == '\"' && s.charAt(s.length()-1) == '\"'){
            return s;
        } else {
            return "\"" + s + "\"";
        }
    }

    // Let path = "name1/name2/name3"
    // The method returns "name3"
    public static String getLastComponentOfPath(String path){
        if(isNullEmptyOrBlank(path)) return path;

        int length = path.length();
        for(int i = length - 1; i >= 0; i--){
            if(path.charAt(i) == '/'){
                return path.substring(i + 1, length);
            }
        }

        return path;
    }

    public static boolean isIdentifier(String s){
        boolean result = false;

        Pattern p = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9\u005F]+");
        Matcher m = p.matcher(s);
        result = m.matches();

        return result;
    }

}
