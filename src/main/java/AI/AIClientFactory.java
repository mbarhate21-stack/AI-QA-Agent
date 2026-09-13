package AI;

public class AIClientFactory {

    public static AIClient createClient() {

        String provider =
                System.getenv("AI_PROVIDER");

        if (provider == null
                || provider.isBlank()) {

            provider = "MOCK";
        }

        return createClient(provider);
    }

    public static AIClient createClient(
            String provider) {

        if (provider == null
                || provider.isBlank()) {

            provider = "MOCK";
        }

        return switch (provider.toUpperCase()) {

            case "OPENAI" ->
                    new RealAIClient();

            case "DEEPSEEK" ->
                    new DeepSeekAIClient();

            case "MOCK" ->
                    new MockAIClient();

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported AI provider: "
                                    + provider
                    );
        };
    }
}