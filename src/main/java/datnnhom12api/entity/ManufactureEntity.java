package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ManufactureRequest;
import datnnhom12api.utils.support.ManufactureStatus;
import datnnhom12api.utils.support.ProcessorStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufactures")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ManufactureEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private ManufactureStatus status;

    @OneToMany(mappedBy = "manufacture")
    private List<ProductEntity> products;

    public void setData(ManufactureRequest request) {
        this.name = request.getName();
        this.status = request.getStatus()
                == ManufactureStatus.DRAFT ? ManufactureStatus.DRAFT
                : ManufactureStatus.ACTIVE ;
    }
}
