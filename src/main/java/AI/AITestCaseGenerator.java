package AI;

public class AITestCaseGenerator {

    private final AIClient aiClient;

    public AITestCaseGenerator(AIClient aiClient) {
        this.aiClient = aiClient;
    }

    public String generateTestCases(String requirement)
            throws Exception {

        // Validate requirement before sending it to AI
        if (requirement == null
                || requirement.isBlank()) {

            return """
            {
              "test_cases": []
            }
            """;
        }

        String prompt = """
                Generate software test cases
                for the following requirement.

                Requirement:
                %s

                Return the response in valid JSON format.
                """.formatted(requirement);

        return aiClient.generateResponse(prompt);
    }
}