package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.ColorDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ColorResponse  extends BaseResponse<ColorDTO> {
    public ColorResponse(Page<ColorDTO> toPageDTO) {
        super(toPageDTO);
    }

    public ColorResponse(List<ColorDTO> toListDTO) {
        super(toListDTO);
    }

    public ColorResponse(ColorDTO toDTO) {
        super(toDTO);
    }

    public ColorResponse() {
        super();
    }
}
