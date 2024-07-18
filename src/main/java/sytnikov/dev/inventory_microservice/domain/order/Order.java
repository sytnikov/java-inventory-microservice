package sytnikov.dev.inventory_microservice.domain.order;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sytnikov.dev.inventory_microservice.domain.orderItem.OrderItem;
import sytnikov.dev.inventory_microservice.domain.supplier.Supplier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"order\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Transient
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @DateTimeFormat
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}