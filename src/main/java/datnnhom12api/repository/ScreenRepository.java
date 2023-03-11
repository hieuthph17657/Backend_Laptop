package datnnhom12api.repository;
import datnnhom12api.entity.ScreenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenEntity, Long>,
        JpaSpecificationExecutor<ScreenEntity> {
}
