package support;

import java.text.SimpleDateFormat;
import java.util.*;

public class Converter {
    public static List<String> objectToArrayList(Map<String, Object> target, String key) {
        return (ArrayList<String>)target.get(key);
    }

    public static Map<String, String> objectToMap(Map<String, Object> target, String key) {
        return (Map<String, String>)target.get(key);
    }

    public static String obtainCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
        Date currentDate = new Date ();
        return simpleDateFormat.format(currentDate);
    }

    public static String splitDisplayName(String name) {
        if(name.contains("(") && name.contains(")")) {
            return name.split("\\(")[1].split("\\)")[0];
        }
        return name;
    }

}
