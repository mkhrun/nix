package nix.exercise.two.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private List<OrderedPhone> phones;
    private BigDecimal totalPrice;
}
