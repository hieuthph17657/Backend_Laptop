package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.DiscountDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class DiscountResponse extends BaseResponse<DiscountDTO> {
    public DiscountResponse(Page<DiscountDTO> toPageDTO) {
        super(toPageDTO);
    }

    public DiscountResponse(List<DiscountDTO> toListDTO) {
        super(toListDTO);
    }

    public DiscountResponse(DiscountDTO toDTO) {
        super(toDTO);
    }

    public DiscountResponse() {
        super();
    }
}