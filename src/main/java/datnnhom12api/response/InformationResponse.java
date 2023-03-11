package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.InformationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class InformationResponse extends BaseResponse<InformationDTO> {
    public InformationResponse(Page<InformationDTO> toPageDTO) {
        super(toPageDTO);
    }

    public InformationResponse(List<InformationDTO> toListDTO) {
        super(toListDTO);
    }

    public InformationResponse(InformationDTO toDTO) {
        super(toDTO);
    }

    public InformationResponse() {
        super();
    }
}
