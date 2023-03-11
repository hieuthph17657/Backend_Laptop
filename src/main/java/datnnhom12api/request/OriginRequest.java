package datnnhom12api.request;

import datnnhom12api.utils.support.OriginStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OriginRequest {
//    @NotBlank(message = "Tên nước xuất xứ không được để trống")
    private String name;

    @Enumerated(EnumType.STRING)
    private OriginStatus status;
}
