package nix.exercise.two.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * H2 does not like table named as "order" :).
 */
@Data
@Entity
public class OrderUnit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDate dateCreated;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orderUnit", cascade = REMOVE)
    private List<OrderDetail> orderDetails;

    private BigDecimal totalPrice;
}
