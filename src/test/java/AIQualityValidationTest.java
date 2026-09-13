package AI;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AIQualityValidationTest {

    private final AIQualityValidator validator =
            new AIQualityValidator();

    // =========================================================
    // 1. GOOD TEST CASE
    // =========================================================

    @Test
    public void testGoodTestCase() throws Exception {

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
                "Valid test case should have no quality errors: "
                        + errors
        );
    }

    // =========================================================
    // 2. EMPTY PRECONDITION
    // =========================================================

    @Test
    public void testEmptyPrecondition() throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "",
              "test_steps": [
                "Open the login page",
                "Enter username"
              ],
              "test_data": "Username: testuser",
              "expected_result": "User should successfully reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Precondition is missing or empty"
                        )
                )
        );
    }

    // =========================================================
    // 3. EMPTY TEST STEPS
    // =========================================================

    @Test
    public void testEmptyTestSteps() throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_steps": [],
              "test_data": "Username: testuser",
              "expected_result": "User should successfully reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Test steps must not be empty"
                        )
                )
        );
    }

    // =========================================================
    // 4. ONLY ONE TEST STEP
    // =========================================================

    @Test
    public void testOnlyOneTestStep() throws Exception {

        String response = """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login with valid credentials",
              "type": "POSITIVE",
              "precondition": "User is registered.",
              "test_steps": [
                "Open the login page"
              ],
              "test_data": "Username: testuser",
              "expected_result": "User should successfully reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "at least 2 test steps"
                        )
                )
        );
    }

    // =========================================================
    // 5. EMPTY TEST DATA
    // =========================================================

    @Test
    public void testEmptyTestData() throws Exception {

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
              "test_data": "",
              "expected_result": "User should successfully reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Test data is missing or empty"
                        )
                )
        );
    }

    // =========================================================
    // 6. EMPTY TEST STEP
    // =========================================================

    @Test
    public void testEmptyIndividualTestStep()
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
                "",
                "Click the Login button"
              ],
              "test_data": "Username: testuser",
              "expected_result": "User should successfully reach the dashboard."
            }
          ]
        }
        """;

        List<String> errors =
                validator.validate(response);

        Assert.assertTrue(
                errors.stream().anyMatch(
                        error -> error.contains(
                                "Test step must not be empty"
                        )
                )
        );
    }
}