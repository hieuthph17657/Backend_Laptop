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
public class UpdateOrderDetailDTO extends BaseDTO implements Serializable {
    private int quantity;
    private Long productId;
}
