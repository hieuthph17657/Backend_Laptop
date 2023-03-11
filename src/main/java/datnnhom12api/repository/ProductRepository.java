package datnnhom12api.repository;

import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    @Query("SELECT c FROM ProductEntity c WHERE c.name = ?1")
    ProductEntity findByProductName(String name);

    @Query("SELECT c FROM ProductEntity c WHERE c.id = ?1")
    ProductEntity findByProductID(Long id);

    @Query("SELECT new SumProductDTO(sum(c.quantity))FROM ProductEntity c")
    SumProductDTO sumProduct();

    @Query("SELECT p FROM ProductEntity p where p.pn = ?2 and p.status = ?3 and p.price >= ?4 and p.price <= ?5 and (p.name like %?1% or" +
            " p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyAll(
            String searchProductKey, String searchPn, String searchStatus,
            Double searchPrice, Double endPrice, Specification<ProductEntity> specifications, Pageable pageable
    );

    @Query("SELECT p FROM ProductEntity p where p.pn = ?2 and p.status = ?3 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontPrice(String searchProductKey, String searchPn, String searchStatus, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.pn = ?2 and p.price >= ?3 and p.price <= ?4 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontStatus(String searchProductKey, String searchPn, Double searchPrice, Double endPrice, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.status = ?2 and p.price >= ?3 and p.price <= ?4 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontImei(String searchProductKey, String searchStatus, Double searchPrice, Double endPrice, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.pn = ?2 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontPriceAndStatus(String searchProductKey, String searchPn, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.status = ?2 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontPriceAndImei(String searchProductKey, String searchStatus, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.price >= ?2 and p.price <= ?3 and (p.name like %?1% or p.manufacture.name like %?1%)")
    Page<ProductEntity> findProductByKeyDontStatusAndImei(String searchProductKey, Double searchPrice, Double endPrice, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.price >= ?1 and p.price <= ?2 and p.pn = ?3")
    Page<ProductEntity> findProductByPriceAndImei(Double searchPrice, Double endPrice, String searchPn, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.price >= ?1 and p.price <= ?2 and p.status = ?3")
    Page<ProductEntity> findProductByPriceAndStatus(Double searchPrice, Double endPrice, String status, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.pn = ?1 and p.status = ?2")
    Page<ProductEntity> findProductByImeiAndStatus(String searchPn, String status, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where (p.name like %?1% or p.manufacture.name like %?1%) and p.status = ?2")
    Page<ProductEntity> findProductByProductKeyAndStatus(String searchProductKey, String status, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.name like %?1% or p.manufacture.name like %?1%")
    Page<ProductEntity> findProductByKeyDontPriceAndStatusAndImei(String searchProductKey, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p where p.price >= ?1 and p.price <= ?2")
    Page<ProductEntity> findProductByPrice(Double searchPrice, Double endPrice, Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM ProductEntity c WHERE c.discount.id IS NOT NULL AND c.status='ACTIVE'")
    List<ProductEntity> getProductWithDiscount();


    @Query("SELECT p FROM ProductEntity p inner join  ProductCategoryEntity  pc " +
            "on pc.product.id = p.id inner join CategoryEntity  c on c.id = pc.category.id where c.id = :id")
    List<ProductEntity> findProductByCategory(@Param("id") Long id);

    @Query("SELECT c FROM ProductEntity c WHERE c.status='ACTIVE'")
    Page<ProductEntity> findProductByStatus(Specification<ProductEntity> specifications, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.discount is not null")
    Page<ProductEntity> findProductsHasDiscount(Pageable pageable);
}
