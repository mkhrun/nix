package nix.exercise.one.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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

    @Column(name = "price", precision = 20, scale = 2)
    private BigDecimal price;

    @Column(name = "article", unique = true, nullable = false)
    private String article;
}
