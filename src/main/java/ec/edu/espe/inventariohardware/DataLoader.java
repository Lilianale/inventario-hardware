package ec.edu.espe.inventariohardware;

import ec.edu.espe.inventariohardware.entity.Categoria;
import ec.edu.espe.inventariohardware.entity.Estado;
import ec.edu.espe.inventariohardware.entity.HardwareEntity;
import ec.edu.espe.inventariohardware.repository.HardwareRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final HardwareRepository repository;
    private final Random random = new Random();

    public DataLoader(HardwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        // Evita duplicar datos cada vez que corras el proyecto
        if (repository.count() > 0) {
            return;
        }

        for (int i = 1; i <= 10000; i++) {

            HardwareEntity h = new HardwareEntity();

            h.setModelo("HW-" + i);

            h.setCategoria(
                    Categoria.values()[random.nextInt(Categoria.values().length)]
            );

            h.setEstado(
                    Estado.values()[random.nextInt(Estado.values().length)]
            );

            h.setPrecio(
                    BigDecimal.valueOf(500 + random.nextInt(4500))
            );

            h.setFechaCompra(
                    LocalDate.now().minusDays(random.nextInt(365 * 6))
            );

            repository.save(h);
        }

        System.out.println("✔ 10.000 registros generados correctamente");
    }
}