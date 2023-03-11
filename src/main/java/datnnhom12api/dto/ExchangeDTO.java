package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ReturnStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExchangeDTO extends BaseDTO implements Serializable {

    private Long id;

    private OrderDTO orderId;

    private String description;

    private ReturnStatus status;
}