package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ManufactureDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ManufactureResponse extends BaseResponse<ManufactureDTO> {

    public ManufactureResponse(Page<ManufactureDTO> toPageDTO) {
        super(toPageDTO);
    }
    public ManufactureResponse(List<ManufactureDTO> toListDTO) {
        super(toListDTO);
    }

    public ManufactureResponse(ManufactureDTO toDTO) {
        super(toDTO);
    }

    public ManufactureResponse() {
        super();
    }
}
