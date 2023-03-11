package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.StorageRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "storage_type")
@EqualsAndHashCode(callSuper = true)
public class StorageTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "storageType")
    List<StorageDetailEntity> storageDetails;

    private String name;
}
