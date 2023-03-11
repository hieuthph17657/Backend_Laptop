package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.utils.support.ProcessorStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProcessorDTO extends BaseDTO implements Serializable {

    private Long id;

    private String cpuCompany;

    private String cpuTechnology;

    private String cpuType;

    private String cpuSpeed;

    private String maxSpeed;

    private int multiplier;

    private int numberOfThread;

    private String caching;

    private ProcessorStatus status;


}
