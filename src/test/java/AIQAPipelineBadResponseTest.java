import AI.AIClient;
import AI.AIQAPipeline;
import AI.AIQAResult;
import AI.BadMockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAPipelineBadResponseTest {

    @Test
    public void badAIResponseShouldFailCompletePipeline()
            throws Exception {

        AIClient aiClient =
                new BadMockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate login test cases"
                );

        AIQAPipeline pipeline =
                new AIQAPipeline();

        AIQAResult result =
                pipeline.validate(
                        "User should be able to login",
                        aiResponse
                );

        System.out.println();
        System.out.println(
                "========== BAD AI RESPONSE PIPELINE TEST =========="
        );

        System.out.println(
                "Passed: " + result.isPassed()
        );

        System.out.println(
                "Error Count: " + result.getErrorCount()
        );

        for (String error : result.getErrors()) {

            System.out.println(
                    "❌ " + error
            );
        }

        Assert.assertFalse(
                result.isPassed(),
                "Bad AI response should fail the complete pipeline."
        );

        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Bad AI response should produce validation errors."
        );
    }
}