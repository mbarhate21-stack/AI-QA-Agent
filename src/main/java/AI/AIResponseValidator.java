package AI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class AIResponseValidator {

    public List<String> validate(String aiResponse) throws Exception {

        List<String> errors = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode json = mapper.readTree(aiResponse);


        // 1. Required fields

        String[] requiredFields = {
                "bug_title",
                "severity",
                "priority",
                "is_valid_bug",
                "recommendation"
        };

        for (String field : requiredFields) {

            if (!json.has(field)) {
                errors.add(
                        "Missing required field: " + field
                );
            }
        }


        // 2. Severity

        if (json.has("severity")) {

            String severity =
                    json.get("severity").asText();

            if (!severity.equals("LOW") &&
                    !severity.equals("MEDIUM") &&
                    !severity.equals("HIGH") &&
                    !severity.equals("CRITICAL")) {

                errors.add(
                        "Invalid severity: " + severity
                );
            }
        }


        // 3. Priority

        if (json.has("priority")) {

            String priority =
                    json.get("priority").asText();

            if (!priority.equals("P1") &&
                    !priority.equals("P2") &&
                    !priority.equals("P3") &&
                    !priority.equals("P4")) {

                errors.add(
                        "Invalid priority: " + priority
                );
            }
        }


        // 4. is_valid_bug

        if (json.has("is_valid_bug")) {

            JsonNode validBug =
                    json.get("is_valid_bug");

            if (!validBug.isBoolean()) {

                errors.add(
                        "is_valid_bug must be boolean"
                );
            }
        }


        // 5. Extra fields

        String[] allowedFields = {
                "bug_title",
                "severity",
                "priority",
                "is_valid_bug",
                "recommendation"
        };

        json.fieldNames().forEachRemaining(field -> {

            boolean allowed = false;

            for (String allowedField : allowedFields) {

                if (field.equals(allowedField)) {
                    allowed = true;
                    break;
                }
            }

            if (!allowed) {

                errors.add(
                        "Extra field: " + field
                );
            }
        });


        return errors;
    }
}
