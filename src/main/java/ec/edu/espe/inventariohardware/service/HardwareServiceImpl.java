package ec.edu.espe.inventariohardware.service;

import ec.edu.espe.inventariohardware.dto.CategoriaReporteDTO;
import ec.edu.espe.inventariohardware.entity.HardwareEntity;
import ec.edu.espe.inventariohardware.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository repository;

    public HardwareServiceImpl(HardwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> reporteImperativo() {

        long inicio = System.currentTimeMillis();

        List<HardwareEntity> lista = repository.findAll();

        Map<String, CategoriaReporteDTO> mapa = new HashMap<>();
        Map<String, Integer> contador = new HashMap<>();

        int index = 0;

        for (HardwareEntity hw : lista) {

            if (index < 500) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            index++;

            String cat = hw.getCategoria().name();

            if (!mapa.containsKey(cat)) {
                CategoriaReporteDTO dto = new CategoriaReporteDTO();
                dto.setValorTotal(BigDecimal.ZERO);
                dto.setEquipoMasCaro(hw.getModelo());
                dto.setPromedioPrecio(0.0);
                mapa.put(cat, dto);
                contador.put(cat, 0);
            }

            CategoriaReporteDTO dto = mapa.get(cat);

            dto.setValorTotal(dto.getValorTotal().add(hw.getPrecio()));
            contador.put(cat, contador.get(cat) + 1);

            HardwareEntity masCaro = lista.stream()
                    .filter(h -> h.getCategoria().name().equals(cat))
                    .max(Comparator.comparing(HardwareEntity::getPrecio))
                    .orElse(hw);

            dto.setEquipoMasCaro(masCaro.getModelo());
        }

        for (String cat : mapa.keySet()) {
            CategoriaReporteDTO dto = mapa.get(cat);
            int count = contador.get(cat);
            dto.setPromedioPrecio(dto.getValorTotal().doubleValue() / count);
        }

        long fin = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<>();
        response.put("datos", mapa);
        response.put("tiempoMs", fin - inicio);

        return response;
    }

    @Override
    public Map<String, Object> reporteFuncional() {

        long inicio = System.currentTimeMillis();

        List<HardwareEntity> lista = repository.findAll();

        AtomicInteger index = new AtomicInteger(0);

        Map<String, CategoriaReporteDTO> categorias = lista.stream()
                .peek(h -> {
                    if (index.getAndIncrement() < 500) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                })
                .collect(Collectors.groupingBy(
                        h -> h.getCategoria().name(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                items -> {

                                    CategoriaReporteDTO dto = new CategoriaReporteDTO();

                                    BigDecimal total = items.stream()
                                            .map(HardwareEntity::getPrecio)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                                    dto.setValorTotal(total);

                                    dto.setPromedioPrecio(
                                            total.doubleValue() / items.size()
                                    );

                                    dto.setEquipoMasCaro(
                                            items.stream()
                                                    .max(Comparator.comparing(HardwareEntity::getPrecio))
                                                    .get()
                                                    .getModelo()
                                    );

                                    return dto;
                                }
                        )
                ));

        long fin = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<>();
        response.put("datos", categorias);
        response.put("tiempoMs", fin - inicio);

        return response;
    }
}