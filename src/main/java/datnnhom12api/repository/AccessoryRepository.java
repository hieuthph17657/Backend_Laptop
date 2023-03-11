package datnnhom12api.repository;

import datnnhom12api.entity.AccessoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Long>, JpaSpecificationExecutor<AccessoryEntity> {
}
