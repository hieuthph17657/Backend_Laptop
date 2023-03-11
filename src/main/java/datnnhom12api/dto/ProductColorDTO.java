package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.ColorEntity;
import datnnhom12api.entity.ImagesEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductColorDTO extends BaseDTO implements Serializable {
    private Long id;

    private ColorDTO color;
}
