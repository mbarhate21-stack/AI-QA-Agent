
import AI.AIQAReport;
import AI.AIQAResult;
import AI.AIQAPipeline;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AIQAPipelineTest {

    @DataProvider(name = "aiResponses")
    public Object[][] aiResponses() {

        return new Object[][] {

                // =========================================
                // P001 - Valid Schema + Valid Quality
                // Expected: PASS
                // =========================================

                {
                        "P001",
                        """
                        {
                          "test_cases": [
                            {
                              "test_case_id": "TC001",
                              "title": "Login with valid credentials",
                              "type": "POSITIVE",
                              "precondition": "User is registered and is on the login page.",
                              "test_steps": [
                                "Open the login page",
                                "Enter a valid username",
                                "Enter a valid password",
                                "Click the Login button"
                              ],
                              "test_data": "Username: testuser, Password: Test@123",
                              "expected_result": "User should successfully reach the dashboard."
                            }
                          ]
                        }
                        """,
                        true
                },

                // =========================================
                // P002 - Valid JSON but Invalid Quality
                // Expected: FAIL
                // =========================================

                {
                        "P002",
                        """
                        {
                          "test_cases": [
                            {
                              "test_case_id": "TC002",
                              "title": "Test",
                              "type": "POSITIVE",
                              "precondition": "User is on the login page.",
                              "test_steps": [
                                "Open the login page",
                                "Click Login"
                              ],
                              "test_data": "Valid login credentials",
                              "expected_result": "It should work"
                            }
                          ]
                        }
                        """,
                        false
                },

                // =========================================
                // P003 - Invalid Schema
                // Expected: FAIL
                // =========================================

                {
                        "P003",
                        """
                        {
                          "test_cases": [
                            {
                              "test_case_id": "TC003",
                              "title": "Login with invalid type",
                              "type": "URGENT",
                              "precondition": "User is on the login page.",
                              "test_steps": [
                                "Open the login page",
                                "Enter login details"
                              ],
                              "test_data": "Invalid login data",
                              "expected_result": "Login should fail."
                            }
                          ]
                        }
                        """,
                        false
                },

                // =========================================
                // P004 - Invalid JSON
                // Expected: FAIL
                // =========================================

                {
                        "P004",
                        "This is not valid JSON",
                        false
                },

                // =========================================
                // P005 - Multiple Valid Test Cases
                // Expected: PASS
                // =========================================

                {
                        "P005",
                        """
                        {
                          "test_cases": [
                            {
                              "test_case_id": "TC005",
                              "title": "Login with valid credentials",
                              "type": "POSITIVE",
                              "precondition": "User is registered and is on the login page.",
                              "test_steps": [
                                "Open the login page",
                                "Enter a valid username",
                                "Enter a valid password",
                                "Click the Login button"
                              ],
                              "test_data": "Username: testuser, Password: Test@123",
                              "expected_result": "User should successfully reach the dashboard."
                            },
                            {
                              "test_case_id": "TC006",
                              "title": "Login with invalid password",
                              "type": "NEGATIVE",
                              "precondition": "User is registered and is on the login page.",
                              "test_steps": [
                                "Open the login page",
                                "Enter a valid username",
                                "Enter an invalid password",
                                "Click the Login button"
                              ],
                              "test_data": "Username: testuser, Password: Wrong@123",
                              "expected_result": "User should see an authentication error message."
                            }
                          ]
                        }
                        """,
                        true
                }
        };
    }

    @Test(dataProvider = "aiResponses")
    public void validateAIAgentResponse(
            String testId,
            String aiResponse,
            boolean expectedValid)
            throws Exception {

        System.out.println(
                "\n========== " + testId + " =========="
        );

        // =========================================
        // Create AI QA Pipeline
        // =========================================

        AIQAPipeline pipeline =
                new AIQAPipeline();

        // =========================================
        // Run Complete AI QA Pipeline
        // =========================================

        AIQAResult result =
                pipeline.validate(aiResponse);

        // =========================================
        // Create Report
        // =========================================

        AIQAReport report =
                new AIQAReport(
                        testId,
                        result
                );

        report.printReport();

        // =========================================
        // Get Result
        // =========================================

        boolean actualValid =
                result.isPassed();

        List<String> errors =
                result.getErrors();

        // =========================================
        // Print Result
        // =========================================

        System.out.println(
                "Expected Result: " + expectedValid
        );

        System.out.println(
                "Actual Result:   " + actualValid
        );

        System.out.println(
                "AI QA Status:    "
                        + (actualValid
                        ? "PASS"
                        : "FAIL")
        );

        System.out.println(
                "Error Count:     "
                        + result.getErrorCount()
        );

        // =========================================
        // Print Validation Errors
        // =========================================

        if (!errors.isEmpty()) {

            System.out.println(
                    "Validation Errors:"
            );

            for (String error : errors) {

                System.out.println(
                        "- " + error
                );
            }
        }

        // =========================================
        // TestNG Assertion
        // =========================================

        Assert.assertEquals(
                actualValid,
                expectedValid,
                testId
                        + " AI QA pipeline result did not match expected result."
        );
    }
}
