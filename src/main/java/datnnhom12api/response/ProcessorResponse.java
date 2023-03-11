package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ProcessorDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProcessorResponse extends BaseResponse<ProcessorDTO> {

    public ProcessorResponse(Page<ProcessorDTO> toPageDTO) {
        super(toPageDTO);
    }

    public ProcessorResponse(List<ProcessorDTO> toListDTO) {
        super(toListDTO);
    }

    public ProcessorResponse(ProcessorDTO toDTO) {
        super(toDTO);
    }

    public ProcessorResponse() {
        super();
    }


}
