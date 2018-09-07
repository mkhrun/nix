package nix.exercise.two.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import nix.exercise.two.domain.dto.OrderDto;

@RestController
public class OrderController {
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @PostMapping("/order/new")
    public ResponseEntity<Void> addOrder(final OrderDto orderDto) {
        Application application = eurekaClient.getApplication("PhoneSearchApplication");

        final StringBuilder serviceUrl = new StringBuilder();

        application.getInstances()
                   .stream()
                   .findFirst()
                   .ifPresent(instanceInfo -> serviceUrl.append(String.format("http://%s:%s/", instanceInfo.getIPAddr(), instanceInfo.getPort())));

        System.out.println(serviceUrl + "/phones/all");

        return ResponseEntity.noContent().build();
    }
}
