package AI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AIQAReportGenerator {

    // ============================================================
    // STEP 18.1 + 18.4 + 18.5 + 18.6
    // GENERATE PROFESSIONAL TEXT REPORT
    // ============================================================

    public void generateTextReport(
            AIQAAgentResult result,
            String requirement,
            String filePath
    ) throws IOException {

        StringBuilder report = new StringBuilder();

        report.append("==============================================\n");
        report.append("           AI QA PROFESSIONAL REPORT\n");
        report.append("==============================================\n\n");

        // ========================================================
        // EXECUTION TIME
        // ========================================================

        report.append("Execution Time : ")
                .append(
                        LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm:ss"
                                )
                        )
                )
                .append("\n");

        // ========================================================
        // REQUIREMENT
        // ========================================================

        report.append("Requirement    : ")
                .append(requirement)
                .append("\n");

        // ========================================================
        // STATUS
        // ========================================================

        report.append("Status         : ")
                .append(result.isPassed() ? "PASS" : "FAIL")
                .append("\n");

        // ========================================================
        // ERROR COUNT
        // ========================================================

        report.append("Error Count    : ")
                .append(result.getErrorCount())
                .append("\n\n");

        // ========================================================
        // ERRORS
        // ========================================================

        report.append("==============================================\n");
        report.append("                    ERRORS\n");
        report.append("==============================================\n");

        if (result.getErrors().isEmpty()) {

            report.append("No errors found.\n");

        } else {

            for (String error : result.getErrors()) {

                report.append("- ")
                        .append(error)
                        .append("\n");
            }
        }

        report.append("\n");

        // ========================================================
        // STEP 18.6
        // QA SUMMARY
        // ========================================================

        report.append("==============================================\n");
        report.append("                  QA SUMMARY\n");
        report.append("==============================================\n\n");

        report.append("Requirement Status : ")
                .append(result.isPassed() ? "PASS" : "FAIL")
                .append("\n");

        report.append("Total Test Cases   : ")
                .append(result.getTestCases().size())
                .append("\n");

        // Count priorities by position
        int p1Count = 0;
        int p2Count = 0;
        int p3Count = 0;
        int p4Count = 0;

        for (int i = 0;
             i < result.getTestCases().size();
             i++) {

            switch (i) {

                case 0:
                    p1Count++;
                    break;

                case 1:
                    p2Count++;
                    break;

                case 2:
                    p3Count++;
                    break;

                case 3:
                    p4Count++;
                    break;

                default:
                    break;
            }
        }

        report.append("P1 Test Cases      : ")
                .append(p1Count)
                .append("\n");

        report.append("P2 Test Cases      : ")
                .append(p2Count)
                .append("\n");

        report.append("P3 Test Cases      : ")
                .append(p3Count)
                .append("\n");

        report.append("P4 Test Cases      : ")
                .append(p4Count)
                .append("\n");

        report.append("Errors             : ")
                .append(result.getErrorCount())
                .append("\n");

        report.append("\n");

        // ========================================================
        // GENERATED TEST CASES
        // ========================================================

        report.append("==============================================\n");
        report.append("             GENERATED TEST CASES\n");
        report.append("==============================================\n");

        report.append("Total Test Cases : ")
                .append(result.getTestCases().size())
                .append("\n\n");

        if (result.getTestCases().isEmpty()) {

            report.append("No test cases generated.\n");

        } else {

            report.append(
                    "Prioritised Test Cases:\n\n"
            );

            // ====================================================
            // STEP 18.5
            // FULL TEST CASE DETAILS
            // ====================================================

            for (int i = 0;
                 i < result.getTestCases().size();
                 i++) {

                AITestCase testCase =
                        result.getTestCases().get(i);

                String priority =
                        "P" + (i + 1);

                // ------------------------------------------------
                // TEST CASE HEADER
                // ------------------------------------------------

                report.append("----------------------------------------------\n");

                report.append(priority)
                        .append(" - ")
                        .append(testCase.test_case_id)
                        .append(" - ")
                        .append(testCase.type)
                        .append("\n");

                // ------------------------------------------------
                // TITLE
                // ------------------------------------------------

                report.append("Title           : ")
                        .append(testCase.title)
                        .append("\n");

                // ------------------------------------------------
                // PRECONDITION
                // ------------------------------------------------

                report.append("Precondition    : ")
                        .append(testCase.precondition)
                        .append("\n\n");

                // ------------------------------------------------
                // TEST STEPS
                // ------------------------------------------------

                report.append("Test Steps:\n");

                if (testCase.test_steps == null
                        || testCase.test_steps.isEmpty()) {

                    report.append("  No test steps available.\n");

                } else {

                    for (int step = 0;
                         step < testCase.test_steps.size();
                         step++) {

                        report.append("  ")
                                .append(step + 1)
                                .append(". ")
                                .append(
                                        testCase.test_steps.get(step)
                                )
                                .append("\n");
                    }
                }

                report.append("\n");

                // ------------------------------------------------
                // TEST DATA
                // ------------------------------------------------

                report.append("Test Data       : ")
                        .append(testCase.test_data)
                        .append("\n");

                // ------------------------------------------------
                // EXPECTED RESULT
                // ------------------------------------------------

                report.append("Expected Result : ")
                        .append(testCase.expected_result)
                        .append("\n\n");
            }

            report.append("----------------------------------------------\n");
        }

        report.append("\n");

        // ========================================================
        // END OF REPORT
        // ========================================================

        report.append("==============================================\n");
        report.append("               END OF REPORT\n");
        report.append("==============================================\n");

        // ========================================================
        // CREATE DIRECTORY
        // ========================================================

        Path path = Path.of(filePath);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        // ========================================================
        // WRITE REPORT
        // ========================================================

        Files.writeString(
                path,
                report.toString()
        );
    }


    // ============================================================
    // STEP 18.2 + 18.3
    // GENERATE JSON REPORT
    // ============================================================

    public void generateJsonReport(
            AIQAAgentResult result,
            String requirement,
            String filePath
    ) throws IOException {

        StringBuilder json = new StringBuilder();

        json.append("{\n");

        // Requirement
        json.append("  \"requirement\": \"")
                .append(escapeJson(requirement))
                .append("\",\n");

        // Status
        json.append("  \"status\": \"")
                .append(result.isPassed() ? "PASS" : "FAIL")
                .append("\",\n");

        // Error count
        json.append("  \"errorCount\": ")
                .append(result.getErrorCount())
                .append(",\n");

        // Errors
        json.append("  \"errors\": [\n");

        for (int i = 0;
             i < result.getErrors().size();
             i++) {

            json.append("    \"")
                    .append(
                            escapeJson(
                                    result.getErrors().get(i)
                            )
                    )
                    .append("\"");

            if (i < result.getErrors().size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ],\n");

        // Test case count
        json.append("  \"testCaseCount\": ")
                .append(result.getTestCases().size())
                .append(",\n");

        // Test cases
        json.append("  \"testCases\": [\n");

        for (int i = 0;
             i < result.getTestCases().size();
             i++) {

            AITestCase testCase =
                    result.getTestCases().get(i);

            json.append("    {\n");

            // Priority
            json.append("      \"priority\": \"")
                    .append("P")
                    .append(i + 1)
                    .append("\",\n");

            // Test Case ID
            json.append("      \"testCaseId\": \"")
                    .append(
                            escapeJson(
                                    testCase.test_case_id
                            )
                    )
                    .append("\",\n");

            // Type
            json.append("      \"type\": \"")
                    .append(testCase.type)
                    .append("\",\n");

            // Title
            json.append("      \"title\": \"")
                    .append(
                            escapeJson(
                                    testCase.title
                            )
                    )
                    .append("\"\n");

            json.append("    }");

            if (i < result.getTestCases().size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("  ]\n");

        json.append("}\n");

        // Create directory if necessary
        Path path = Path.of(filePath);

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        // Write JSON report
        Files.writeString(
                path,
                json.toString()
        );
    }


    // ============================================================
    // JSON ESCAPING HELPER
    // ============================================================

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}