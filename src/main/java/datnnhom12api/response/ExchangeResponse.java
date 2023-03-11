package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.OrderDTO;
import datnnhom12api.dto.OrderDetailDTO;

import java.util.List;

public class ExchangeResponse extends BaseResponse<OrderDetailDTO> {
    public ExchangeResponse(List<OrderDetailDTO> toListDTO) {
        super(toListDTO);
    }
}
