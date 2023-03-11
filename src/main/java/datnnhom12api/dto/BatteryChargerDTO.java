package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.BatteryChargerStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BatteryChargerDTO extends BaseDTO implements Serializable {
    private Long id;

    private String batteryType;//loại pin

    private String battery;//pin

    private String charger;//sac

    @Enumerated(EnumType.STRING)
    private BatteryChargerStatus status;

}
