package datnnhom12api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_category")
public class ProductCategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
}
