package datnnhom12api.repository;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.InformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long>, JpaSpecificationExecutor<DiscountEntity> {
    @Query("SELECT c FROM DiscountEntity c WHERE c.id = ?1")
    DiscountEntity findByIdDiscount(Long id);
    @Query("SELECT c FROM DiscountEntity c WHERE (c.startDate BETWEEN ?1 AND ?2) AND c.endDate BETWEEN ?1 AND ?2 ")
    Page<DiscountEntity> betweenDate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
