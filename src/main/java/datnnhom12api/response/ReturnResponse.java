package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ExchangeDTO;
import org.springframework.data.domain.Page;

public class ReturnResponse extends BaseResponse<ExchangeDTO> {
    public ReturnResponse(int status, String message, ExchangeDTO data) {
        super(status, message, data);
    }

    public ReturnResponse() {
    }

    public ReturnResponse(ExchangeDTO data) {
        super(data);
    }

    public ReturnResponse(Page<ExchangeDTO> toPageDTO) {
        super(toPageDTO);
    }
}