package datnnhom12api.request;

import datnnhom12api.entity.*;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Số lượng sản phẩm không được để trống")
    private Integer quantity;

    @NotNull(message = "Giá sản phẩm không được để trống")
    private Double price;

    @NotBlank(message = "Không được để trống")
    private String imei;

    @NotNull(message = "Không được để trống")
    private Float weight;

    @NotNull(message = "Không được để trống chiều dài")
    private Float length;

    @NotNull(message = "Không được để trống chiều rộng")
    private Float width;

    @NotNull(message = "Không được để trống chiều cao")
    private Float height;

//    @NotBlank(message = "Năm ra mắt Không được để trống")
    private String debut;

    private List<ImageRequest> images;

    private Integer warrantyPeriod;

    private String status;

    private List<Long> categoryId;

    private Long manufactureId;

    private Long processorId;

    private Long RamId;

    private Long screenId;

    private Long cardId;

    private Long originId;

    private List<Long> colorId;

    private Long batteryId;

    private Long storageId;

    private Long winId;

    private String material;

    private Long cardOnboard;

    private List<Long> accessoryId;

    private String description;

    private String security;

}
