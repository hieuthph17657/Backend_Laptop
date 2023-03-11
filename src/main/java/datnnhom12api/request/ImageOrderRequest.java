package datnnhom12api.request;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageOrderRequest {

    @NotBlank(message = "Id hoá đơn không được để trống")
    private Long order_id;

    private Long productId;

    private Long exchange_id;

    private String name;
}
