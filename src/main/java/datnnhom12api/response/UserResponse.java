package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserResponse extends BaseResponse<UserDTO> {
    public UserResponse(Page<UserDTO> toPageDTO) {
        super(toPageDTO);
    }

    public UserResponse(List<UserDTO> toListDTO) {

        super(toListDTO);
    }

    public UserResponse(UserDTO toDTO) {
        super(toDTO);
    }

    public UserResponse() {
        super();
    }
}
