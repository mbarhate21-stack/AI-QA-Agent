package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AIRequirementRelevanceValidator {

    private final ObjectMapper objectMapper;

    public AIRequirementRelevanceValidator() {
        objectMapper = new ObjectMapper();
    }

    public List<String> validate(
            String requirement,
            String aiResponse)
            throws Exception {

        List<String> errors = new ArrayList<>();

        if (requirement == null
                || requirement.isBlank()) {

            errors.add(
                    "Requirement must not be empty."
            );

            return errors;
        }

        JsonNode root =
                objectMapper.readTree(aiResponse);

        JsonNode testCases =
                root.get("test_cases");

        if (testCases == null
                || !testCases.isArray()
                || testCases.isEmpty()) {

            errors.add(
                    "No test cases available for relevance validation."
            );

            return errors;
        }

        Set<String> requirementKeywords =
                extractKeywords(requirement);

        int relevantTestCases = 0;

        for (JsonNode testCase : testCases) {

            String testCaseId =
                    testCase.has("test_case_id")
                            ? testCase.get("test_case_id").asText()
                            : "UNKNOWN";

            String searchableText =
                    getSearchableText(testCase);

            Set<String> testCaseKeywords =
                    extractKeywords(searchableText);

            boolean relevant =
                    hasRelevantKeyword(
                            requirementKeywords,
                            testCaseKeywords
                    );

            if (relevant) {
                relevantTestCases++;
            } else {
                errors.add(
                        testCaseId
                                + ": Test case does not appear relevant to the requirement."
                );
            }
        }

        if (relevantTestCases == 0) {

            errors.add(
                    "No generated test cases are relevant to the requirement."
            );
        }

        return errors;
    }

    private String getSearchableText(JsonNode testCase) {

        StringBuilder text = new StringBuilder();

        addFieldToText(testCase, "title", text);
        addFieldToText(testCase, "precondition", text);
        addFieldToText(testCase, "test_data", text);
        addFieldToText(testCase, "expected_result", text);

        if (testCase.has("test_steps")
                && testCase.get("test_steps").isArray()) {

            for (JsonNode step :
                    testCase.get("test_steps")) {

                text.append(" ")
                        .append(step.asText());
            }
        }

        return text.toString();
    }

    private void addFieldToText(
            JsonNode testCase,
            String fieldName,
            StringBuilder text) {

        if (testCase.has(fieldName)
                && !testCase.get(fieldName).isNull()) {

            text.append(" ")
                    .append(testCase.get(fieldName).asText());
        }
    }

    private Set<String> extractKeywords(String text) {

        Set<String> keywords =
                new HashSet<>();

        String normalisedText =
                text.toLowerCase()
                        .replaceAll("[^a-zA-Z0-9 ]", " ");

        String[] words =
                normalisedText.split("\\s+");

        Set<String> ignoredWords =
                Set.of(
                        "the",
                        "a",
                        "an",
                        "user",
                        "should",
                        "be",
                        "able",
                        "to",
                        "with",
                        "and",
                        "or",
                        "is",
                        "on",
                        "page",
                        "test",
                        "case",
                        "valid",
                        "invalid",
                        "enter",
                        "click",
                        "open",
                        "field",
                        "application",
                        "successfully"
                );

        for (String word : words) {

            if (word.length() >= 4
                    && !ignoredWords.contains(word)) {

                keywords.add(word);
            }
        }

        return keywords;
    }

    private boolean hasRelevantKeyword(
            Set<String> requirementKeywords,
            Set<String> testCaseKeywords) {

        for (String requirementKeyword :
                requirementKeywords) {

            for (String testCaseKeyword :
                    testCaseKeywords) {

                if (requirementKeyword.equals(testCaseKeyword)
                        || requirementKeyword.contains(testCaseKeyword)
                        || testCaseKeyword.contains(requirementKeyword)) {

                    return true;
                }
            }
        }

        return false;
    }
}