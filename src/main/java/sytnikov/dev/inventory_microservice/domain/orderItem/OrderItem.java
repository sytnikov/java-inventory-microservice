package sytnikov.dev.inventory_microservice.domain.orderItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID order_id;

    @Column(nullable = false)
    private UUID stock_id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private float unit_price;
}
