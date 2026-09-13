import AI.AIClient;
import AI.AITestCaseGenerator;
import AI.AIQAResult;
import AI.AIQAPipeline;
import AI.MockAIClient;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AITestCaseGenerationTest {

    @DataProvider(name = "requirements")
    public Object[][] requirements() {

        return new Object[][] {

                {"REQ001", "Valid login requirement", true},

                {"REQ002", "Invalid login requirement", true},

                {"REQ003", "", false},

                {"REQ004",
                        "User should be able to login with valid username and password",
                        true},

                {"REQ005",
                        "User should be able to make a payment",
                        false},

                {"REQ006",
                        "User should be able to register a new account",
                        false}
        };
    }

    @Test(dataProvider = "requirements")
    public void generateAndValidateTestCases(
            String testId,
            String requirement,
            boolean expectedValid)
            throws Exception {

        System.out.println(
                "\n========== " + testId + " =========="
        );

        AIClient aiClient = new MockAIClient();

        AITestCaseGenerator generator =
                new AITestCaseGenerator(aiClient);

        String aiResponse =
                generator.generateTestCases(requirement);

        System.out.println(
                "\n========== REQUIREMENT =========="
        );
        System.out.println(requirement);

        System.out.println(
                "========== AI RESPONSE =========="
        );
        System.out.println(aiResponse);

        /*
         * Use the complete AI QA pipeline.
         *
         * The pipeline performs:
         * 1. Schema validation
         * 2. Quality validation
         * 3. Requirement relevance validation
         */
        AIQAPipeline pipeline =
                new AIQAPipeline();

        AIQAResult result =
                pipeline.validate(
                        requirement,
                        aiResponse
                );

        List<String> errors =
                result.getErrors();

        boolean actualValid =
                result.isPassed();

        System.out.println(
                "========== VALIDATION ERRORS =========="
        );

        if (errors.isEmpty()) {

            System.out.println(
                    "✅ AI response passed all validations."
            );

        } else {

            for (String error : errors) {

                System.out.println(
                        "❌ " + error
                );
            }
        }

        System.out.println(
                "========== AI QA RESULT =========="
        );

        System.out.println(
                "Test ID: " + testId
        );

        System.out.println(
                "Expected Valid: " + expectedValid
        );

        System.out.println(
                "Actual Valid: " + actualValid
        );

        System.out.println(
                "Validation Errors: " + errors.size()
        );

        if (!errors.isEmpty()) {

            System.out.println(
                    "---------- VALIDATION ERROR DETAILS ----------"
            );

            for (int i = 0; i < errors.size(); i++) {

                System.out.println(
                        "❌ Error "
                                + (i + 1)
                                + ": "
                                + errors.get(i)
                );
            }
        }

        if (actualValid == expectedValid) {

            System.out.println(
                    "Status: PASS"
            );

        } else {

            System.out.println(
                    "Status: FAIL"
            );
        }

        Assert.assertEquals(
                actualValid,
                expectedValid,
                testId
                        + " validation result did not match expected result."
        );
    }
}