

import AI.AIClient;
import AI.AIQAAgent;
import AI.AIQAResult;
import AI.BadMockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentFailureTest {

    @Test
    public void testAIQAAgentDetectsBadResponse()
            throws Exception {

        // Use deliberately bad AI response
        AIClient aiClient =
                new BadMockAIClient();

        // Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(aiClient);

        String prompt = """
                Generate software test cases
                for a login page.
                """;

        // Run complete AI QA process
        AIQAResult result =
                agent.run(prompt);

        // The agent SHOULD detect the problems
        Assert.assertFalse(
                result.isPassed(),
                "AI QA Agent should have detected invalid AI output."
        );

        // Verify that errors were detected
        Assert.assertTrue(
                result.getErrorCount() > 0,
                "Expected AI QA Agent to report errors."
        );

        // Print detected errors
        System.out.println();
        System.out.println("Detected AI QA Errors:");

        for (String error : result.getErrors()) {
            System.out.println("- " + error);
        }
    }
}