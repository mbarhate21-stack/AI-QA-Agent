import AI.AIQAAgent;
import AI.AIQAAgentResult;
import AI.MockAIClient;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentIrrelevantRequirementTest {

    @Test
    public void testIrrelevantRequirementIsRejected()
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("   IRRELEVANT REQUIREMENT TEST");
        System.out.println("======================================");

        // Step 1: Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(
                        new MockAIClient()
                );

        // Step 2: Provide a payment requirement
        String requirement =
                "The user should be able to make a payment using a credit card.";

        // Step 3: Process the requirement
        AIQAAgentResult result =
                agent.processRequirement(
                        requirement
                );

        // Step 4: Verify that irrelevant test cases fail validation
        Assert.assertFalse(
                result.isPassed(),
                "Irrelevant generated test cases should fail."
        );

        // Step 5: Verify that validation errors exist
        Assert.assertTrue(
                result.getErrorCount() > 0,
                "There should be validation errors."
        );

        // Step 6: Verify that no test cases are returned
        Assert.assertTrue(
                result.getTestCases().isEmpty(),
                "Irrelevant test cases should not be returned."
        );

        System.out.println();
        System.out.println(
                "✅ Irrelevant requirement was rejected correctly."
        );
    }
}