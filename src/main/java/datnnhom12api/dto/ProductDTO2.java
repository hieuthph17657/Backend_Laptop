package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.ImagesEntity;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductDTO2 extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    private Float weight;

    private Float height ;

    private Integer warrantyPeriod;

    private Float width;

    private Float length;

    private List<ImagesEntity> images;

    private ProductStatus status;

    private ProcessorDTO processor;

    private RamDTO ram;

    private ScreenDTO screen;

    private CardDTO card;

    private StorageDTO storage;

    private CardDTO cardOnboard;

    private DiscountDTO discount;





}
