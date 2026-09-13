package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AIQualityValidator {

    private final ObjectMapper objectMapper;

    public AIQualityValidator() {
        objectMapper = new ObjectMapper();
    }

    public List<String> validate(String aiResponse)
            throws Exception {

        List<String> errors = new ArrayList<>();

        JsonNode root =
                objectMapper.readTree(aiResponse);

        JsonNode testCases =
                root.get("test_cases");

        if (testCases == null
                || !testCases.isArray()
                || testCases.isEmpty()) {

            errors.add(
                    "No valid test cases found."
            );

            return errors;
        }

        Set<String> testCaseIds =
                new HashSet<>();

        for (JsonNode testCase : testCases) {

            String testCaseId =
                    testCase.has("test_case_id")
                            ? testCase.get("test_case_id").asText()
                            : "UNKNOWN";

            // =================================================
            // 1. Duplicate ID
            // =================================================

            if (!testCaseIds.add(testCaseId)) {

                errors.add(
                        testCaseId
                                + ": Duplicate test case ID."
                );
            }

            // =================================================
            // 2. Title quality
            // =================================================

            if (testCase.has("title")) {

                String title =
                        testCase.get("title").asText();

                if (title.isBlank()
                        || title.length() < 10) {

                    errors.add(
                            testCaseId
                                    + ": Test case title is too short."
                    );
                }

                if (title.equalsIgnoreCase("test")
                        || title.equalsIgnoreCase("login test")) {

                    errors.add(
                            testCaseId
                                    + ": Test case title is too generic."
                    );
                }
            }

            // =================================================
            // 3. Expected result quality
            // =================================================

            if (testCase.has("expected_result")) {

                String expectedResult =
                        testCase.get("expected_result").asText();

                if (expectedResult.isBlank()
                        || expectedResult.length() < 15) {

                    errors.add(
                            testCaseId
                                    + ": Expected result is too short."
                    );
                }

                if (expectedResult.equalsIgnoreCase(
                        "it should work")
                        || expectedResult.equalsIgnoreCase(
                        "test should pass")) {

                    errors.add(
                            testCaseId
                                    + ": Expected result is too generic."
                    );
                }
            }

            // =================================================
            // 4. Precondition quality
            // =================================================

            if (!testCase.has("precondition")
                    || testCase.get("precondition").asText().isBlank()) {

                errors.add(
                        testCaseId
                                + ": Precondition is missing or empty."
                );
            }

            // =================================================
            // 5. Test steps quality
            // =================================================

            if (!testCase.has("test_steps")
                    || !testCase.get("test_steps").isArray()) {

                errors.add(
                        testCaseId
                                + ": Test steps must be an array."
                );

            } else {

                JsonNode steps =
                        testCase.get("test_steps");

                if (steps.isEmpty()) {

                    errors.add(
                            testCaseId
                                    + ": Test steps must not be empty."
                    );
                }

                if (steps.size() < 2) {

                    errors.add(
                            testCaseId
                                    + ": Test case should contain at least 2 test steps."
                    );
                }

                for (JsonNode step : steps) {

                    if (step.asText().isBlank()) {

                        errors.add(
                                testCaseId
                                        + ": Test step must not be empty."
                        );
                    }
                }
            }

            // =================================================
            // 6. Test data quality
            // =================================================

            if (!testCase.has("test_data")
                    || testCase.get("test_data").asText().isBlank()) {

                errors.add(
                        testCaseId
                                + ": Test data is missing or empty."
                );
            }
        }

        return errors;
    }
}