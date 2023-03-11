package datnnhom12api.repository;


import datnnhom12api.entity.AccessoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryProductRepository extends JpaRepository<AccessoryProductEntity, Long> {
    @Modifying
    @Query("DELETE FROM AccessoryProductEntity ac where ac.product.id = ?1")
    void deleteAllAccessoryProductByProductId(Long id);
}
