package datnnhom12api.repository;

import datnnhom12api.entity.ManufactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<ManufactureEntity, Long> ,
        JpaSpecificationExecutor<ManufactureEntity> {
}
