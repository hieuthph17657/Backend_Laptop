package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.AuthDTO;

public class AuthResponse extends BaseResponse<AuthDTO> {
    public AuthResponse(int status, String message, AuthDTO data) {
        super(status, message, data);
    }

    public AuthResponse() {
    }

    public AuthResponse(AuthDTO data) {
        super(data);
    }
}
