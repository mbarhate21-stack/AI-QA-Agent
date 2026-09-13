
import AI.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AITestCaseParserTest {

    @Test
    public void testParseAITestCases() throws Exception {

        // Step 1: Get AI response from Mock AI
        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate software test cases for a login page."
                );

        // Step 2: Parse JSON response
        AITestCaseParser parser =
                new AITestCaseParser();

        List<AITestCase> testCases =
                parser.parse(aiResponse);

        // Step 3: Verify number of test cases
        Assert.assertEquals(
                testCases.size(),
                4,
                "Expected 4 test cases."
        );

        // Step 4: Display parsed test cases
        System.out.println();
        System.out.println("======================================");
        System.out.println("       PARSED AI TEST CASES");
        System.out.println("======================================");

        for (AITestCase testCase : testCases) {

            System.out.println(
                    "ID              : "
                            + testCase.test_case_id
            );

            System.out.println(
                    "Title           : "
                            + testCase.title
            );

            System.out.println(
                    "Type            : "
                            + testCase.type
            );

            System.out.println(
                    "Expected Result : "
                            + testCase.expected_result
            );

            System.out.println("--------------------------------------");
        }

        // Step 5: Verify first test case
        AITestCase firstTestCase =
                testCases.get(0);

        Assert.assertEquals(
                firstTestCase.test_case_id,
                "TC001"
        );

        Assert.assertEquals(
                firstTestCase.type,
                TestCaseType.POSITIVE
        );
    }
}