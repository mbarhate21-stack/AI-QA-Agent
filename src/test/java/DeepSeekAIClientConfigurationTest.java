
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeepSeekAIClientConfigurationTest {

    @Test
    public void verifyDeepSeekAPIKeyConfiguration() {

        System.out.println(
                "======================================"
        );
        System.out.println(
                "   DEEPSEEK API CONFIGURATION TEST"
        );
        System.out.println(
                "======================================"
        );

        String apiKey =
                System.getenv("DEEPSEEK_API_KEY");

        Assert.assertNotNull(
                apiKey,
                "DEEPSEEK_API_KEY is not configured."
        );

        Assert.assertFalse(
                apiKey.isBlank(),
                "DEEPSEEK_API_KEY is empty."
        );

        System.out.println(
                "✅ DEEPSEEK_API_KEY is configured."
        );

        System.out.println(
                "✅ API key was detected successfully."
        );

        System.out.println(
                "======================================"
        );
        System.out.println(
                "   CONFIGURATION TEST PASSED"
        );
        System.out.println(
                "======================================"
        );
    }
}