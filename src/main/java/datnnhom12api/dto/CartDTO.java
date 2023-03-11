package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.ProductEntity;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CartDTO extends BaseDTO implements Serializable {

    private Long id;

    private ProductDTO product;

    private Long userId;

    private int quantity;

    private Double total;
}
