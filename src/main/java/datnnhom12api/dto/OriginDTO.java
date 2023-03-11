package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.OriginStatus;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OriginDTO extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private OriginStatus status;
}
