/*import AI.AIResponseValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AIJsonValidationTest {

    AIResponseValidator validator =
            new AIResponseValidator();


    @DataProvider(name = "aiResponses")
    public Object[][] aiResponses() {

        return new Object[][] {

                {
                        "Valid Response",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Invalid Severity",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "URGENT",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Invalid Priority",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P0",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Wrong Boolean Type",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": "true",
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Extra Field",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality.",
                          "confidence": 95
                        }
                        """
                },

                {
                        "Missing Field",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true
                        }
                        """
                }
        };
    }


    @Test(dataProvider = "aiResponses")
    public void validateAIResponse(
            String testName,
            String aiResponse) throws Exception {

        System.out.println();
        System.out.println(
                "Running: " + testName
        );

        List<String> errors =
                validator.validate(aiResponse);


        // Print errors

        for (String error : errors) {

            System.out.println(
                    "❌ " + error
            );
        }


        //you need hide this while using
         * For this lesson:
         *
         * Valid Response should have ZERO errors.
         *
         * Invalid responses should contain
         * validation errors.


        if (testName.equals("Valid Response")) {

            Assert.assertTrue(
                    errors.isEmpty(),
                    "Valid response contains errors: "
                            + errors
            );

        } else {

            Assert.assertFalse(
                    errors.isEmpty(),
                    "Expected validation errors, but none were found."
            );
        }
    }
}*/
import AI.AIResponseValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class AIJsonValidationTest {

    AIResponseValidator validator =
            new AIResponseValidator();


    @DataProvider(name = "aiResponses")
    public Object[][] aiResponses() {

        return new Object[][] {

                {
                        "Valid Response",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Invalid Severity",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "URGENT",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Invalid Priority",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P0",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Wrong Boolean Type",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": "true",
                          "recommendation": "Check login functionality."
                        }
                        """
                },

                {
                        "Extra Field",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true,
                          "recommendation": "Check login functionality.",
                          "confidence": 95
                        }
                        """
                },

                {
                        "Missing Field",
                        """
                        {
                          "bug_title": "Login button does not work",
                          "severity": "HIGH",
                          "priority": "P1",
                          "is_valid_bug": true
                        }
                        """
                }
        };
    }


    @Test(dataProvider = "aiResponses")
    public void validateAIResponse(
            String testName,
            String aiResponse) throws Exception {

        System.out.println();
        System.out.println(
                "Running: " + testName
        );

        List<String> errors =
                validator.validate(aiResponse);


        // Print errors

        for (String error : errors) {

            System.out.println(
                    "❌ " + error
            );
        }


        /*
         * For this lesson:
         *
         * Valid Response should have ZERO errors.
         *
         * Invalid responses should contain
         * validation errors.
         */

        if (testName.equals("Valid Response")) {

            Assert.assertTrue(
                    errors.isEmpty(),
                    "Valid response contains errors: "
                            + errors
            );

        } else {

            Assert.assertFalse(
                    errors.isEmpty(),
                    "Expected validation errors, but none were found."
            );
        }
    }
}