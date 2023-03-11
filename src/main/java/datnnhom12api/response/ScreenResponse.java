package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ScreenDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ScreenResponse extends BaseResponse<ScreenDTO> {

    public ScreenResponse(int status, String message, ScreenDTO data) {
        super(status, message, data);
    }

    public ScreenResponse() {
    }

    public ScreenResponse(ScreenDTO data) {
        super(data);
    }

    public ScreenResponse(Page<ScreenDTO> toPageDTO) {
        super(toPageDTO);
    }
    public ScreenResponse(List<ScreenDTO> toListDTO) {
        super(toListDTO);
    }
}
