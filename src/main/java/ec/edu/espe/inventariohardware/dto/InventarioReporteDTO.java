package ec.edu.espe.inventariohardware.dto;

import lombok.Data;

import java.util.Map;

@Data
public class InventarioReporteDTO {

    private Map<String, CategoriaReporteDTO> categorias;
}