package datnnhom12api.repository;

import datnnhom12api.entity.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Long>, JpaSpecificationExecutor<OriginEntity> {
}
