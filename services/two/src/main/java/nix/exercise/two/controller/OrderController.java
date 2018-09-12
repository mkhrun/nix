package nix.exercise.two.controller;

import java.math.BigDecimal;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import nix.exercise.two.PhoneOrderApplication;
import nix.exercise.two.domain.dto.OrderDto;
import nix.exercise.two.domain.dto.OrderedPhone;
import nix.exercise.two.domain.exception.IllegalPhoneArticleException;
import nix.exercise.two.domain.exception.PhoneCatalogServiceException;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

/**
 * RPC controller accepts API calls within {@link PhoneOrderApplication}.
 */
@RestController
public class OrderController {
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/order/new")
    public OrderDto addOrder(OrderDto orderDto, UriComponentsBuilder builder) {
        return ofNullable(eurekaClient.getApplication("PhoneSearchApplication"))
                    .map(application -> application.getInstances()
                                                   .stream()
                                                   .findFirst()
                                                   .orElse(null))
                    .map(instanceInfo -> builder.scheme("http")
                                                .host(instanceInfo.getIPAddr())
                                                .port(instanceInfo.getPort())
                                                .path("phone/")
                                                .build()
                                                .toString())
                    .map(phoneUrl -> {
                        orderDto.getPhones()
                                .stream()
                                .peek(orderedPhone -> setPhonePrice(orderedPhone, phoneUrl))
                                .filter(orderedPhone -> nonNull(orderedPhone.getPrice()))
                                .map(OrderedPhone::getPrice)
                                .reduce(BigDecimal::add)
                                .ifPresent(orderDto::setTotalPrice);

                        return orderDto;
                    })
                    .orElseThrow(() -> new PhoneCatalogServiceException("Service is down"));
    }

    private void setPhonePrice(OrderedPhone orderedPhone, String phoneCatalogServiceUrl) {
        BigDecimal phonePrice =
                    ofNullable(retrievePhonePrice(phoneCatalogServiceUrl, orderedPhone.getArticle())).orElseThrow(IllegalPhoneArticleException::new);
        orderedPhone.setPrice(phonePrice);
    }

    private BigDecimal retrievePhonePrice(String phoneCatalogServiceUrl, String phoneArticle) {
        return restTemplate.getForEntity(phoneCatalogServiceUrl + phoneArticle, BigDecimal.class).getBody();
    }
}
