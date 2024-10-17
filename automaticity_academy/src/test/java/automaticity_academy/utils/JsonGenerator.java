package automaticity_academy.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonGenerator {

    public static Map<String, Object> generateJson(String[][] userData) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String[] data : userData) {
            map.put(data[0], data[1]);
        }
        return map;
    }

}
