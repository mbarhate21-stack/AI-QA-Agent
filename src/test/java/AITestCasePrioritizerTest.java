import AI.AIClient;
import AI.AITestCase;
import AI.AITestCaseParser;
import AI.AITestCasePrioritizer;
import AI.MockAIClient;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AITestCasePrioritizerTest {

    @Test
    public void shouldPrioritizeTestCasesByRisk()
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("     TEST CASE PRIORITIZATION TEST");
        System.out.println("======================================");

        // Step 1: Generate AI response
        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate login test cases"
                );

        // Step 2: Parse AI response
        AITestCaseParser parser =
                new AITestCaseParser();

        List<AITestCase> testCases =
                parser.parse(aiResponse);

        // Step 3: Prioritize test cases
        AITestCasePrioritizer prioritizer =
                new AITestCasePrioritizer();

        List<AITestCase> prioritizedTestCases =
                prioritizer.prioritize(testCases);

        // Step 4: Display priorities
        System.out.println();
        System.out.println("Prioritized Test Cases:");

        for (AITestCase testCase :
                prioritizedTestCases) {

            System.out.println(
                    prioritizer.getPriority(testCase.type)
                            + " - "
                            + testCase.test_case_id
                            + " - "
                            + testCase.type
                            + " - "
                            + testCase.title
            );
        }

        // Step 5: Verify ordering
        Assert.assertEquals(
                prioritizedTestCases.get(0).test_case_id,
                "TC004"
        );

        Assert.assertEquals(
                prioritizedTestCases.get(1).test_case_id,
                "TC002"
        );

        Assert.assertEquals(
                prioritizedTestCases.get(2).test_case_id,
                "TC003"
        );

        Assert.assertEquals(
                prioritizedTestCases.get(3).test_case_id,
                "TC001"
        );

        // Step 6: Verify priority values
        Assert.assertEquals(
                prioritizer.getPriority(
                        prioritizedTestCases.get(0).type
                ),
                "P1"
        );

        Assert.assertEquals(
                prioritizer.getPriority(
                        prioritizedTestCases.get(1).type
                ),
                "P2"
        );

        Assert.assertEquals(
                prioritizer.getPriority(
                        prioritizedTestCases.get(2).type
                ),
                "P3"
        );

        Assert.assertEquals(
                prioritizer.getPriority(
                        prioritizedTestCases.get(3).type
                ),
                "P4"
        );

        System.out.println();
        System.out.println(
                "✅ Test case prioritization is correct."
        );
    }
}