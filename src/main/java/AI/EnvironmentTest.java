package AI;

public class EnvironmentTest {

    public static void main(String[] args) {

        String apiKey = System.getenv("OPENAI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("API key NOT found");
        } else {
            System.out.println("API key found");
        }
    }
}
