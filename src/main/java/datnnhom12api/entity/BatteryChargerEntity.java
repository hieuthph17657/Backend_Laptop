package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.utils.support.BatteryChargerStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "battery_charger")
@EqualsAndHashCode(callSuper = true)
public class BatteryChargerEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "battery")
    private List<ProductEntity> products;

    @Column(name="battery_type")
    private String batteryType;//loại pin

    @Column(name="battery")
    private String battery;//pin

    @Column(name="charger")
    private String charger;//sac

    @Enumerated(EnumType.STRING)
    private BatteryChargerStatus status;

    public void setData(BatteryChargerRequest request) {
        this.id = request.getId();
        this.batteryType=request.getBatteryType();
        this.battery = request.getBattery();
        this.charger = request.getCharger();
        this.status = request.getStatus()
                == BatteryChargerStatus.DRAFT ? BatteryChargerStatus.DRAFT
                : request.getStatus()
                == BatteryChargerStatus.INACTIVE ? BatteryChargerStatus.INACTIVE
                :BatteryChargerStatus.ACTIVE ;
    }
}
