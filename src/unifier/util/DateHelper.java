package unifier.util;

import java.util.Date;
import java.util.Calendar;
import javax.swing.text.MaskFormatter;
import java.util.GregorianCalendar;

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
public class DateHelper {

    // create MaskFormatter accordong to mask string
    public static MaskFormatter createMaskFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
        }
        return formatter;
    }

    // converts string of format dd/mm/yyyy into Date
    public static Date convertFormattedStringIntoDate(String s) throws
            IncorrectDateException {
        int year;
        int month;
        int day;
        GregorianCalendar calendar = new GregorianCalendar();

        if (s.length() != 10) {
            System.out.println(s.length());
            throw new IncorrectDateException();
        }

        year = (new Integer(s.substring(6, 10))).intValue();
        if (year < 1900 || year > 2100) {
            throw new IncorrectDateException();
        }

        month = (new Integer(s.substring(3, 5))).intValue();
        if (month < 1 || month > 12) {
            throw new IncorrectDateException();
        }

        day = (new Integer(s.substring(0, 2))).intValue();
        if (day < 1 || day > 31) {
            throw new IncorrectDateException();
        }

        calendar.set(year, month - 1, day);

        return calendar.getTime();
    }

    // converts Date into string of format dd/mm/yyyy
    public static String convertDateIntoFormattedText(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        String result = "";

        calendar.setTime(date);

        if (calendar.get(Calendar.DAY_OF_MONTH) >= 10) {
            result += new Integer(calendar.get(Calendar.DAY_OF_MONTH)).toString();
        } else {
            result += "0" +
                    new Integer(calendar.get(Calendar.DAY_OF_MONTH)).toString();
        }

        result += "/";

        if (calendar.get(Calendar.MONTH) >= 9) {
            result += new Integer(calendar.get(Calendar.DAY_OF_MONTH) + 1).
                    toString();
        } else {
            result += "0" +
                    new Integer(calendar.get(Calendar.MONTH) + 1).toString();
        }

        result += "/";

        result += new Integer(calendar.get(Calendar.YEAR)).toString();

        return result;
    }

}

class IncorrectDateException extends Exception {
    public String toString() {
        return "IncorrectDateException.";
      }
}

