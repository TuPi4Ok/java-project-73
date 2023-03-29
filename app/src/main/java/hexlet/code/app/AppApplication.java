package hexlet.code.app;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load(); // Load environment variables from .env file
        String databaseUrl = dotenv.get("ACTIVE_PROFILE");
        SpringApplication.run(AppApplication.class, args);
    }

}
