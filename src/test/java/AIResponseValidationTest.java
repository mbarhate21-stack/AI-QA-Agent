package AI;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AIResponseValidationTest {

    private final AITestCaseValidator validator =
            new AITestCaseValidator();

    // =========================================================
    // 1. VALID RESPONSE
    // =========================================================

    @Test
    public void testValidResponse() throws Exception {

        String response = """
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
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.isEmpty(),
                "Valid response should have no errors: "
                        + errors
        );
    }

    // =========================================================
    // 2. INVALID TYPE
    // =========================================================

    @Test
    public void testInvalidType() throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "URGENT",
              "precondition": "User is registered.",
              "test_steps": [
                "Open the login page",
                "Enter username"
              ],
              "test_data": "Username: testuser",
              "expected_result": "User should reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Invalid type: URGENT"
                        )
                ),
                "Expected invalid type error."
        );
    }

    // =========================================================
    // 3. MISSING REQUIRED FIELD
    // =========================================================

    @Test
    public void testMissingExpectedResult()
            throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_steps": [
                "Open the login page",
                "Enter username"
              ],
              "test_data": "Username: testuser"
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Missing required field: expected_result"
                        )
                ),
                "Expected missing expected_result error."
        );
    }

    // =========================================================
    // 4. EXTRA FIELD
    // =========================================================

    @Test
    public void testUnexpectedField()
            throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_steps": [
                "Open the login page",
                "Enter username"
              ],
              "test_data": "Username: testuser",
              "expected_result": "User should reach the dashboard.",
              "confidence": 0.98
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Unexpected field: confidence"
                        )
                ),
                "Expected unexpected field error."
        );
    }

    // =========================================================
    // 5. INVALID JSON
    // =========================================================

    @Test
    public void testInvalidJson()
            throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE"
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Invalid JSON response"
                        )
                ),
                "Expected invalid JSON error."
        );
    }

    // =========================================================
    // 6. MISSING TEST STEPS
    // =========================================================

    @Test
    public void testMissingTestSteps()
            throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_data": "Username: testuser",
              "expected_result": "User should reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Missing required field: test_steps"
                        )
                ),
                "Expected missing test_steps error."
        );
    }

    // =========================================================
    // 7. TEST STEPS MUST BE AN ARRAY
    // =========================================================

    @Test
    public void testTestStepsMustBeArray()
            throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_steps": "Open login page",
              "test_data": "Username: testuser",
              "expected_result": "User should reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "test_steps must be an array"
                        )
                ),
                "Expected test_steps array error."
        );
    }
}