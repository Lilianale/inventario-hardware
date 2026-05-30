package ec.edu.espe.inventariohardware;

import ec.edu.espe.inventariohardware.entity.Categoria;
import ec.edu.espe.inventariohardware.entity.HardwareEntity;
import ec.edu.espe.inventariohardware.repository.HardwareRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HardwareFunctional {

    private final HardwareRepository repository;

    public HardwareFunctional(HardwareRepository repository) {
        this.repository = repository;
        runFunctional();
    }

    public void runFunctional() {

        List<HardwareEntity> hardwareList = repository.findAll();

        // Agrupar por categoría
        Map<Categoria, Long> countByCategory = hardwareList.stream()
                .collect(Collectors.groupingBy(HardwareEntity::getCategoria, Collectors.counting()));

        // Sumar precios
        BigDecimal totalPrice = hardwareList.stream()
                .map(HardwareEntity::getPrecio)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("=== FUNCIONAL ===");
        System.out.println("Conteo por categoría: " + countByCategory);
        System.out.println("Precio total: " + totalPrice);
        System.out.println("Promedio precio: " + totalPrice.divide(BigDecimal.valueOf(hardwareList.size()), 2, BigDecimal.ROUND_HALF_UP));
    }
}