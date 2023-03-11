package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.entity.StorageEntity;
import datnnhom12api.entity.StorageTypeEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StorageDetailDTO extends BaseDTO implements Serializable {

    private Long id;

    private StorageTypeDTO storageType;

    private String type;

    private String capacity;
}
