package utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;


public class JsonReader {

    public static JSONObject getData()
            throws Exception {

        String content =
            Files.readString(
             Paths.get(
              "src/test/resources/testdata.json"));

        return new JSONObject(content);
    }
}