package datnnhom12api.repository;

import datnnhom12api.entity.StorageDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageDetailRepository extends JpaRepository<StorageDetailEntity, Long> , JpaSpecificationExecutor<StorageDetailEntity> {
}
