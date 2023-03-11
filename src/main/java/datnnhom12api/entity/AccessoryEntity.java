package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.AccessoryRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accessories")
@EqualsAndHashCode(callSuper = true)
public class AccessoryEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "accessory")
    private List<AccessoryProductEntity> accessoryProducts;

    public void setData(AccessoryRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
    }
}
