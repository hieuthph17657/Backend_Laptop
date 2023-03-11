package datnnhom12api.repository;

import datnnhom12api.dto.InventoryDTO;
import datnnhom12api.entity.ExchangeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeDetailRepository extends JpaRepository<ExchangeDetailEntity, Long>,
        JpaSpecificationExecutor<ExchangeDetailEntity> {

    @Query("select o from ExchangeDetailEntity o where o.exchange.id = :id")
    List<ExchangeDetailEntity> findReturnByIds(@Param("id")Long id);

    @Query("select new InventoryDTO(p.id,p.name,m.name,p.price,count(rd.quantity)) from  ExchangeDetailEntity rd " +
            "inner join OrderDetailEntity od on od.id = rd.orderDetail.id " +
            "inner join ProductEntity p on p.id = od.product.id " +
            "inner join ManufactureEntity  m on m.id = p.manufacture.id " +
            "inner join ExchangeEntity r on r.id = rd.exchange.id where  rd.status= 'DA_XAC_NHAN' and rd.isCheck='1'" +
            "group by p.id")
    List<InventoryDTO> getAllInventoryDTO();


    @Query("select o from ExchangeDetailEntity o where o.orderChange = :id")
    ExchangeDetailEntity getByOrderChange(Integer id);


}
