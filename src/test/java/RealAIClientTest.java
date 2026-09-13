
import AI.RealAIClient;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
@Ignore("require openai Credit")
public class RealAIClientTest {

    @Test
    public void testRealAIClient() throws Exception {

        RealAIClient aiClient = new RealAIClient();

        String prompt = """
                Generate exactly 2 software test cases for a login page.

                Return the response in this JSON format:

                {
                  "test_cases": [
                    {
                      "test_case_id": "TC001",
                      "title": "Test title",
                      "type": "POSITIVE",
                      "expected_result": "Expected result"
                    }
                  ]
                }

                Allowed type values:
                POSITIVE, NEGATIVE, BOUNDARY, SECURITY

                Return JSON only.
                """;

        String response =
                aiClient.generateResponse(prompt);

        System.out.println();
        System.out.println("======================================");
        System.out.println("       REAL AI RESPONSE");
        System.out.println("======================================");
        System.out.println(response);
        System.out.println("======================================");

        Assert.assertNotNull(response);
        Assert.assertFalse(response.isBlank());
    }
}