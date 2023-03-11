package datnnhom12api.repository;

import datnnhom12api.entity.WinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WinRepository extends JpaRepository<WinEntity, Long>,
        JpaSpecificationExecutor<WinEntity> {
}
