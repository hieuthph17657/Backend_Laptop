package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.request.ImageRequest;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.utils.support.OrderDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;


    @Column(name = "exchange_id")
    private Long exchange_id;


    public void setData(ImageRequest request) {
        this.name = request.getName();
        this.product = request.getProduct();
        this.exchange_id = request.getExchange_id();
    }
}
