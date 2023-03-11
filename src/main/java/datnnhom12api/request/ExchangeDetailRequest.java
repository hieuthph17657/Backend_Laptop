package datnnhom12api.request;

import datnnhom12api.utils.support.ReturnDetailStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeDetailRequest {

    @NotNull(message = "Mã sản phầm không được trống")
    private Long productId;

    private Long orderDetailId;

    private String reason;

    private int quantity;

    private  Long id;

    private Integer orderChange;

    private String isCheck;

    private ReturnDetailStatus status;

}
