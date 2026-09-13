
import org.testng.Assert;
import org.testng.annotations.Test;

public class RealAIClientConfigurationTest {

    @Test
    public void verifyOpenAIConfiguration() {

        System.out.println(
                "======================================"
        );
        System.out.println(
                "     REAL AI CONFIGURATION TEST"
        );
        System.out.println(
                "======================================"
        );

        String apiKey =
                System.getenv("OPENAI_API_KEY");

        Assert.assertNotNull(
                apiKey,
                "OPENAI_API_KEY environment variable is not configured."
        );

        Assert.assertFalse(
                apiKey.isBlank(),
                "OPENAI_API_KEY is empty."
        );

        System.out.println(
                "✅ OPENAI_API_KEY is configured."
        );

        System.out.println(
                "✅ API key was detected successfully."
        );

        System.out.println(
                "======================================"
        );
        System.out.println(
                "     CONFIGURATION TEST PASSED"
        );
        System.out.println(
                "======================================"
        );
    }
}