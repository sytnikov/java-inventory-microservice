package sytnikov.dev.inventory_microservice.domain.supplier;

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
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String contact_person;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String email;
}
