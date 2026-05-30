package ec.edu.espe.inventariohardware.controller;

import ec.edu.espe.inventariohardware.service.HardwareService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HardwareController {

    private final HardwareService service;

    public HardwareController(HardwareService service) {
        this.service = service;
    }

    @GetMapping("/reporte/imperativo")
    public Map<String, Object> imperativo() {
        return service.reporteImperativo();
    }

    @GetMapping("/reporte/funcional")
    public Map<String, Object> funcional() {
        return service.reporteFuncional();
    }
}