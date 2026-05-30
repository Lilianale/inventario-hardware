package ec.edu.espe.inventariohardware.service;

import ec.edu.espe.inventariohardware.dto.InventarioReporteDTO;

public interface HardwareService {

    InventarioReporteDTO reporteImperativo();

    InventarioReporteDTO reporteFuncional();

}