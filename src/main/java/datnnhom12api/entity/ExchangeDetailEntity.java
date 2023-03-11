package datnnhom12api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.utils.support.ReturnDetailStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exchange_detail")
@EqualsAndHashCode(callSuper = true)
public class ExchangeDetailEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;

    @OneToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetailEntity orderDetail;

    private Integer orderChange;

    private String isCheck;

    private String reason;

    private int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exchange_id")
    ExchangeEntity exchange;

    @Enumerated(EnumType.STRING)
    private ReturnDetailStatus status;

}
