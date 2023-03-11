package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ProductDTOById;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductFilterResponse extends BaseResponse<ProductDTOById> {
    public ProductFilterResponse(Page<ProductDTOById> toPageDTO) {
        super(toPageDTO);
    }

    public ProductFilterResponse(List<ProductDTOById> toListDTO) {
        super(toListDTO);
    }

    public ProductFilterResponse(ProductDTOById toDTO) {
        super(toDTO);
    }

    public ProductFilterResponse() {
        super();
    }
}
