

import AI.AIClient;
import AI.AIQAAgent;
import AI.AIQAResult;
import AI.MockAIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentTest {

    @Test
    public void testAIAgentWithMockAI() throws Exception {

        AIClient aiClient =
                new MockAIClient();

        AIQAAgent agent =
                new AIQAAgent(aiClient);

        String prompt = """
                Generate software test cases
                for a login page.
                """;

        AIQAResult result =
                agent.run(prompt);

        Assert.assertTrue(
                result.isPassed(),
                "AI QA validation failed: "
                        + result.getErrors()
        );
    }
}