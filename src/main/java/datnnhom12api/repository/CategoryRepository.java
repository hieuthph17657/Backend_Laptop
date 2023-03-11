package datnnhom12api.repository;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.name = ?1")
    CategoryEntity findByName(String name);
    @Query("SELECT c FROM CategoryEntity c WHERE c.id = ?1")
    CategoryEntity findByIdCate(Long id);
}
