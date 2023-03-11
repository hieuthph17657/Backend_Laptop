package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductDiscountDTO extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    private String imei;

    private DiscountDTO discount;
}
