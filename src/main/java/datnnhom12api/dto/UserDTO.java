package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO implements Serializable {
    private int id;
    private String username;
    private int status;
    List<RoleDTO> roles;
    List<InformationDTO> information;
}
