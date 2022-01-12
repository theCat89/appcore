package ru.horologer.base;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static ru.horologer.core.AppConfiguration.getLogger;

/**
 * Class for converting Objects in Strings and back encapsulate null-safety (TODO make this state true)
 */
public interface StringConverter {

    static String toString(int number) {
        return String.valueOf(number);
    }

    static String toString(Long number) {
        if (number == null) return "";
        return number.toString().trim();
    }

    static String toString(Date date, String format) {
        if (date == null) return "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    static String toString(LocalDate date, String format) {
        if (date == null) return "";
        return DateTimeFormatter.ofPattern(format).format(date);
    }

    static String toString(Integer number) {
        return String.valueOf(number);
    }

    static String toString(Short number) {
        return String.valueOf(number);
    }

    static String toString(Object o) {
        if (o == null) return "";
        if (o instanceof Long) return toString((Long) o);
        if (o instanceof Integer) return toString((Integer) o);
        if (o instanceof Date) return toString((Date) o, "dd.MM.yyyy");
        if (o instanceof LocalDate) return toString((LocalDate) o, "dd.MM.yyyy");
        if (o instanceof String) return (String) o;
        if (o instanceof Boolean) return ((Boolean) o).toString();
        throw new RuntimeException("Unknown type to parse: " + o.getClass());
    }

    static Object toObject(String str) {
        if (str == null) return null;
        /**
         * Integer
         * Long
         * Date
         * Boolean
         * String - default
         * */
        //-2147483648 to 2147483647
        // Если содержит цифры и точку - double, если содержит цифры - int или long (от max int)
        // если что-то кроме String, кроме вариантов с Date (типы DatePattern надо определить)
        if(str.matches("\\d+") || str.matches("-\\d+")) {
            long vCandidate;
            if ((vCandidate = Long.parseLong(str)) <= 2147483647L && vCandidate >= -2147483648)
                return (int) vCandidate;
            return vCandidate;
        }
        if(str.matches("\\d{2}.\\d{2}.\\d{4}"))
            return toDate(str, "dd.MM.yyyy");
        if(str.matches("\\d{2}.\\d{2}.\\d{4}\\s\\d{2}:\\d{2}:\\d{2}"))
            return toDate(str, "dd.MM.yyyy hh:mm:ss");
//        if(str.matches("d*")) return toDate(str);//TODO insert datePattern
        if(str.equalsIgnoreCase("true")) return true;
        if(str.equalsIgnoreCase("false")) return false;
        return str;
    }

    static boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.equalsIgnoreCase("null");
    }

    static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    static boolean isEmpty(Object o) {
        if (o == null) return true;
        return o instanceof String && isEmpty((String) o);
    }

    static Short toShort(String s) {
        if (isEmpty(s))
            return null;
        return Short.valueOf(s);
    }

    static Integer toInteger(String s) {
        if (isEmpty(s))
            return null;
        return Integer.valueOf(s);
    }

    static Long toLong(String s) {
        if (isEmpty(s))
            return null;
        return Long.valueOf(s.trim());
    }

    static Date toDate(String s) {
        return toDate(s, "dd.MM.yyyy");
    }

    static Date toDate(String s, String format) {
        if (!isEmpty(s)) {
            try {
                return new SimpleDateFormat(format).parse(s);
            } catch (ParseException e) {
                getLogger().log(e);
            }
        }
        return null;
    }

    static LocalDate toLocalDate(String s, String format) {
        if (isEmpty(s) || isEmpty(format))
            return null;
        return LocalDate.parse(s, DateTimeFormatter.ofPattern(format));
    }


    static Timestamp toTimestamp(String s, String format) {
        if (!isEmpty(s) && !isEmpty(format)) {
            try {
                return Timestamp.from(new SimpleDateFormat(format).parse(s).toInstant());
            } catch (ParseException e) {
                getLogger().log(e);
            }
        }
        return null;
    }
}
