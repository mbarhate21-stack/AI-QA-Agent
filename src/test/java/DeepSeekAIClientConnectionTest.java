
import AI.DeepSeekAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeepSeekAIClientConnectionTest {

    @Test
    public void verifyDeepSeekConnection()
            throws Exception {

        System.out.println(
                "======================================"
        );
        System.out.println(
                "      DEEPSEEK AI CONNECTION TEST"
        );
        System.out.println(
                "======================================"
        );

        DeepSeekAIClient deepSeekAIClient =
                new DeepSeekAIClient();

        String prompt = """
                You are a software testing assistant.

                Give one simple test case for a login page.

                Return only a short plain-text answer.
                """;

        String response =
                deepSeekAIClient.generateResponse(prompt);

        Assert.assertNotNull(
                response,
                "DeepSeek response must not be null."
        );

        Assert.assertFalse(
                response.isBlank(),
                "DeepSeek response must not be empty."
        );

        System.out.println(
                "\nDeepSeek AI Response:"
        );

        System.out.println(response);

        System.out.println(
                "\n======================================"
        );
        System.out.println(
                "      DEEPSEEK CONNECTION PASSED"
        );
        System.out.println(
                "======================================"
        );
    }
}