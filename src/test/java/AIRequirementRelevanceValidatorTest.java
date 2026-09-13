import AI.AIRequirementRelevanceValidator;
import AI.MockAIClient;
import AI.AIClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AIRequirementRelevanceValidatorTest {

    @Test
    public void loginRequirementShouldBeRelevant()
            throws Exception {

        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate login test cases"
                );

        AIRequirementRelevanceValidator validator =
                new AIRequirementRelevanceValidator();

        List<String> errors =
                validator.validate(
                        "User should be able to login with valid username and password",
                        aiResponse
                );

        System.out.println(
                "\n========== LOGIN RELEVANCE TEST =========="
        );

        for (String error : errors) {
            System.out.println("❌ " + error);
        }

        Assert.assertTrue(
                errors.isEmpty(),
                "Login test cases should be relevant."
        );
    }

    @Test
    public void paymentRequirementShouldNotMatchLoginCases()
            throws Exception {

        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate payment test cases"
                );

        AIRequirementRelevanceValidator validator =
                new AIRequirementRelevanceValidator();

        List<String> errors =
                validator.validate(
                        "User should be able to make a payment",
                        aiResponse
                );

        System.out.println(
                "\n========== PAYMENT RELEVANCE TEST =========="
        );

        for (String error : errors) {
            System.out.println("❌ " + error);
        }

        Assert.assertFalse(
                errors.isEmpty(),
                "Login test cases should not match a payment requirement."
        );
    }

    @Test
    public void registrationRequirementShouldNotMatchLoginCases()
            throws Exception {

        AIClient aiClient =
                new MockAIClient();

        String aiResponse =
                aiClient.generateResponse(
                        "Generate registration test cases"
                );

        AIRequirementRelevanceValidator validator =
                new AIRequirementRelevanceValidator();

        List<String> errors =
                validator.validate(
                        "User should be able to register a new account",
                        aiResponse
                );

        System.out.println(
                "\n========== REGISTRATION RELEVANCE TEST =========="
        );

        for (String error : errors) {
            System.out.println("❌ " + error);
        }

        Assert.assertFalse(
                errors.isEmpty(),
                "Login test cases should not match a registration requirement."
        );
    }
}