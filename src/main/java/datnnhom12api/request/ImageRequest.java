package datnnhom12api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import datnnhom12api.entity.ProductEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageRequest {

    @NotBlank(message = "Tên ảnh không được để trống")
    private String name;

    @NotBlank(message = "Sản phẩm không được để trống")
    private ProductEntity product;

    private Long return_id;

    private Long exchange_id;
}
