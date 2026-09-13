import AI.AIQAAgent;
import AI.AIQAAgentResult;
import AI.AITestCase;
import AI.MockAIClient;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AIQAAgentResultTest {

    @Test
    public void testCompleteAIQAAgentResult()
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       AI QA AGENT RESULT TEST");
        System.out.println("======================================");

        // Step 1: Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(
                        new MockAIClient()
                );

        // Step 2: Run complete AI QA process
        AIQAAgentResult result =
                agent.runAndGenerateTestCases(
                        "Generate login test cases"
                );

        // Step 3: Verify validation passed
        Assert.assertTrue(
                result.isPassed(),
                "AI QA validation should pass."
        );

        // Step 4: Verify there are no validation errors
        Assert.assertEquals(
                result.getErrorCount(),
                0,
                "There should be no validation errors."
        );

        // Step 5: Get generated test cases
        List<AITestCase> testCases =
                result.getTestCases();

        // Step 6: Verify number of test cases
        Assert.assertEquals(
                testCases.size(),
                4,
                "There should be 4 generated test cases."
        );

        // Step 7: Verify prioritised order
        Assert.assertEquals(
                testCases.get(0).test_case_id,
                "TC004",
                "Security test case should be first."
        );

        Assert.assertEquals(
                testCases.get(1).test_case_id,
                "TC002",
                "Negative test case should be second."
        );

        Assert.assertEquals(
                testCases.get(2).test_case_id,
                "TC003",
                "Boundary test case should be third."
        );

        Assert.assertEquals(
                testCases.get(3).test_case_id,
                "TC001",
                "Positive test case should be fourth."
        );

        System.out.println();
        System.out.println(
                "✅ AI QA Agent result and prioritisation are correct."
        );
    }
}
