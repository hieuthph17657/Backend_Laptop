package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ManufactureDTO;
import datnnhom12api.dto.OrderHistoryDTO;
import org.springframework.data.domain.Page;

public class OrderHistoryResponse extends BaseResponse<OrderHistoryDTO> {

    public OrderHistoryResponse(Page<OrderHistoryDTO> toPageDTO) {
        super(toPageDTO);
    }

    public OrderHistoryResponse(OrderHistoryDTO toDTO) {
        super(toDTO);
    }

    public OrderHistoryResponse() {
        super();
    }
}
