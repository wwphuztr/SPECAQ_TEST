package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ConfigObject configObject() {
        // Read the configuration file
        String configFile = "resources/config.json";
        JsonObject config = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();
        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
        JsonObject webConfig = config.get("google").getAsJsonObject();
        JsonObject themeObject = new JsonObject();
        String env = webConfig.get("env").getAsString();
        System.out.println("-----env-----: " + env);

        // Find the right environment
        if (env.equals("prod")) {
            env = webConfig.get("prod").getAsString();
        } else if (env.equals("stag")) {
            env = webConfig.get("stag").getAsString();
        } else {
            env = webConfig.get("dev").getAsString();
        }
        jsonObject.addProperty("env", env);
        jsonObject.addProperty("timeOut", webConfig.get("timeout").getAsNumber());
        jsonObject.addProperty("browser", webConfig.get("browser").getAsString());
        jsonObject.addProperty("urlHome", env + webConfig.get("route").getAsJsonObject().get("home").getAsString());

        String osName = "win";
        if (osName.contains("win")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome", webConfig.get("web_driver_path").getAsJsonObject().get("win").getAsJsonObject().get("chrome").getAsString());
        } else if (osName.contains("nix") || osName.contains("nux")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome", webConfig.get("web_driver_path").getAsJsonObject().get("linux").getAsJsonObject().get("chrome").getAsString());
        } else {
            // nothing
        }
        ConfigObject configObject = gson.fromJson(jsonObject, ConfigObject.class);
        // Return the object
        return configObject;
    }
    @Data
    public class ConfigObject {
        private String env;
        private String browser;
        private Integer timeOut;
        private String urlHome;
    }
}
