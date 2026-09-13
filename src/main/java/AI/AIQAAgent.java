package AI;

import java.util.List;

public class AIQAAgent {

    private final AIClient aiClient;
    private final AIQAPipeline pipeline;
    private final AITestCaseParser parser;
    private final AITestCasePrioritizer prioritizer;
    private final AITestCaseGenerator generator;

    // Default constructor
    // Uses AIClientFactory to select the AI provider.
    public AIQAAgent() {

        this(
                AIClientFactory.createClient()
        );
    }

    // Existing constructor
    // Keeps support for manually providing an AI client.
    public AIQAAgent(AIClient aiClient) {

        this.aiClient = aiClient;

        this.pipeline =
                new AIQAPipeline();

        this.parser =
                new AITestCaseParser();

        this.prioritizer =
                new AITestCasePrioritizer();

        this.generator =
                new AITestCaseGenerator(
                        aiClient
                );
    }

    public AIQAResult run(String prompt)
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("          AI QA AGENT STARTED");
        System.out.println("======================================");

        String aiResponse =
                aiClient.generateResponse(prompt);

        System.out.println();
        System.out.println("AI Response:");
        System.out.println(aiResponse);

        AIQAResult result =
                pipeline.validate(aiResponse);

        AIQAReport report =
                new AIQAReport(
                        "AI_AGENT_001",
                        result
                );

        report.printReport();

        return result;
    }

    public List<AITestCase> generateTestCases(
            String prompt)
            throws Exception {

        String aiResponse =
                aiClient.generateResponse(prompt);

        AIQAResult result =
                pipeline.validate(aiResponse);

        if (!result.isPassed()) {

            throw new IllegalStateException(
                    "AI response failed validation: "
                            + result.getErrors()
            );
        }

        List<AITestCase> testCases =
                parser.parse(aiResponse);

        return prioritizer.prioritize(
                testCases
        );
    }

    public AIQAAgentResult processRequirement(
            String requirement)
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("     REQUIREMENT PROCESSING STARTED");
        System.out.println("======================================");

        System.out.println();
        System.out.println("Requirement:");
        System.out.println(requirement);

        if (requirement == null
                || requirement.isBlank()) {

            AIQAResult failedResult =
                    new AIQAResult(
                            false,
                            List.of(
                                    "Requirement must not be empty."
                            )
                    );

            AIQAReport report =
                    new AIQAReport(
                            "AI_AGENT_REQUIREMENT_001",
                            failedResult
                    );

            report.printReport();

            return new AIQAAgentResult(
                    failedResult,
                    List.of()
            );
        }

        String aiResponse =
                generator.generateTestCases(
                        requirement
                );

        System.out.println();
        System.out.println("Generated AI Response:");
        System.out.println(aiResponse);

        AIQAResult validationResult =
                pipeline.validate(
                        requirement,
                        aiResponse
                );

        AIQAReport report =
                new AIQAReport(
                        "AI_AGENT_REQUIREMENT_001",
                        validationResult
                );

        report.printReport();

        if (!validationResult.isPassed()) {

            System.out.println();
            System.out.println(
                    "Requirement processing failed."
            );

            return new AIQAAgentResult(
                    validationResult,
                    List.of()
            );
        }

        List<AITestCase> testCases =
                parser.parse(aiResponse);

        List<AITestCase> prioritizedTestCases =
                prioritizer.prioritize(
                        testCases
                );

        System.out.println();
        System.out.println("======================================");
        System.out.println("       PRIORITIZED TEST CASES");
        System.out.println("======================================");

        for (AITestCase testCase :
                prioritizedTestCases) {

            System.out.println(
                    prioritizer.getPriority(
                            testCase.type
                    )
                            + " - "
                            + testCase.test_case_id
                            + " - "
                            + testCase.type
                            + " - "
                            + testCase.title
            );
        }

        System.out.println();
        System.out.println(
                "✅ Requirement processing completed successfully."
        );

        return new AIQAAgentResult(
                validationResult,
                prioritizedTestCases
        );
    }

    public AIQAAgentResult runAndGenerateTestCases(
            String prompt)
            throws Exception {

        System.out.println();
        System.out.println("======================================");
        System.out.println("     AI QA AGENT COMPLETE PROCESS");
        System.out.println("======================================");

        String aiResponse =
                aiClient.generateResponse(prompt);

        System.out.println();
        System.out.println("AI Response:");
        System.out.println(aiResponse);

        AIQAResult validationResult =
                pipeline.validate(aiResponse);

        AIQAReport report =
                new AIQAReport(
                        "AI_AGENT_002",
                        validationResult
                );

        report.printReport();

        if (!validationResult.isPassed()) {

            return new AIQAAgentResult(
                    validationResult,
                    List.of()
            );
        }

        List<AITestCase> testCases =
                parser.parse(aiResponse);

        List<AITestCase> prioritizedTestCases =
                prioritizer.prioritize(
                        testCases
                );

        System.out.println();
        System.out.println("======================================");
        System.out.println("       PRIORITIZED TEST CASES");
        System.out.println("======================================");

        for (AITestCase testCase :
                prioritizedTestCases) {

            System.out.println(
                    prioritizer.getPriority(
                            testCase.type
                    )
                            + " - "
                            + testCase.test_case_id
                            + " - "
                            + testCase.type
                            + " - "
                            + testCase.title
            );
        }

        return new AIQAAgentResult(
                validationResult,
                prioritizedTestCases
        );
    }
}