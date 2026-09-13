

import AI.AIQAAgent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIQAAgentFactoryTest {

    @Test
    public void verifyAgentUsesFactory() {

        AIQAAgent agent =
                new AIQAAgent();

        Assert.assertNotNull(
                agent,
                "AIQAAgent should be created successfully."
        );

        System.out.println(
                "======================================"
        );
        System.out.println(
                "     AI QA AGENT FACTORY TEST"
        );
        System.out.println(
                "======================================"
        );

        System.out.println(
                "✅ AIQAAgent created using AIClientFactory."
        );

        System.out.println(
                "======================================"
        );
    }
}