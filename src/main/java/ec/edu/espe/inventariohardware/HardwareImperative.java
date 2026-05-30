package ec.edu.espe.inventariohardware;

import ec.edu.espe.inventariohardware.entity.Categoria;
import ec.edu.espe.inventariohardware.entity.Estado;
import ec.edu.espe.inventariohardware.entity.HardwareEntity;
import ec.edu.espe.inventariohardware.repository.HardwareRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class HardwareImperative {

    private final HardwareRepository repository;

    public HardwareImperative(HardwareRepository repository) {
        this.repository = repository;
        runImperative();
    }

    public void runImperative() {

        List<HardwareEntity> hardwareList = repository.findAll();

        // Contadores
        int laptops = 0, pcs = 0, servidores = 0;
        BigDecimal precioTotal = BigDecimal.ZERO;

        for (HardwareEntity hw : hardwareList) {

            // Contar según categoría
            if (hw.getCategoria() == Categoria.LAPTOP) laptops++;
            else if (hw.getCategoria() == Categoria.PC) pcs++;
            else if (hw.getCategoria() == Categoria.SERVIDOR) servidores++;

            // Acumular precio total
            if (hw.getPrecio() != null) {
                precioTotal = precioTotal.add(hw.getPrecio());
            }
        }

        System.out.println("=== IMPERATIVO ===");
        System.out.println("Laptops: " + laptops);
        System.out.println("PCs: " + pcs);
        System.out.println("Servidores: " + servidores);
        System.out.println("Precio total: " + precioTotal);
        System.out.println("Promedio precio: " + precioTotal.divide(BigDecimal.valueOf(hardwareList.size()), 2, BigDecimal.ROUND_HALF_UP));
    }
}