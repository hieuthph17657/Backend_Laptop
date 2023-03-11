package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.OrderRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private double total;
    private String payment;
    private double money;
    private String address;

    @Column(name = "shipping_free")
    private double shippingFree;
    private String phone;
    private String customerName;
    private String note;
    @Column(name = "status")
    private String status;

    //    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetailEntity> orderDetails;


    @OneToMany(mappedBy = "order")
    private List<ImagesEntity> images;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    public void setData(OrderRequest request) {
        this.total = request.getTotal();
        this.payment = request.getPayment();
        this.money = request.getMoney();
        this.address = request.getAddress();
        this.phone = request.getPhone();
        this.customerName = request.getCustomerName().equals(" ") ? " " : request.getCustomerName();
        this.note = request.getNote();
        this.status = request.getStatus();
        this.shippingFree = request.getShippingFree();
    }
}

