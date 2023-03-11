package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.utils.support.OrderDetailStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_detail")
@EqualsAndHashCode(callSuper = true)
public class OrderDetailEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private double total;

    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderDetailStatus status;

    @Column(name = "isCheck")
    private Integer isCheck;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderEntity order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;


    ProductDTO pro;

    public void setData(OrderDetailRequest request) {
        this.quantity = request.getQuantity();
        this.isCheck = request.getIsCheck() == null ? null : request.getIsCheck();
        if (request.getStatus().equals("CHO_LAY_HANG")) {
            this.status = OrderDetailStatus.CHO_LAY_HANG;
        } else if (request.getStatus().equals("DANG_GIAO")) {
            this.status = OrderDetailStatus.DANG_GIAO;
        } else if (request.getStatus().equals("DA_NHAN")) {
            this.status = OrderDetailStatus.DA_NHAN;
        } else if (request.getStatus().equals("DA_HUY")) {
            this.status = OrderDetailStatus.DA_HUY;
        } else {
            this.status = OrderDetailStatus.CHO_XAC_NHAN;
        }
        this.total = request.getTotal();
    }

    public void enrichProduct(ProductEntity product) {

        this.product = product;
    }
}
