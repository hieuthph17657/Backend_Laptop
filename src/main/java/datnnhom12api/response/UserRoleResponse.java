package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.UserRoleDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserRoleResponse extends BaseResponse<UserRoleDTO> {
    public UserRoleResponse(Page<UserRoleDTO> toPageDTO) {
        super(toPageDTO);
    }

    public UserRoleResponse(List<UserRoleDTO> toListDTO) {

        super(toListDTO);
    }

    public UserRoleResponse(UserRoleDTO toDTO) {
        super(toDTO);
    }

    public UserRoleResponse() {
        super();
    }

    {
    }
}

