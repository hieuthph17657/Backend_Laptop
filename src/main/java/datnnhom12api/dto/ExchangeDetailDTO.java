package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ReturnDetailStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExchangeDetailDTO extends BaseDTO implements Serializable {
    private Long id;

    private ProductDTO productId;

    private OrderDetailDTO orderDetail;

    private Long exchangeId;

    private String reason;

    private int quantity;

    private String isCheck;

    private Integer orderChange;

    private ReturnDetailStatus status;
}
