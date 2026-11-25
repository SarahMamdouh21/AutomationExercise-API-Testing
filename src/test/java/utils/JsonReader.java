package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonReader {

    public static Map<String, String> getJsonDataAsMap(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/test/resources/" + filePath);
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON file from path: " + filePath, e);
        }
    }
}