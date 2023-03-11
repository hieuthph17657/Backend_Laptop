package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.OrderDetailStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderDetailDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long orderId;
    private ProductDTO product;
    private double total;
    private int quantity;
    private OrderDetailStatus status;
    private Integer isCheck;
}
