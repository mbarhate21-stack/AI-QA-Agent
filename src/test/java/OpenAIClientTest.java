import AI.AIClient;
import AI.RealAIClient;
import com.openai.client.OpenAIClientImpl;
//import com.openai.client.OpenAIClientImpl;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore("Requires OPENAI API Credits")
public class OpenAIClientTest {

    @Test
    public void testOpenAIConnection() throws  Exception {

        AIClient aiClient = new RealAIClient();


        String response =
                aiClient.generateResponse(
                        "Return exactly this text: API connection successful"
                );


        System.out.println();
        System.out.println(
                "========== OPENAI RESPONSE =========="
        );

        System.out.println(response);


        Assert.assertFalse(
                response.isBlank(),
                "OpenAI returned an empty response."
        );
    }
}