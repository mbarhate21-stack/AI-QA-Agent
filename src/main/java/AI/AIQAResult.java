package AI;

import java.util.List;

public class AIQAResult {

    private final boolean passed;
    private final List<String> errors;

    public AIQAResult(
            boolean passed,
            List<String> errors) {

        this.passed = passed;
        this.errors = errors;
    }

    public boolean isPassed() {
        return passed;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getErrorCount() {
        return errors.size();
    }
}