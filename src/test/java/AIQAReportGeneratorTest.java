package AI;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class AIQAReportGeneratorTest {

    @Test
    public void testProfessionalReportGeneration()
            throws Exception {

        System.out.println(
                "======================================"
        );
        System.out.println(
                "       AI QA REPORT GENERATOR TEST"
        );
        System.out.println(
                "======================================"
        );

        // ========================================================
        // STEP 1
        // CREATE AI CLIENT
        // ========================================================

        AIClient aiClient =
                new MockAIClient();

        // ========================================================
        // STEP 2
        // CREATE AI QA AGENT
        // ========================================================

        AIQAAgent agent =
                new AIQAAgent(aiClient);

        // ========================================================
        // STEP 3
        // REQUIREMENT
        // ========================================================

        String requirement =
                "The user should be able to log in "
                        + "with a valid username and password.";

        // ========================================================
        // STEP 4
        // PROCESS REQUIREMENT
        // ========================================================

        AIQAAgentResult result =
                agent.processRequirement(requirement);

        // ========================================================
        // STEP 5
        // CREATE REPORT GENERATOR
        // ========================================================

        AIQAReportGenerator reportGenerator =
                new AIQAReportGenerator();

        // ========================================================
        // STEP 6
        // TEXT REPORT
        // ========================================================

        String textReportPath =
                "target/reports/ai-qa-report.txt";

        reportGenerator.generateTextReport(
                result,
                requirement,
                textReportPath
        );

        Path textPath =
                Path.of(textReportPath);

        // Verify file exists
        Assert.assertTrue(
                Files.exists(textPath),
                "Text report should be generated."
        );

        // Read report content
        String textReport =
                Files.readString(textPath);

        // ========================================================
        // BASIC REPORT VALIDATION
        // ========================================================

        Assert.assertTrue(
                textReport.contains(
                        "AI QA PROFESSIONAL REPORT"
                ),
                "Report title is missing."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Status         : PASS"
                ),
                "PASS status is missing."
        );

        Assert.assertTrue(
                textReport.contains(
                        "GENERATED TEST CASES"
                ),
                "Generated test cases section is missing."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Total Test Cases : 4"
                ),
                "Total test case count is incorrect."
        );

        // ========================================================
        // STEP 18.7
        // QA SUMMARY VALIDATION
        // ========================================================

        Assert.assertTrue(
                textReport.contains(
                        "QA SUMMARY"
                ),
                "QA Summary section is missing."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Requirement Status : PASS"
                ),
                "Requirement status is missing or incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Total Test Cases   : 4"
                ),
                "QA Summary total test case count is incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "P1 Test Cases      : 1"
                ),
                "P1 test case count is incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "P2 Test Cases      : 1"
                ),
                "P2 test case count is incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "P3 Test Cases      : 1"
                ),
                "P3 test case count is incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "P4 Test Cases      : 1"
                ),
                "P4 test case count is incorrect."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Errors             : 0"
                ),
                "Error count is incorrect."
        );

        // ========================================================
        // FULL TEST CASE VALIDATION
        // ========================================================

        Assert.assertTrue(
                textReport.contains(
                        "TC004"
                ),
                "TC004 is missing from the report."
        );

        Assert.assertTrue(
                textReport.contains(
                        "Login with SQL injection input"
                ),
                "TC004 title is missing."
        );

        Assert.assertTrue(
                textReport.contains(
                        "TC002"
                ),
                "TC002 is missing from the report."
        );

        Assert.assertTrue(
                textReport.contains(
                        "TC003"
                ),
                "TC003 is missing from the report."
        );

        Assert.assertTrue(
                textReport.contains(
                        "TC001"
                ),
                "TC001 is missing from the report."
        );

        System.out.println(
                "\n✅ QA Summary content validated successfully."
        );

        System.out.println(
                "✅ Text report content validated successfully."
        );

        // ========================================================
        // STEP 7
        // JSON REPORT
        // ========================================================

        String jsonReportPath =
                "target/reports/ai-qa-report.json";

        reportGenerator.generateJsonReport(
                result,
                requirement,
                jsonReportPath
        );

        Path jsonPath =
                Path.of(jsonReportPath);

        // Verify JSON file exists
        Assert.assertTrue(
                Files.exists(jsonPath),
                "JSON report should be generated."
        );

        // Read JSON content
        String jsonReport =
                Files.readString(jsonPath);

        // ========================================================
        // JSON VALIDATION
        // ========================================================

        Assert.assertTrue(
                jsonReport.contains(
                        "\"requirement\""
                ),
                "Requirement is missing from JSON report."
        );

        Assert.assertTrue(
                jsonReport.contains(
                        "\"status\": \"PASS\""
                ),
                "PASS status is missing from JSON report."
        );

        Assert.assertTrue(
                jsonReport.contains(
                        "\"errorCount\": 0"
                ),
                "Error count is incorrect in JSON report."
        );

        Assert.assertTrue(
                jsonReport.contains(
                        "\"testCaseCount\": 4"
                ),
                "Test case count is incorrect in JSON report."
        );

        Assert.assertTrue(
                jsonReport.contains(
                        "\"testCaseId\": \"TC004\""
                ),
                "TC004 is missing from JSON report."
        );

        Assert.assertTrue(
                jsonReport.contains(
                        "\"priority\": \"P1\""
                ),
                "P1 priority is missing from JSON report."
        );

        System.out.println(
                "✅ JSON report content validated successfully."
        );

        System.out.println(
                "\n======================================"
        );
        System.out.println(
                "      REPORT VALIDATION COMPLETE"
        );
        System.out.println(
                "======================================"
        );
    }
}