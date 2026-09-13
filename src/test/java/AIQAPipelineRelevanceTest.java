import AI.AIClient;
import AI.AIQAPipeline;
import AI.AIQAResult;
import AI.MockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAPipelineRelevanceTest {

    @Test
    public void loginRequirementShouldPassCompletePipeline()
            throws Exception {

        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate login test cases"
                );

        AIQAPipeline pipeline =
                new AIQAPipeline();

        AIQAResult result =
                pipeline.validate(
                        "User should be able to login with valid username and password",
                        aiResponse
                );

        System.out.println();
        System.out.println(
                "========== LOGIN PIPELINE TEST =========="
        );

        System.out.println(
                "Passed: " + result.isPassed()
        );

        System.out.println(
                "Errors: " + result.getErrors()
        );

        Assert.assertTrue(
                result.isPassed(),
                "Login requirement should pass the complete pipeline."
        );
    }

    @Test
    public void paymentRequirementShouldFailCompletePipeline()
            throws Exception {

        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate payment test cases"
                );

        AIQAPipeline pipeline =
                new AIQAPipeline();

        AIQAResult result =
                pipeline.validate(
                        "User should be able to make a payment",
                        aiResponse
                );

        System.out.println();
        System.out.println(
                "========== PAYMENT PIPELINE TEST =========="
        );

        System.out.println(
                "Passed: " + result.isPassed()
        );

        for (String error : result.getErrors()) {
            System.out.println("❌ " + error);
        }

        Assert.assertFalse(
                result.isPassed(),
                "Payment requirement should fail because mock returns login cases."
        );

        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Failure should contain validation errors."
        );
    }
}