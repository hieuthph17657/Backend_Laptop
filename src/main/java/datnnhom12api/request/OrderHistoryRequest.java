package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderHistoryRequest {

    @NotBlank(message = "Mã hoá đơn không được để trống")
    private Long id;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}
