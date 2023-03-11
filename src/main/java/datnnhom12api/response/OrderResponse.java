package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class OrderResponse extends BaseResponse<OrderDTO> {
    public OrderResponse(Page<OrderDTO> toPageDTO) {
        super(toPageDTO);
    }

    public OrderResponse(List<OrderDTO> toListDTO) {
        super(toListDTO);
    }

    public OrderResponse(OrderDTO toDTO) {
        super(toDTO);
    }

    public OrderResponse() {
        super();
    }
}
