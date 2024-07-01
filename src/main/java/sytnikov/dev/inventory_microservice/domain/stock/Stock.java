package sytnikov.dev.inventory_microservice.domain.stock;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID supplier_id;

    @Column(nullable = false)
    private UUID product_id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String product_barcode;

    @Column(nullable = false)
    private int quantity;
}
