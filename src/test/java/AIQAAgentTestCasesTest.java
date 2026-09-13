import AI.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AIQAAgentTestCasesTest {

    @Test
    public void testAgentGeneratesTestCases()
            throws Exception {

        // Use Mock AI
        AIClient aiClient =
                new MockAIClient();

        // Create AI QA Agent
        AIQAAgent agent =
                new AIQAAgent(aiClient);

        // Requirement
        String prompt = """
                Generate software test cases
                for a login page.
                """;

        // Generate structured test cases
        List<AITestCase> testCases =
                agent.generateTestCases(prompt);

        // Verify number of test cases
        Assert.assertEquals(
                testCases.size(),
                4,
                "Expected 4 test cases."
        );

        System.out.println();
        System.out.println("======================================");
        System.out.println("    AI AGENT GENERATED TEST CASES");
        System.out.println("======================================");

        for (AITestCase testCase : testCases) {

            System.out.println(
                    testCase.test_case_id
                            + " | "
                            + testCase.type
                            + " | "
                            + testCase.title
            );
        }

        // Verify prioritised order

        // P1 - Security
        Assert.assertEquals(
                testCases.get(0).test_case_id,
                "TC004"
        );

        Assert.assertEquals(
                testCases.get(0).type,
                TestCaseType.SECURITY
        );

        // P2 - Negative
        Assert.assertEquals(
                testCases.get(1).test_case_id,
                "TC002"
        );

        Assert.assertEquals(
                testCases.get(1).type,
                TestCaseType.NEGATIVE
        );

        // P3 - Boundary
        Assert.assertEquals(
                testCases.get(2).test_case_id,
                "TC003"
        );

        Assert.assertEquals(
                testCases.get(2).type,
                TestCaseType.BOUNDARY
        );

        // P4 - Positive
        Assert.assertEquals(
                testCases.get(3).test_case_id,
                "TC001"
        );

        Assert.assertEquals(
                testCases.get(3).type,
                TestCaseType.POSITIVE
        );
    }
}