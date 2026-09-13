package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AITestCaseValidator {

    private final ObjectMapper objectMapper;

    public AITestCaseValidator() {
        objectMapper = new ObjectMapper();
    }

    public List<String> validate(String aiResponse)
            throws Exception {

        List<String> errors = new ArrayList<>();

        JsonNode root;

        // =====================================================
        // 1. Validate JSON
        // =====================================================

        try {
            root = objectMapper.readTree(aiResponse);
        } catch (Exception e) {

            errors.add("Invalid JSON response.");

            return errors;
        }

        // =====================================================
        // 2. Validate test_cases
        // =====================================================

        JsonNode testCases =
                root.get("test_cases");

        if (testCases == null) {

            errors.add(
                    "Missing required field: test_cases"
            );

            return errors;
        }

        if (!testCases.isArray()) {

            errors.add(
                    "Field test_cases must be an array."
            );

            return errors;
        }

        if (testCases.isEmpty()) {

            errors.add(
                    "test_cases array must not be empty."
            );

            return errors;
        }

        // =====================================================
        // 3. Allowed fields
        // =====================================================

        Set<String> allowedFields =
                new HashSet<>();

        allowedFields.add("test_case_id");
        allowedFields.add("title");
        allowedFields.add("type");
        allowedFields.add("precondition");
        allowedFields.add("test_steps");
        allowedFields.add("test_data");
        allowedFields.add("expected_result");

        // =====================================================
        // 4. Validate each test case
        // =====================================================

        for (JsonNode testCase : testCases) {

            // -------------------------------------------------
            // Required fields
            // -------------------------------------------------

            validateRequiredField(
                    testCase,
                    "test_case_id",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "title",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "type",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "precondition",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "test_steps",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "test_data",
                    errors
            );

            validateRequiredField(
                    testCase,
                    "expected_result",
                    errors
            );

            // -------------------------------------------------
            // Type validation
            // -------------------------------------------------

            if (testCase.has("type")) {

                String type =
                        testCase.get("type").asText();

                try {

                    TestCaseType.valueOf(type);

                } catch (IllegalArgumentException e) {

                    errors.add(
                            testCase.get("test_case_id").asText()
                                    + ": Invalid type: "
                                    + type
                    );
                }
            }

            // -------------------------------------------------
            // Test steps must be an array
            // -------------------------------------------------

            if (testCase.has("test_steps")) {

                if (!testCase.get("test_steps").isArray()) {

                    errors.add(
                            testCase.get("test_case_id").asText()
                                    + ": test_steps must be an array."
                    );
                }
            }

            // -------------------------------------------------
            // Extra field validation
            // -------------------------------------------------

            testCase.fieldNames()
                    .forEachRemaining(fieldName -> {

                        if (!allowedFields.contains(fieldName)) {

                            errors.add(
                                    testCase.get("test_case_id").asText()
                                            + ": Unexpected field: "
                                            + fieldName
                            );
                        }
                    });
        }

        return errors;
    }

    // =========================================================
    // Helper method
    // =========================================================

    private void validateRequiredField(
            JsonNode testCase,
            String fieldName,
            List<String> errors) {

        if (!testCase.has(fieldName)
                || testCase.get(fieldName).isNull()) {

            String testCaseId =
                    testCase.has("test_case_id")
                            ? testCase.get("test_case_id").asText()
                            : "UNKNOWN";

            errors.add(
                    testCaseId
                            + ": Missing required field: "
                            + fieldName
            );
        }
    }
}