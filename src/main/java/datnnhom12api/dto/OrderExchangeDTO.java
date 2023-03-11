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
public class OrderExchangeDTO extends BaseDTO implements Serializable {

    private Long id;

    private Long productId;

    private int quantity;

    private double total;

    private String status;

    private Integer isCheck;

    private String isBoolean;
}
