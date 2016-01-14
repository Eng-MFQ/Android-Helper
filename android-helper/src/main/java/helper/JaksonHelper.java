package helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Bayan on 4/16/2015.
 */
public class JaksonHelper {

    public static ArrayList<?> getJsonList(String jsonString , TypeReference<?> type )
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString,  type);

    }

    public static Object getJsonObject(String jsonString , Class<?> type ) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, type);

    }
}
