package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class CategoryResponse extends BaseResponse<CategoryDTO> {
    public CategoryResponse(Page<CategoryDTO> toPageDTO) {
        super(toPageDTO);
    }

    public CategoryResponse(List<CategoryDTO> toListDTO) {
        super(toListDTO);
    }

    public CategoryResponse(CategoryDTO toDTO) {
        super(toDTO);
    }

    public CategoryResponse() {
        super();
    }
}
