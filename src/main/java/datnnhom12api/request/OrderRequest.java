package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long userId;

    @NotNull(message = "tổng tiền không được để trống")
    private double total;

    @NotBlank(message = "phương thức thanh toán không được để trống")
    private String payment;

    @NotNull
    private double money;

    @NotBlank(message = "địa chỉ không được để trống")
    private String address;

    private String status;

    private String customerName;

    private String phone;

    private String note;

    private double shippingFree;

    private List<OrderDetailRequest> orderDetails;
}
