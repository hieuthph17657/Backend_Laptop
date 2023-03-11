package datnnhom12api.repository;

import datnnhom12api.entity.BatteryChargerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BatteryChargerRepository extends JpaRepository<BatteryChargerEntity, Long>, JpaSpecificationExecutor<BatteryChargerEntity> {
}
