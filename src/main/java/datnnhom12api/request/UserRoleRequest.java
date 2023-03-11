package datnnhom12api.request;

import datnnhom12api.dto.UserRoleDTO;
import datnnhom12api.entity.UserRoleEntity;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleRequest {
    private List<UserRoleDTO> values;
}
