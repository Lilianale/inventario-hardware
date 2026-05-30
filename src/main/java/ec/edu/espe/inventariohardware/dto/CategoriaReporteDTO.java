package ec.edu.espe.inventariohardware.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoriaReporteDTO {

    private BigDecimal valorTotal;

    private Double promedioPrecio;

    private String equipoMasCaro;
}