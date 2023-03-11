package datnnhom12api.repository;

import datnnhom12api.entity.ProductCategoryEntity;
import datnnhom12api.entity.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColorEntity, Long> {
    @Modifying
    @Query("DELETE FROM ProductColorEntity pc where pc.product.id = ?1")
    void deleteAllProductColorByProductId(Long id);


    @Query("SELECT c FROM ProductColorEntity c WHERE c.product.id = :id")
    List<ProductColorEntity> findByProductID(@Param("id") Long id);
}
