package datnnhom12api.repository;


import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.ProductCategoryEntity;
import datnnhom12api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long>,
        JpaSpecificationExecutor<ProductCategoryEntity> {

    @Modifying
    @Query("DELETE FROM ProductCategoryEntity pc where pc.product.id = :id")
    void deleteAllProductCategoryByProductId(@Param("id") Long id);

    @Query("SELECT c FROM ProductCategoryEntity c WHERE c.product.id = :id")
    List<ProductCategoryEntity> findByProductID(@Param("id") Long id);

}
