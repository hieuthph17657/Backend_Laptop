package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDTO2;
import datnnhom12api.dto.ProductDTOById;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductResponse2 extends BaseResponse<ProductDTO2> {

    public ProductResponse2(Page<ProductDTO2> toPageDTO) {
        super(toPageDTO);
    }

    public ProductResponse2(List<ProductDTO2> toListDTO) {
        super(toListDTO);
    }

    public ProductResponse2(ProductDTO2 toDTO) {
        super(toDTO);
    }

    public ProductResponse2() {
        super();
    }
}
