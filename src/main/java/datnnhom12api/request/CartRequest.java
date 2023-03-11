package datnnhom12api.request;

import lombok.*;
import org.aspectj.bridge.IMessage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartRequest {


    private Long productId;

    @NotNull(message ="mã người dùng không được trống")
    private Long userId;

    @NotNull(message = "số lượng không được trống")
    private int quantity;

    private Double total;
}
