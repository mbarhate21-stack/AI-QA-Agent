import AI.AIClient;
import AI.AIQAPipeline;
import AI.AIQAResult;
import AI.MockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAPipelineEdgeCaseTest {

    @Test
    public void emptyRequirementShouldFail()
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
                        "",
                        aiResponse
                );

        System.out.println();
        System.out.println(
                "========== EMPTY REQUIREMENT TEST =========="
        );

        System.out.println(
                "Passed: " + result.isPassed()
        );

        for (String error : result.getErrors()) {
            System.out.println("❌ " + error);
        }

        Assert.assertFalse(
                result.isPassed(),
                "Empty requirement should fail validation."
        );

        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Empty requirement should produce an error."
        );
    }

    @Test
    public void invalidJsonShouldFailBeforeRelevanceValidation()
            throws Exception {

        String invalidAiResponse =
                """
                {
                  "test_cases": [
                """;

        AIQAPipeline pipeline =
                new AIQAPipeline();

        AIQAResult result =
                pipeline.validate(
                        "User should be able to login",
                        invalidAiResponse
                );

        System.out.println();
        System.out.println(
                "========== INVALID JSON PIPELINE TEST =========="
        );

        System.out.println(
                "Passed: " + result.isPassed()
        );

        for (String error : result.getErrors()) {
            System.out.println("❌ " + error);
        }

        Assert.assertFalse(
                result.isPassed(),
                "Invalid JSON should fail the pipeline."
        );

        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Invalid JSON should produce validation errors."
        );
    }
}