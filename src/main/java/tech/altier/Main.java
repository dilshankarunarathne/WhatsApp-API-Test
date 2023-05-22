package tech.altier;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Main {
    public static void main( String[] args ) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://graph.facebook.com/v13.0/191135383876894/messages"))
                    .header("Authorization", "Bearer EAATrb8LJqVMBAMNvZADo5quwhSXX61T531fyUZBWKHJYmNLloMTlFtGEshzEqYv6OLKAoP6ZAvRWdRZBfGaqJZBFF1ue6xknwGiYoVeGTZCTW49ypjXcl07bC6FIsc1iMcCF6n6PNz16gc0V7PdCww1ooz23x0fZAKZBZAVC1ch0jFsScPlZA1EXTlSXmA8V7MZAZBRyRQUz0Trocxfk8ho2GiXb4norlb99cJ0ZD")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"+94786241686\", \"type\": \"template\", \"template\": { \"name\": \"hello_world\", \"language\": { \"code\": \"en_US\" } } }"))
                    .build();
            HttpClient http = HttpClient.newHttpClient();
            HttpResponse<String> response = http.send(request,BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}