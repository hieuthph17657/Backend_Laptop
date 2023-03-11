package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ExchangeRequest2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exchanges")
public class ExchangeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderId;

    @Column(name = "description")
    private String description;


    private String status;

    @OneToMany(mappedBy = "exchange")
    List<ExchangeDetailEntity> returnDetailEntities;

    public void setData(ExchangeRequest2 request) {
        this.description = request.getDescription();
        this.status = request.getStatus();
    }
}