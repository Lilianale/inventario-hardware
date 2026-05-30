package ec.edu.espe.inventariohardware.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hardware")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardwareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private BigDecimal precio;

    private LocalDate fechaCompra;

    @Enumerated(EnumType.STRING)
    private Estado estado;
}