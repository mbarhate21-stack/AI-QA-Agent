import AI.AIQAAgent;
import AI.AIQAAgentResult;
import AI.MockAIClient;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentEmptyRequirementTest {

    @Test
    public void testEmptyRequirementIsRejected()
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("     EMPTY REQUIREMENT TEST");
        System.out.println("======================================");

        // Step 1: Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(
                        new MockAIClient()
                );

        // Step 2: Send an empty requirement
        AIQAAgentResult result =
                agent.processRequirement(
                        ""
                );

        // Step 3: Verify that processing failed
        Assert.assertFalse(
                result.isPassed(),
                "Empty requirement should fail."
        );

        // Step 4: Verify the expected error count
        Assert.assertEquals(
                result.getErrorCount(),
                1,
                "There should be one validation error."
        );

        // Step 5: Verify the error message
        Assert.assertEquals(
                result.getErrors().get(0),
                "Requirement must not be empty.",
                "Incorrect error message."
        );

        // Step 6: Verify no test cases were generated
        Assert.assertTrue(
                result.getTestCases().isEmpty(),
                "No test cases should be generated."
        );

        System.out.println();
        System.out.println(
                "✅ Empty requirement was rejected correctly."
        );
    }
}