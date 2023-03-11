package datnnhom12api.dto;

import datnnhom12api.entity.InformationEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO implements Serializable {
    private Long id;
    private String token;
    private List<RoleDTO> roles;
    private String username;
    private List<InformationDTO> information;
}
