package tech.altier;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static String API_KEY = "e4e79003d371d80a3fb7f7be39e1f6ee";

    public static void main(String[] args) throws JSONException, IOException {
        chat("hello_world");
    }

/**
* ChatGPT API caller function
* This method will take a string as input and return a string as output
* @return response
*/
public static String chat(String input) throws IOException, JSONException {
    String URL = "https://graph.facebook.com/v16.0/1384765148735827/messages";
    HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();

    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Bearer " + API_KEY);

    JSONObject data = new JSONObject();
    data.put("messaging_product", "whatsapp");
    data.put("to", "+94761824607");
    data.put("type", "template");
    JSONObject template = new JSONObject();
    template.put("name", input);
    JSONObject language = new JSONObject();
    language.put("code", "en_US");
    template.put("language", language);
    data.put("template", template);

    con.setDoOutput(true);
    con.getOutputStream().write(data.toString().getBytes());

    String output = new BufferedReader(new InputStreamReader(con.getInputStream()))
            .lines()
            .reduce((a, b) -> a + b)
            .get();

    return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
}
}
