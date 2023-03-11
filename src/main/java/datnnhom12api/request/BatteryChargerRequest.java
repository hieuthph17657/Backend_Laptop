package datnnhom12api.request;

import datnnhom12api.utils.support.BatteryChargerStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BatteryChargerRequest {
    private Long id;

    private String batteryType;//loại pin

//    @NotNull(message ="pin không được trống")
    private String battery;//pin

//    @NotNull(message ="sạc không được trống")
    private String charger;//sạc

//    @NotNull(message ="giá không được trống")
    private double price;

    @Enumerated(EnumType.STRING)
    private BatteryChargerStatus status;
}
