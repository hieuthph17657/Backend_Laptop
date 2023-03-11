package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.StorageDetailRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "storage_detail")
@EqualsAndHashCode(callSuper = true)
public class StorageDetailEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "storage_type_id")
    private StorageTypeEntity storageType;

    @OneToMany(mappedBy = "storageDetail")
    List<StorageEntity> storages;

    private String type;

    private String capacity;

    public void setData(StorageDetailRequest request) {
        this.type = request.getType();
        this.capacity = request.getCapacity();
    }
}
