package AI;

public class AIQAReport {

    private final String testId;
    private final boolean passed;
    private final int errorCount;

    public AIQAReport(
            String testId,
            AIQAResult result) {

        this.testId = testId;
        this.passed = result.isPassed();
        this.errorCount = result.getErrorCount();
    }

    public void printReport() {

        System.out.println();
        System.out.println("======================================");
        System.out.println("           AI QA FINAL REPORT");
        System.out.println("======================================");

        System.out.println(
                "Test ID      : " + testId
        );

        System.out.println(
                "Status       : "
                        + (passed ? "PASS" : "FAIL")
        );

        System.out.println(
                "Error Count  : " + errorCount
        );

        System.out.println("======================================");
    }

    public boolean isPassed() {
        return passed;
    }

    public String getTestId() {
        return testId;
    }

    public int getErrorCount() {
        return errorCount;
    }
}