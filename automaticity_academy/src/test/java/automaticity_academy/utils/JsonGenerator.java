package automaticity_academy.utils;

import org.json.JSONObject;

public class JsonGenerator {

    public static JSONObject generateJson(String[][] userData) {
        JSONObject json = new JSONObject();
        for (String[] data : userData) {
            json.put(data[0], data[1]);
        }
        return json;
    }

}
