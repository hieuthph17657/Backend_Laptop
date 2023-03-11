package datnnhom12api.repository;

import datnnhom12api.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<RamEntity, Long>, JpaSpecificationExecutor<RamEntity> {


}
