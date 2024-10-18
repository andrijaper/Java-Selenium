package automaticity_academy.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonGenerator {

    public static JSONObject generateJson(String[][] userData) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String[] data : userData) {
            map.put(data[0], data[1]);
        }
        return new JSONObject(map);
    }

}
