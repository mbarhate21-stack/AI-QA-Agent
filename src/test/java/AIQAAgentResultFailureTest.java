
import AI.AIClient;
import AI.AIQAAgent;
import AI.AIQAAgentResult;
import AI.BadMockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentResultFailureTest {

    @Test
    public void testCompleteAgentDetectsInvalidAIResponse()
            throws Exception {

        // Use Bad Mock AI
        AIClient aiClient =
                new BadMockAIClient();

        // Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(aiClient);

        // Requirement
        String prompt = """
                Generate software test cases
                for a login page.
                """;

        // Run complete AI QA process
        AIQAAgentResult result =
                agent.runAndGenerateTestCases(prompt);

        // AI response should fail validation
        Assert.assertFalse(
                result.isPassed(),
                "AI response should have failed validation."
        );

        // There should be errors
        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Expected validation errors."
        );

        // Invalid response must NOT be parsed
        Assert.assertEquals(
                result.getTestCases().size(),
                0,
                "Invalid AI response should not generate test case objects."
        );

        System.out.println();
        System.out.println("======================================");
        System.out.println("   AI QA FAILURE TEST RESULT");
        System.out.println("======================================");

        System.out.println(
                "Validation Status : "
                        + (result.isPassed()
                        ? "PASS"
                        : "FAIL")
        );

        System.out.println(
                "Error Count       : "
                        + result.getErrorCount()
        );

        System.out.println(
                "Generated Objects : "
                        + result.getTestCases().size()
        );

        System.out.println();
        System.out.println("Detected Errors:");

        for (String error : result.getErrors()) {
            System.out.println("- " + error);
        }
    }
}