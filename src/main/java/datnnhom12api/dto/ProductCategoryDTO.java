package datnnhom12api.dto;


import datnnhom12api.core.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductCategoryDTO extends BaseDTO implements Serializable {
    private Long id;

    private CategoryDTO category;
}
