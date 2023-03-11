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
@Table(name = "storages")
@EqualsAndHashCode(callSuper = true)
public class StorageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "storage")
    private List<ProductEntity> products;

    @ManyToOne
    @JoinColumn(name = "storage_detail_id")
    private StorageDetailEntity storageDetail;

    private int total;

    private int number;

    public void setData(StorageRequest request) {
        this.total = request.getTotal();
        this.number = request.getNumber();
    }
}
