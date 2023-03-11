package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductResponse extends BaseResponse<ProductDTO> {
    public ProductResponse(Page<ProductDTO> toPageDTO) {
        super(toPageDTO);
    }

    public ProductResponse(List<ProductDTO> toListDTO) {
        super(toListDTO);
    }

    public ProductResponse(ProductDTO toDTO) {
        super(toDTO);
    }

    public ProductResponse() {
        super();
    }
}
