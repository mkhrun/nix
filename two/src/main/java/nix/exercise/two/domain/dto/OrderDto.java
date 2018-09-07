package nix.exercise.two.domain.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private List<String> phoneArticles;
}
