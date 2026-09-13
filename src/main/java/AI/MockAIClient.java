package AI;

public class MockAIClient implements AIClient {

    @Override
    public String generateResponse(String prompt)
            throws Exception {

        System.out.println(
                "Mock AI Client is generating response..."
        );

        return """
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
            },
            {
              "test_case_id": "TC002",
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
              "expected_result": "User should see an authentication error."
            },
            {
              "test_case_id": "TC003",
              "title": "Login with empty username",
              "type": "BOUNDARY",
              "precondition": "User is on the login page.",
              "test_steps": [
                "Open the login page",
                "Leave the username field empty",
                "Enter a valid password",
                "Click the Login button"
              ],
              "test_data": "Username: empty, Password: Test@123",
              "expected_result": "User should see a username validation message."
            },
            {
              "test_case_id": "TC004",
              "title": "Login with SQL injection input",
              "type": "SECURITY",
              "precondition": "User is on the login page.",
              "test_steps": [
                "Open the login page",
                "Enter SQL injection input in the username field",
                "Enter any password",
                "Click the Login button"
              ],
              "test_data": "Username: ' OR '1'='1, Password: Test@123",
              "expected_result": "Application should reject malicious input and prevent unauthorised access."
            }
          ]
        }
        """;
    }
}