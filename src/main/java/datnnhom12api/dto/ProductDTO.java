package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.*;
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
public class ProductDTO extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

//    private YearDTO year;
    private String pn;

    private String imei;

    private Float weight;

    private Float height ;

    private Float width;

    private Float length;

    private String debut;

//    private WinDTO win;

    private String description;

    private String material;

//    private UserDTO created_by;
//
//    private UserDTO updated_by;

    private List<ProductCategoryDTO> categoryProducts;

    private ManufactureDTO manufacture;

    private List<ImagesEntity> images;

    private List<ProductColorDTO> productColors;

//    private List<AccessoryProductDTO> accessoryProducts;

    private ProductStatus status;

//    private ProcessorDTO processor;

//    private RamDTO ram;

//    private ScreenDTO screen;

//    private CardDTO card;

    private OriginDTO origin;

//    private StorageDTO storage;

    private String security;

//    private CardDTO cardOnboard;

//    private BatteryChargerDTO battery;
    private DiscountDTO discount;
}
