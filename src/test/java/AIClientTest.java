import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class AIClientTest {

    public static void main(String[] args) {

        String apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException("OPENAI_API_KEY not found");
        }

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        String bugReport = """
                Bug Title:
                Login button does not work.

                Steps:
                1. Open login page.
                2. Enter valid username.
                3. Enter valid password.
                4. Click Login.

                Expected:
                User should reach the dashboard.

                Actual:
                Nothing happens after clicking Login.
                """;

        ChatCompletionCreateParams params =
                ChatCompletionCreateParams.builder()
                        .model(ChatModel.GPT_4O_MINI)
                        .addUserMessage("""
                                You are a Senior QA Engineer.

                                Analyze this bug report:

                                %s

                                Return only a JSON object with:
                                {
                                  "bug_title": "",
                                  "severity": "",
                                  "priority": "",
                                  "is_valid_bug": true,
                                  "recommendation": ""
                                }

                                Allowed severity:
                                LOW, MEDIUM, HIGH, CRITICAL

                                Allowed priority:
                                P1, P2, P3, P4
                                """.formatted(bugReport))
                        .build();

        ChatCompletion response =
                client.chat().completions().create(params);

        String aiResponse =
                response.choices()
                        .get(0)
                        .message()
                        .content()
                        .orElse("");

        System.out.println("AI RESPONSE:");
        System.out.println(aiResponse);
    }
}