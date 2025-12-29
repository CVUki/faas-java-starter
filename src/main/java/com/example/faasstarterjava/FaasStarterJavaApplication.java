package com.example.faasstarterjava;

import com.example.faasstarterjava.model.SignupRequest;
import com.example.faasstarterjava.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class FaasStarterJavaApplication {

    private static final Logger LOG = LoggerFactory.getLogger(FaasStarterJavaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FaasStarterJavaApplication.class, args);
    }

    //Functionality of Function is to receive input and return an output
    //In this case the logic is to take a raw request, validate it and create a User object
    @Bean
    public Function<SignupRequest, User> processSignup() {
        return request -> {
            LOG.info("Processing signup for: {}", request.name());
            // Simulating business logic (e.g. generating ID)
            return new User(
                    UUID.randomUUID().toString(),
                    request.name().toUpperCase(), // Normalizing data
                    request.email(),
                    LocalDateTime.now()
            );
        };
    }

    //Functionality of Consumer is to receive input and return a void
    //In this case the logic is to send a mail upon signup
    @Bean
    public Consumer<User> notifySystem() {
        return user -> {
            LOG.info("SIDE EFFECT executed: Sending Welcome Email to {} ({})",
                    user.name(), user.email());
            // Real world: mailService.send(...)
        };
    }

    //Functionality of Supplier is to NOT receive input and return an output
    //In this case the logic is to send the status of the server
    @Bean
    public Supplier<String> systemHealth() {
        return () -> "System is UP. Server time: " + LocalDateTime.now();
    }


}
