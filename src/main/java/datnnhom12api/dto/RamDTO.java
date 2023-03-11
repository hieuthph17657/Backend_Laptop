package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.utils.support.RamStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RamDTO extends BaseDTO implements Serializable {

    private Long id;

    private String ramCapacity;

    private String typeOfRam;

    private String ramSpeed;

    private String maxRamSupport;

    private int onboardRam;

    private int looseSlot;

    private int remainingSlot;

    private RamStatus status;
}
