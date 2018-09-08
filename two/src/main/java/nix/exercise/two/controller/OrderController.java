package nix.exercise.two.controller;

import java.math.BigDecimal;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import nix.exercise.two.PhoneOrderApplication;
import nix.exercise.two.domain.dto.OrderDto;
import nix.exercise.two.domain.dto.OrderedPhone;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

/**
 * RPC controller accepts API calls within {@link PhoneOrderApplication}.
 */
@RestController
public class OrderController {
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/order/new")
    public OrderDto addOrder(OrderDto orderDto, UriComponentsBuilder builder) {
        return ofNullable(eurekaClient.getApplication("PhoneSearchApplication"))
                    .map(application -> {
                        application.getInstances()
                                   .stream()
                                   .findFirst()
                                   .ifPresent(instanceInfo -> builder.scheme("http")
                                                                     .host(instanceInfo.getIPAddr())
                                                                     .port(instanceInfo.getPort())
                                                                     .path("phone"));

                        var phoneUrl = builder.build().toString();

                        orderDto.getPhones().stream()
                                .peek(orderedPhone -> ofNullable(restTemplate.getForEntity(phoneUrl + "/" + orderedPhone.getArticle(),
                                                                                           BigDecimal.class).getBody())
                                            .ifPresent(orderedPhone::setPrice))
                                .filter(orderedPhone -> nonNull(orderedPhone.getPrice()))
                                .map(OrderedPhone::getPrice)
                                .reduce(BigDecimal::add)
                                .ifPresent(orderDto::setTotalPrice);

                        return orderDto;
                    })
                    .orElse(null);
    }
}
