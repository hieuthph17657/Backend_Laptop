package datnnhom12api.request;

import datnnhom12api.utils.support.ManufactureStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManufactureRequest {
    @NotBlank(message = "Hãng sản xuất không được để trống")
    private String name;

    @Enumerated(EnumType.STRING)
    private ManufactureStatus status;
}
