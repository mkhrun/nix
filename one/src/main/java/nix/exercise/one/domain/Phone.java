package nix.exercise.one.domain;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imgPath;
    private BigDecimal price;
    private String article;
}
