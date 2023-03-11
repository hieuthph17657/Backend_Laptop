package datnnhom12api.request;

import datnnhom12api.utils.support.OrderDetailStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeRequest {

    @NotNull(message = "Id hoá đơn không được trống")
    private Long orderId;

    @NotNull(message = "id sản phẩm không được để trống")
    private Long productId;

    @NotNull(message = "total không được để trống")
    private double total;

    @NotNull (message = "số lượng không được để trống")
    private int quantity;

    private int isCheck;

//    @Enumerated(EnumType.STRING)
//    private OrderDetailStatus status;
}
