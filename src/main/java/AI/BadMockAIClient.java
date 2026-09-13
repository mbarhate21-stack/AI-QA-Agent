package AI;

public class BadMockAIClient implements AIClient {

    @Override
    public String generateResponse(String prompt) {

        System.out.println(
                "Bad Mock AI Client is generating invalid response..."
        );

        return """
        {
          "test_cases": [
            {
              "test_case_id": "TC001",
              "title": "Login test",
              "type": "URGENT",
              "expected_result": "test should pass"
            },
            {
              "test_case_id": "TC001",
              "title": "Login test",
              "type": "NEGATIVE",
              "expected_result": "test should pass"
            }
          ]
        }
        """;
    }
}