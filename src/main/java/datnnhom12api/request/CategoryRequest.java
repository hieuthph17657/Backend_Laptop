package datnnhom12api.request;

import datnnhom12api.utils.support.CategoryStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = "Tên loại sản phẩm không được để trống")
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

}
