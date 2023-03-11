package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessoryRequest {
    @NotBlank(message = "Tên phụ kiện không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

}
