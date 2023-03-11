package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accessory_product")
public class AccessoryProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name="accessory_id")
    private AccessoryEntity accessory;
}
