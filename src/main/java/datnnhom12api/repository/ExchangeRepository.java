package datnnhom12api.repository;

import datnnhom12api.entity.ExchangeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long>, JpaSpecificationExecutor<ExchangeEntity> {

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.status = ?1 AND ex.orderId.customerName like %?2% AND ex.orderId.phone = ?3")
    Page<ExchangeEntity> findByStatusAndNameAndPhone(String searchStatus, String searchName, String searchPhone, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.status = ?1 AND ex.orderId.customerName like %?2%")
    Page<ExchangeEntity> findByStatusAndName(String searchStatus, String searchName, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.status = ?1 AND ex.orderId.phone = ?2")
    Page<ExchangeEntity> findByStatusAndPhone(String searchStatus, String searchPhone, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.orderId.customerName like %?1% AND ex.orderId.phone = ?2")
    Page<ExchangeEntity> findByNameAndPhone(String searchName, String searchPhone, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.status = ?1")
    Page<ExchangeEntity> findByStatus(String searchStatus, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.orderId.customerName like %?1%")
    Page<ExchangeEntity> findByName(String searchName, Specification<ExchangeEntity> specifications, Pageable pageable);

    @Query("SELECT ex FROM ExchangeEntity ex WHERE ex.orderId.phone = ?1")
    Page<ExchangeEntity> findByPhone(String searchPhone, Specification<ExchangeEntity> specifications, Pageable pageable);
}