package datnnhom12api.dto;

import datnnhom12api.entity.ProductEntity;
import lombok.*;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductQuantityDTO {
    private Long id;
    private String name;
    private Integer quantity;
    public ProductQuantityDTO(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
    }
}
