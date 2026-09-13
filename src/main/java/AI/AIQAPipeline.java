package AI;

import java.util.ArrayList;
import java.util.List;

public class AIQAPipeline {

    private final AITestCaseValidator schemaValidator;
    private final AIQualityValidator qualityValidator;
    private final AIRequirementRelevanceValidator relevanceValidator;

    public AIQAPipeline() {

        schemaValidator =
                new AITestCaseValidator();

        qualityValidator =
                new AIQualityValidator();

        relevanceValidator =
                new AIRequirementRelevanceValidator();
    }

    public AIQAResult validate(
            String aiResponse)
            throws Exception {

        List<String> errors =
                new ArrayList<>();

        List<String> schemaErrors =
                schemaValidator.validate(aiResponse);

        if (!schemaErrors.isEmpty()) {

            errors.addAll(schemaErrors);

            return new AIQAResult(
                    false,
                    errors
            );
        }

        List<String> qualityErrors =
                qualityValidator.validate(aiResponse);

        errors.addAll(qualityErrors);

        boolean passed =
                errors.isEmpty();

        return new AIQAResult(
                passed,
                errors
        );
    }

    public AIQAResult validate(
            String requirement,
            String aiResponse)
            throws Exception {

        List<String> errors =
                new ArrayList<>();

        List<String> schemaErrors =
                schemaValidator.validate(aiResponse);

        if (!schemaErrors.isEmpty()) {

            errors.addAll(schemaErrors);

            return new AIQAResult(
                    false,
                    errors
            );
        }

        List<String> qualityErrors =
                qualityValidator.validate(aiResponse);

        errors.addAll(qualityErrors);

        if (qualityErrors.isEmpty()) {

            List<String> relevanceErrors =
                    relevanceValidator.validate(
                            requirement,
                            aiResponse
                    );

            errors.addAll(relevanceErrors);
        }

        boolean passed =
                errors.isEmpty();

        return new AIQAResult(
                passed,
                errors
        );
    }
}