
import AI.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIClientFactoryTest {

    @Test
    public void verifyMockProvider() {

        AIClient client =
                AIClientFactory.createClient("MOCK");

        Assert.assertTrue(
                client instanceof MockAIClient,
                "Factory should create MockAIClient."
        );

        System.out.println(
                "✅ Factory created MOCK provider successfully."
        );
    }

    @Test
    public void verifyOpenAIProvider() {

        AIClient client =
                AIClientFactory.createClient("OPENAI");

        Assert.assertTrue(
                client instanceof RealAIClient,
                "Factory should create RealAIClient."
        );

        System.out.println(
                "✅ Factory created OPENAI provider successfully."
        );
    }

    @Test
    public void verifyDeepSeekProvider() {

        AIClient client =
                AIClientFactory.createClient("DEEPSEEK");

        Assert.assertTrue(
                client instanceof DeepSeekAIClient,
                "Factory should create DeepSeekAIClient."
        );

        System.out.println(
                "✅ Factory created DEEPSEEK provider successfully."
        );
    }

    @Test
    public void verifyDefaultProvider() {

        AIClient client =
                AIClientFactory.createClient("");

        Assert.assertTrue(
                client instanceof MockAIClient,
                "Blank provider should default to MockAIClient."
        );

        System.out.println(
                "✅ Blank provider defaults to MOCK successfully."
        );
    }

    @Test
    public void verifyUnsupportedProvider() {

        Assert.expectThrows(
                IllegalArgumentException.class,
                () -> AIClientFactory.createClient("GEMINI")
        );

        System.out.println(
                "✅ Unsupported provider was rejected correctly."
        );
    }
}