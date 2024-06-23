package sytnikov.dev.inventory_microservice.domain.stock;

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
