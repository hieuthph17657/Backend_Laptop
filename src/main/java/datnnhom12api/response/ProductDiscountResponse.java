package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDiscountDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductDiscountResponse extends BaseResponse<ProductDiscountDTO> {
    public ProductDiscountResponse(Page<ProductDiscountDTO> toPageDTO) {
        super(toPageDTO);
    }

    public ProductDiscountResponse(List<ProductDiscountDTO> toListDTO) {
        super(toListDTO);
    }

    public ProductDiscountResponse(ProductDiscountDTO toDTO) {
        super(toDTO);
    }

    public ProductDiscountResponse() {
        super();
    }
}
