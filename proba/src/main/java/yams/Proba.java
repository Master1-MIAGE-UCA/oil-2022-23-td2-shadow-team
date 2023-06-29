package yams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.InetAddress;

@SpringBootApplication
public class Proba {

    private static final Logger logger = LogManager.getLogger(Proba.class);
    public static void main(String[] args) {
        SpringApplication.run(Proba.class, args);
    }
}