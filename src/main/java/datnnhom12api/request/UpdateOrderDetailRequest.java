package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateOrderDetailRequest {

    @NotNull(message = "id sản phẩm không được để trống")
    private Long productId;

    @NotNull (message = "số lượng không được để trống")
    private int quantity;
}
