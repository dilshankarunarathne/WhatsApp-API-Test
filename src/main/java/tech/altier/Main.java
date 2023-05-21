package tech.altier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

/**
* ChatGPT API caller function
* This method will take a string as input and return a string as output
* @return response
*/
public static String chat(String input) throws IOException, JSONException {
    String URL = "https://graph.facebook.com/v16.0/105954558954427/messages";
    HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();

    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Authorization", "Bearer " + API_KEY);

    JSONObject data = new JSONObject();
    data.put("model", "text-davinci-003");
    data.put("prompt", input);
    data.put("max_tokens", 4000);
    data.put("temperature", 1.0);

    con.setDoOutput(true);
    con.getOutputStream().write(data.toString().getBytes());

    String output = new BufferedReader(new InputStreamReader(con.getInputStream()))
            .lines()
            .reduce((a, b) -> a + b)
            .get();

    return new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
}
}
