package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ReturnDetailStatus;
import lombok.*;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UpdateReturnDetailDTO extends BaseDTO implements Serializable {
    private ReturnDetailStatus status;
}
