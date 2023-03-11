package datnnhom12api.repository;

import datnnhom12api.entity.StorageTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageTypeRepository extends JpaRepository<StorageTypeEntity, Long> , JpaSpecificationExecutor<StorageTypeEntity> {
}
