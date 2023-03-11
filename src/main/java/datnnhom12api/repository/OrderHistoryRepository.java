package datnnhom12api.repository;

import datnnhom12api.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long>, JpaSpecificationExecutor<OrderHistoryEntity> {

    @Query("select o from  OrderHistoryEntity  o where o.orderId.id =:orderId")
    List<OrderHistoryEntity> findByOrderId(@PathVariable("orderId") Long orderId);

    @Query("select o from  OrderHistoryEntity  o where o.orderId.id =:orderId")
    OrderHistoryEntity getByOrderId(@PathVariable("orderId") Long orderId);

}
