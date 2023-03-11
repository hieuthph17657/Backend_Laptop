package datnnhom12api.repository;

import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>,
        JpaSpecificationExecutor<OrderDetailEntity> {

    @Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id= :id ")
    OrderDetailEntity findByOrderId(@Param("id") Long id);

    @Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id=?1 ")
    List<OrderDetailEntity> findByOrder(Long id);

    @Query("select o from OrderDetailEntity o where o.order.id = :Id")
    List<OrderDetailEntity> getOrderDetailEntityById(@Param("Id") Long id);

    @Query("select o from OrderDetailEntity o where o.isCheck = :id and o.product.id = :productId")
    OrderDetailEntity getOrderDetailByIsCheckAndProductId(@Param("id") Integer id,@Param("productId")Long productId);

    @Query("select o from OrderDetailEntity  o where  o.order.id = :orderId and o.product.id = :productId")
     OrderDetailEntity getOrderDetailEntityByOrderIsAndAndProduct(@Param("orderId")Long orderId,
                                                                 @Param("productId")Long productId);

    @Query("select o from OrderDetailEntity  o where  o.id = :id and o.order.id = :orderId")
    OrderDetailEntity findByIdAndOrderId(@Param("id") Long orderDetailId,@Param("orderId") Long orderId);


    @Query("select o from OrderDetailEntity o where o.order.id = :id and (o.isCheck is null or o.isCheck = 1)")
    List<OrderDetailEntity> findByOrderAndIscheck(@Param("id") Long id);

    @Query("select o from OrderDetailEntity o where o.order.id = :id and o.isCheck = :isCheck")
    List<OrderDetailEntity> findByOrderAndIscheck2(@Param("id") Long id,@Param("isCheck") Integer isCheck);

}
