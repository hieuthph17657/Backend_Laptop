package datnnhom12api.repository;

import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.ProcessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<ProcessorEntity, Long>, JpaSpecificationExecutor<ProcessorEntity> {
}
