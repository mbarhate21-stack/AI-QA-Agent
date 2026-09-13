
import AI.RealAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RealAIClientConnectionTest {

    @Test
    public void verifyRealAIConnection() throws Exception {

        System.out.println(
                "======================================"
        );
        System.out.println(
                "       REAL AI CONNECTION TEST"
        );
        System.out.println(
                "======================================"
        );

        RealAIClient realAIClient =
                new RealAIClient();

        String prompt = """
                You are a software testing assistant.

                Give one simple test case for a login page.

                Return only a short plain-text answer.
                """;

        String response =
                realAIClient.generateResponse(prompt);

        Assert.assertNotNull(
                response,
                "Real AI response must not be null."
        );

        Assert.assertFalse(
                response.isBlank(),
                "Real AI response must not be empty."
        );

        System.out.println(
                "\nReal AI Response:"
        );

        System.out.println(response);

        System.out.println(
                "\n======================================"
        );
        System.out.println(
                "     REAL AI CONNECTION PASSED"
        );
        System.out.println(
                "======================================"
        );
    }
}