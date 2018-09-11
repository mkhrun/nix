package nix.exercise.two.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderedPhone {
    private String article;
    private BigDecimal price;
}
