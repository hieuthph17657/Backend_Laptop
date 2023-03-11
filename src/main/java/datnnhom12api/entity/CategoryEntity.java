package datnnhom12api.entity;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.utils.support.CategoryStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private CategoryStatus status;

    public void setData(CategoryRequest request) {
        this.name = request.getName();
        this.status = request.getStatus()
                == CategoryStatus.DRAFT ? CategoryStatus.DRAFT
                : CategoryStatus.ACTIVE ;
    }
}

