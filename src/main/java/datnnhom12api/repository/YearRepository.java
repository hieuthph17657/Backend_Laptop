package datnnhom12api.repository;


import datnnhom12api.entity.YearEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends JpaRepository<YearEntity, Long>,
        JpaSpecificationExecutor<YearEntity> {
}
