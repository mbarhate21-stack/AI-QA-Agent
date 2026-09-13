package AI;

import java.util.List;

public class AIQAAgentResult {

    private final AIQAResult validationResult;
    private final List<AITestCase> testCases;

    public AIQAAgentResult(
            AIQAResult validationResult,
            List<AITestCase> testCases) {

        this.validationResult = validationResult;
        this.testCases = testCases;
    }

    public AIQAResult getValidationResult() {
        return validationResult;
    }

    public List<AITestCase> getTestCases() {
        return testCases;
    }

    public boolean isPassed() {
        return validationResult.isPassed();
    }

    public List<String> getErrors() {
        return validationResult.getErrors();
    }

    public int getErrorCount() {
        return validationResult.getErrorCount();
    }
}