package datnnhom12api.repository;

import datnnhom12api.dto.StatisticalMonthDTO;
import datnnhom12api.dto.StatisticalOrderDTO;
import datnnhom12api.dto.StatisticalProductDTO;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    @Query("SELECT o FROM OrderEntity o WHERE o.user.username=?1 ORDER BY o.id desc ")
    public List<OrderEntity> findUserName(String username);

    @Query("SELECT o FROM OrderEntity o WHERE o.phone=?1 ORDER BY o.id desc ")
    public List<OrderEntity> findPhone(String phone);

    @Query("SELECT o FROM OrderEntity o WHERE o.createdAt=?1 ORDER BY o.id desc")
    public List<OrderEntity> findByDate(LocalDateTime createdAt);




    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND (c.createdAt BETWEEN ?1 AND ?2)")
    Page<OrderEntity> betweenDate(LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1")
    Page<OrderEntity> findAllAndStatus(String searchStatus, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2%")
    Page<OrderEntity> searchNameAndStatus(String searchStatus, String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.phone = ?3")
    Page<OrderEntity> searchNameAndPhoneAndStatus(String searchStatus, String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2")
    Page<OrderEntity> searchPhoneAndStatus(String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.phone = ?3")
    Page<OrderEntity> searchPaymentAndPhoneAndStatus(String searchStatus, String searchPayment, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1")
    Page<OrderEntity> searchPhone(String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1%")
    Page<OrderEntity> searchName(String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1")
    Page<OrderEntity> searchPayment(String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2")
    Page<OrderEntity> searchPaymentAndStatus(String searchStatus, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.payment = ?3")
    Page<OrderEntity> searchNameAndPaymentAndStatus(String searchStatus, String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndStatus(String searchStatus, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndPhone(String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndStatus(String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPaymentAndStatus(String searchStatus, String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndPayment(String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndNameAndStatus(String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndName(String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4 AND (c.createdAt BETWEEN ?5 AND ?6) AND (c.createdAt BETWEEN ?5 AND ?6)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentAndName(String searchPayment, String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4")
    Page<OrderEntity> findByPhoneAndStatusAndPaymentAndName(String searchPayment, String searchName, String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentAndName(String searchPayment, String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPayment(String searchPayment, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndPayment(String searchPayment, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.customerName like %?3% AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndNameAndStatusAndPayment(String searchPayment, String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndNameAndPayment(String searchPayment, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndName(String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndName(String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2")
    Page<OrderEntity> searchNameAndPhone(String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.payment = ?2")
    Page<OrderEntity> searchNameAndPayment(String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone like %?1% AND c.payment = ?2")
    Page<OrderEntity> searchPhoneAndPayment(String searchPhone, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);




    @Query("SELECT c FROM OrderEntity c WHERE c.money = 0")
    Page<OrderEntity> findAllDontMoney(Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND (c.createdAt BETWEEN ?1 AND ?2) AND c.money = 0")
    Page<OrderEntity> betweenDateDontMoney(LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.money = 0")
    Page<OrderEntity> findAllAndStatusDontMoney(String searchStatus, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.money = 0")
    Page<OrderEntity> searchNameAndStatusDontMoney(String searchStatus, String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND c.money = 0")
    Page<OrderEntity> searchNameAndPhoneAndStatusDontMoney(String searchStatus, String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND c.money = 0")
    Page<OrderEntity> searchPhoneAndStatusDontMoney(String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.phone = ?3 AND c.money = 0")
    Page<OrderEntity> searchPaymentAndPhoneAndStatusDontMoney(String searchStatus, String searchPayment, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND c.money = 0")
    Page<OrderEntity> searchPhoneDontMoney(String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.money = 0")
    Page<OrderEntity> searchNameDontMoney(String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.money = 0")
    Page<OrderEntity> searchPaymentDontMoney(String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.money = 0")
    Page<OrderEntity> searchPaymentAndStatusDontMoney(String searchStatus, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.payment = ?3 AND c.money = 0")
    Page<OrderEntity> searchNameAndPaymentAndStatusDontMoney(String searchStatus, String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = 0")
    Page<OrderEntity> betweenDateAndStatusDontMoney(String searchStatus, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneDontMoney(String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusDontMoney(String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPaymentAndStatusDontMoney(String searchStatus, String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPaymentDontMoney(String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndNameAndStatusDontMoney(String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = 0")
    Page<OrderEntity> betweenDateAndNameDontMoney(String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4 AND (c.createdAt BETWEEN ?5 AND ?6) AND (c.createdAt BETWEEN ?5 AND ?6) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentAndNameDontMoney(String searchPayment, String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentAndNameDontMoney(String searchPayment, String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentDontMoney(String searchPayment, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentDontMoney(String searchPayment, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.customerName like %?3% AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = 0")
    Page<OrderEntity> betweenDateAndNameAndStatusAndPaymentDontMoney(String searchPayment, String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndNameAndPaymentDontMoney(String searchPayment, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndNameDontMoney(String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = 0")
    Page<OrderEntity> betweenDateAndPhoneAndNameDontMoney(String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND c.money = 0")
    Page<OrderEntity> searchNameAndPhoneDontMoney(String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.payment = ?2 AND c.money = 0")
    Page<OrderEntity> searchNameAndPaymentDontMoney(String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone like %?1% AND c.payment = ?2 AND c.money = 0")
    Page<OrderEntity> searchPhoneAndPaymentDontMoney(String searchPhone, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);



    @Query("SELECT c FROM OrderEntity c WHERE c.money = c.total AND c.money > 0")
    Page<OrderEntity> findAllFinishMoney(Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND (c.createdAt BETWEEN ?1 AND ?2) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateFinishMoney(LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> findAllAndStatusFinishMoney(String searchStatus, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameAndStatusFinishMoney(String searchStatus, String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameAndPhoneAndStatusFinishMoney(String searchStatus, String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPhoneAndStatusFinishMoney(String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.phone = ?3 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPaymentAndPhoneAndStatusFinishMoney(String searchStatus, String searchPayment, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPhoneFinishMoney(String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameFinishMoney(String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPaymentFinishMoney(String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPaymentAndStatusFinishMoney(String searchStatus, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.payment = ?3 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameAndPaymentAndStatusFinishMoney(String searchStatus, String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndStatusFinishMoney(String searchStatus, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneFinishMoney(String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusFinishMoney(String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPaymentAndStatusFinishMoney(String searchStatus, String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPaymentFinishMoney(String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndStatusFinishMoney(String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameFinishMoney(String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4 AND (c.createdAt BETWEEN ?5 AND ?6) AND (c.createdAt BETWEEN ?5 AND ?6) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentAndNameFinishMoney(String searchPayment, String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentAndNameFinishMoney(String searchPayment, String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentFinishMoney(String searchPayment, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentFinishMoney(String searchPayment, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.customerName like %?3% AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndStatusAndPaymentFinishMoney(String searchPayment, String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndPaymentFinishMoney(String searchPayment, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndNameFinishMoney(String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndNameFinishMoney(String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameAndPhoneFinishMoney(String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.payment = ?2 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchNameAndPaymentFinishMoney(String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone like %?1% AND c.payment = ?2 AND c.money = c.total AND c.money > 0")
    Page<OrderEntity> searchPhoneAndPaymentFinishMoney(String searchPhone, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);





    @Query("SELECT c FROM OrderEntity c WHERE c.money > 0")
    Page<OrderEntity> findAllMoney(Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND (c.createdAt BETWEEN ?1 AND ?2) AND c.money > 0")
    Page<OrderEntity> betweenDateMoney(LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.money > 0")
    Page<OrderEntity> findAllAndStatusMoney(String searchStatus, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.money > 0")
    Page<OrderEntity> searchNameAndStatusMoney(String searchStatus, String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND c.money > 0")
    Page<OrderEntity> searchNameAndPhoneAndStatusMoney(String searchStatus, String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND c.money > 0")
    Page<OrderEntity> searchPhoneAndStatusMoney(String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.phone = ?3 AND c.money > 0")
    Page<OrderEntity> searchPaymentAndPhoneAndStatusMoney(String searchStatus, String searchPayment, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND c.money > 0")
    Page<OrderEntity> searchPhoneMoney(String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.money > 0")
    Page<OrderEntity> searchNameMoney(String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.money > 0")
    Page<OrderEntity> searchPaymentMoney(String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND c.money > 0")
    Page<OrderEntity> searchPaymentAndStatusMoney(String searchStatus, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND c.payment = ?3 AND c.money > 0")
    Page<OrderEntity> searchNameAndPaymentAndStatusMoney(String searchStatus, String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money > 0")
    Page<OrderEntity> betweenDateAndStatusMoney(String searchStatus, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneMoney(String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusMoney(String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPaymentAndStatusMoney(String searchStatus, String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPaymentMoney(String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndStatusMoney(String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3) AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameMoney(String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4 AND (c.createdAt BETWEEN ?5 AND ?6) AND (c.createdAt BETWEEN ?5 AND ?6) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentAndNameMoney(String searchPayment, String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentAndNameMoney(String searchPayment, String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentMoney(String searchPayment, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentMoney(String searchPayment, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.customerName like %?3% AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndStatusAndPaymentMoney(String searchPayment, String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndNameAndPaymentMoney(String searchPayment, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndNameMoney(String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4) AND c.money > 0")
    Page<OrderEntity> betweenDateAndPhoneAndNameMoney(String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND c.money > 0")
    Page<OrderEntity> searchNameAndPhoneMoney(String searchName, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.payment = ?2 AND c.money > 0")
    Page<OrderEntity> searchNameAndPaymentMoney(String searchName, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone like %?1% AND c.payment = ?2 AND c.money > 0")
    Page<OrderEntity> searchPhoneAndPaymentMoney(String searchPhone, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT new StatisticalMonthDTO(extract(month from c.updatedAt), sum (c.money))  FROM OrderEntity c WHERE (c.status='DA_NHAN' or c.status = 'DA_HUY') " +
            "AND extract(YEAR from c.updatedAt) = :year group by extract(month from c.updatedAt)")
    List<StatisticalMonthDTO> statisticalByYear(@Param("year") Integer year);


    @Query("SELECT new StatisticalOrderDTO((c.status),count(c.id))  FROM OrderEntity c WHERE extract(month from c.createdAt) = :month " +
            "and extract(YEAR from c.createdAt) = :year group by c.status")
    List<StatisticalOrderDTO> statisticalByOrder(@Param("month") Integer month, @Param("year") Integer year);


    @Query("SELECT new StatisticalProductDTO(p.id, concat(p.name, ' ', p.debut), count (p.quantity)) FROM ProductEntity p inner join OrderDetailEntity" +
            " od on od.product.id = p.id" +
            " inner join OrderEntity o on od.order.id = o.id where  o.status = 'DA_NHAN' group by p.id")
    List<StatisticalProductDTO> statisticalByProduct();

    @Query("SELECT new SumProductDTO(sum (od.quantity)) FROM ProductEntity p inner join OrderDetailEntity" +
            " od on od.product.id = p.id" +
            " inner join OrderEntity o on od.order.id = o.id where  o.status = 'DA_NHAN'")
    SumProductDTO numberOfProductsSold();

    @Query("SELECT new SumProductDTO(count (c.id))FROM OrderEntity c")
    SumProductDTO countOrder();
}
