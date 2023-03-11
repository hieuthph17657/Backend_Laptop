package datnnhom12api.repository;

import datnnhom12api.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long>, JpaSpecificationExecutor<ColorEntity> {
}
