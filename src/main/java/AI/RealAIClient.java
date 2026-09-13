package AI;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class RealAIClient implements AIClient {

    private final OpenAIClient client;

    public RealAIClient() {
        client = OpenAIOkHttpClient.fromEnv();
    }

    @Override
    public String generateResponse(String prompt)
            throws Exception {

        System.out.println(
                "Real AI Client is generating response..."
        );

        ChatCompletionCreateParams params =
                ChatCompletionCreateParams.builder()
                        .model("gpt-4o-mini")
                        .addUserMessage(prompt)
                        .build();

        ChatCompletion response =
                client.chat()
                        .completions()
                        .create(params);

        return response.choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }
}