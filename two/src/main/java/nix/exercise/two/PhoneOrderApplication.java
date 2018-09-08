package nix.exercise.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import nix.exercise.two.domain.Customer;
import nix.exercise.two.domain.OrderDetail;
import nix.exercise.two.domain.OrderUnit;

/**
 * Service application. Takes responsibility to handle RPC related to {@link OrderUnit} and sub-units ({@link Customer}, {@link OrderDetail}).
 */
@SpringBootApplication
@EnableEurekaClient
public class PhoneOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneOrderApplication.class, args);
    }
}
