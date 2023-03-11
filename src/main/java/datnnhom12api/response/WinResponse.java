package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.WinDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class WinResponse extends BaseResponse<WinDTO> {
    public WinResponse(int status, String message, WinDTO data) {
        super(status, message, data);
    }

    public WinResponse() {
    }

    public WinResponse(WinDTO data) {
        super(data);
    }

    public WinResponse(Page<WinDTO> toPageDTO) {
        super(toPageDTO);
    }

    public WinResponse(List<WinDTO> toListDTO) {
        super(toListDTO);
    }
}
