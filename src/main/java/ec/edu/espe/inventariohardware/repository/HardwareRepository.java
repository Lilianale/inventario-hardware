package ec.edu.espe.inventariohardware.repository;

import ec.edu.espe.inventariohardware.entity.HardwareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository
        extends JpaRepository<HardwareEntity, Long> {
}
