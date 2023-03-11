package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.request.OriginRequest;
import datnnhom12api.utils.support.CategoryStatus;
import datnnhom12api.utils.support.OriginStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "origins")
@EqualsAndHashCode(callSuper = true)
public class OriginEntity  extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "origin")
    private List<ProductEntity> products;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private OriginStatus status;

    public void setData(OriginRequest request) {
        this.name = request.getName();
        this.status = request.getStatus()
                == OriginStatus.DRAFT ? OriginStatus.DRAFT
                : OriginStatus.ACTIVE ;
    }
}
