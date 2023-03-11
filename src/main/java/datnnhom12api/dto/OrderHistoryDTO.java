package datnnhom12api.dto;


import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.OrderEntity;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrderHistoryDTO extends BaseDTO implements Serializable {

    private Long id;

    private OrderDTO orderId;

    private Double total;

    private String verifier;

    private String status;
}
