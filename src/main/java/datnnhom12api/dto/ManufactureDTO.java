package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ManufactureStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ManufactureDTO  extends BaseDTO implements Serializable {

    private Long id;

    private String name;

    private ManufactureStatus status;
}
