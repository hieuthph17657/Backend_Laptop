package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_history")
@EqualsAndHashCode(callSuper = true)
public class OrderHistoryEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @Column(name = "total")
    private Double total;

    @Column(name = "verifier")
    private String verifier;

    private String status;
}
