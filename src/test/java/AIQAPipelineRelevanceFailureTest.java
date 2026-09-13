import AI.AIClient;
import AI.AIQAPipeline;
import AI.AIQAResult;
import AI.MockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAPipelineRelevanceFailureTest {

    @Test
    public void irrelevantAIResponseShouldFailPipeline()
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
                "========== RELEVANCE FAILURE PIPELINE TEST =========="
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
                "Irrelevant AI response should fail the pipeline."
        );

        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Irrelevant AI response should produce relevance errors."
        );
    }
}