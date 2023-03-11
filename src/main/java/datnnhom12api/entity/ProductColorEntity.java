package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "product_color")
public class ProductColorEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name="color_id")
    private ColorEntity color;
}
