package nix.exercise.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import nix.exercise.one.domain.Phone;

/**
 * Service application. Takes responsibility to handle RPC related to {@link Phone}.
 */
@SpringBootApplication
@EnableEurekaClient
public class PhoneSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneSearchApplication.class, args);
    }
}
