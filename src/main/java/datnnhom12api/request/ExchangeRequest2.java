package datnnhom12api.request;

import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeRequest2 {

    @NotNull(message = "Id hoá đơn không được trống")
    private Long orderId;

    private String description;

    private String status;

    private List<ExchangeDetailRequest> returnDetailEntities;


}