package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.RamRequest;
import datnnhom12api.utils.support.RamStatus;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rams")
@EqualsAndHashCode(callSuper = true)
public class RamEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "ram")
    private List<ProductEntity> products;

    @Column(name = "ram_capacity")
    private String ramCapacity;

    @Column(name = "type_of_ram")
    private String typeOfRam;

    @Column(name = "ram_speed")
    private String ramSpeed;

    @Column(name = "max_ram_support")
    private String maxRamSupport;

    @Column(name = "onboard_ram")
    private int onboardRam;

    @Column(name = "loose_slot")
    private int looseSlot;

    @Column(name = "remaining_slot")
    private int remainingSlot;

    @Enumerated(EnumType.STRING)
    private RamStatus status;

    public void setData(RamRequest request) {
        this.ramCapacity = request.getRamCapacity();
        this.typeOfRam = request.getTypeOfRam();
        this.remainingSlot = request.getRemainingSlot();
        this.looseSlot = request.getLooseSlot();
        this.onboardRam = request.getOnboardRam();
        this.ramSpeed = request.getRamSpeed();
        this.maxRamSupport = request.getMaxRamSupport();
        this.status = request.getStatus()
                == RamStatus.DRAFT ? RamStatus.DRAFT
                : RamStatus.ACTIVE ;
    }
}
