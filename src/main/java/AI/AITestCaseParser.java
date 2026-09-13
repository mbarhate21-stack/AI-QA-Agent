package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class AITestCaseParser {

    private final ObjectMapper objectMapper;

    public AITestCaseParser() {
        objectMapper = new ObjectMapper();
    }

    public List<AITestCase> parse(String aiResponse)
            throws Exception {

        JsonNode root =
                objectMapper.readTree(aiResponse);

        JsonNode testCases =
                root.get("test_cases");

        List<AITestCase> result =
                new ArrayList<>();

        for (JsonNode testCase : testCases) {

            AITestCase tc = new AITestCase();

            // Test Case ID
            tc.test_case_id =
                    testCase.get("test_case_id").asText();

            // Title
            tc.title =
                    testCase.get("title").asText();

            // Type
            tc.type =
                    TestCaseType.valueOf(
                            testCase.get("type").asText()
                    );

            // Precondition
            tc.precondition =
                    testCase.get("precondition").asText();

            // Test Steps
            tc.test_steps =
                    new ArrayList<>();

            for (JsonNode step :
                    testCase.get("test_steps")) {

                tc.test_steps.add(
                        step.asText()
                );
            }

            // Test Data
            tc.test_data =
                    testCase.get("test_data").asText();

            // Expected Result
            tc.expected_result =
                    testCase.get("expected_result").asText();

            result.add(tc);
        }

        return result;
    }
}