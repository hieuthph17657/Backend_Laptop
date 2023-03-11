package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.OriginDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class OriginResponse extends BaseResponse<OriginDTO> {
    public OriginResponse(Page<OriginDTO> toPageDTO) {
        super(toPageDTO);
    }

    public OriginResponse(List<OriginDTO> toListDTO) {
        super(toListDTO);
    }

    public OriginResponse(OriginDTO toDTO) {
        super(toDTO);
    }

    public OriginResponse() {
        super();
    }
}
