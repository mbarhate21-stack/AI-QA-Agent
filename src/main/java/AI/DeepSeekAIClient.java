package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeepSeekAIClient implements AIClient {

    private static final String API_URL =
            "https://api.deepseek.com/chat/completions";

    private static final String MODEL =
            "deepseek-v4-flash";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public DeepSeekAIClient() {

        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    @Override
    public String generateResponse(String prompt)
            throws Exception {

        System.out.println(
                "DeepSeek AI Client is generating response..."
        );

        String apiKey =
                System.getenv("DEEPSEEK_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {

            throw new IllegalStateException(
                    "DEEPSEEK_API_KEY environment variable is not configured."
            );
        }

        String requestBody = """
                {
                  "model": "%s",
                  "messages": [
                    {
                      "role": "user",
                      "content": %s
                    }
                  ],
                  "thinking": {
                    "type": "disabled"
                  },
                  "response_format": {
                    "type": "text"
                  }
                }
                """.formatted(
                MODEL,
                objectMapper.writeValueAsString(prompt)
        );

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(API_URL))
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .header(
                                "Authorization",
                                "Bearer " + apiKey
                        )
                        .POST(
                                HttpRequest.BodyPublishers.ofString(
                                        requestBody
                                )
                        )
                        .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() < 200
                || response.statusCode() >= 300) {

            throw new RuntimeException(
                    "DeepSeek API request failed. "
                            + "HTTP Status: "
                            + response.statusCode()
                            + "\nResponse: "
                            + response.body()
            );
        }

        JsonNode root =
                objectMapper.readTree(response.body());

        JsonNode content =
                root.path("choices")
                        .path(0)
                        .path("message")
                        .path("content");

        if (content.isMissingNode()
                || content.isNull()) {

            throw new RuntimeException(
                    "DeepSeek API returned no message content."
            );
        }

        return content.asText();
    }
}