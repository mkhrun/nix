package nix.exercise.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhoneOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneOrderApplication.class, args);
    }
}
