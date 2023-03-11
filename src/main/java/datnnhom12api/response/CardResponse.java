package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CardDTO;
import datnnhom12api.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class CardResponse extends BaseResponse<CardDTO> {
    public CardResponse(Page<CardDTO> toPageDTO) {
        super(toPageDTO);
    }

    public CardResponse(List<CardDTO> toListDTO) {
        super(toListDTO);
    }

    public CardResponse(CardDTO toDTO) {
        super(toDTO);
    }

    public CardResponse() {
        super();
    }
}
