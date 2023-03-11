package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.RamDTO;
import org.springframework.data.domain.Page;
import java.util.List;

public class RamResponse extends BaseResponse<RamDTO> {

    public RamResponse(Page<RamDTO> toPageDTO) {
        super(toPageDTO);
    }

    public RamResponse(List<RamDTO> toListDTO) {
        super(toListDTO);
    }

    public RamResponse(RamDTO toDTO) {
        super(toDTO);
    }

    public RamResponse() {
        super();
    }
}
