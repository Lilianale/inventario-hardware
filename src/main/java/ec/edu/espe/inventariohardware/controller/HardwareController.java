package ec.edu.espe.inventariohardware.controller;

import ec.edu.espe.inventariohardware.dto.InventarioReporteDTO;
import ec.edu.espe.inventariohardware.service.HardwareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HardwareController {

    private final HardwareService service;

    public HardwareController(HardwareService service) {
        this.service = service;
    }

    @GetMapping("/reporte/imperativo")
    public InventarioReporteDTO reporteImperativo() {
        return service.reporteImperativo();
    }

    @GetMapping("/reporte/funcional")
    public InventarioReporteDTO reporteFuncional() {
        return service.reporteFuncional();
    }
}