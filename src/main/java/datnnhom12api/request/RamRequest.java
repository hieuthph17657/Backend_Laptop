package datnnhom12api.request;

import datnnhom12api.utils.support.RamStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RamRequest {

    @NotBlank(message = "Dung lượng ram không được để trống")
    private String ramCapacity;

    @NotBlank(message = "Loại ram không được để trống")
    private String typeOfRam;

    @NotBlank(message = "Tốc độ ram không được để trống")
    private String ramSpeed;

    @NotBlank(message = "Hỗ trợ ram tối đa không được để trống")
    private String maxRamSupport;

    @NotNull(message = "Ram onboard không được để trống")
    private int onboardRam;

    @NotNull(message = "Số khe cắm rời không được để trống")
    private int looseSlot;

    @NotNull(message = "Số khe cắm còn lại không được để trống")
    private int remainingSlot;

    @Enumerated(EnumType.STRING)
    private RamStatus status;
}
