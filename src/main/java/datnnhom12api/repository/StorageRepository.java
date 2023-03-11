package datnnhom12api.repository;

import datnnhom12api.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> , JpaSpecificationExecutor<StorageEntity> {
}
