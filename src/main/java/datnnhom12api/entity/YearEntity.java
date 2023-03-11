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
@Table(name = "years")
@EqualsAndHashCode(callSuper = true)
public class YearEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name="price")
    private Double price;


    @Column(name = "year")
    private String year;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

}
