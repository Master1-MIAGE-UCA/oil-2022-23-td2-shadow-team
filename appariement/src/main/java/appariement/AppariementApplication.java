package appariement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppariementApplication {
    public static void main(String[] args) {
        // les traces sont là juste pour montrer le déroulement et le lancement
        SpringApplication.run(AppariementApplication.class, args);
    }

    @Bean
    public CommandLineRunner scriptLancement(Appariement lancement) {
        return args -> {

        };
    }

}
