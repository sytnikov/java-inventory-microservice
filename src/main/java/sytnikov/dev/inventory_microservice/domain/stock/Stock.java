package sytnikov.dev.inventory_microservice.domain.stock;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.time.LocalDateTime;
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

    @Column(nullable = false, columnDefinition = "VARCHAR(100)", unique = true)
    private String productBarcode;

    @Column(nullable = false, columnDefinition = "NUMERIC")
    private int quantity;

    @ManyToOne
    @JoinColumn
    private Supplier supplier;

    @DateTimeFormat
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
