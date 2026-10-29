package utils;

import java.nio.file.Paths;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class ApiUtils {
 
    public static String loadAndParametrizedPayload(String relativePath, String testDataKey) {
        try {
            // 1. Read payload template file
            String payloadPath = System.getProperty("user.dir") + "/src/test/resources/Payloads/" + relativePath;
            String payload = new String(Files.readAllBytes(Paths.get(payloadPath)));

            // 2. Read testdata.json file
            String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testdata.json";
            String testDataContent = new String(Files.readAllBytes(Paths.get(testDataPath)));
            
            // 3. Parse and replace placeholders
            JSONObject testDataRoot = new JSONObject(testDataContent);
            
            if (testDataRoot.has(testDataKey)) {
                JSONObject specificData = testDataRoot.getJSONObject(testDataKey);
                for (String key : specificData.keySet()) {
                    payload = payload.replace("${" + key + "}", specificData.getString(key));
                }
            } else {
                throw new RuntimeException("Key '" + testDataKey + "' not found in testdata.json");
            }

            return payload;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load and parameterize payload: " + e.getMessage());
        }
    }   
}
