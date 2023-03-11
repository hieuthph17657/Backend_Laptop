package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.AccessoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class accessoryResponse extends BaseResponse<AccessoryDTO> {
    public accessoryResponse(Page<AccessoryDTO> toPageDTO) {
        super(toPageDTO);
    }

    public accessoryResponse(List<AccessoryDTO> toListDTO) {
        super(toListDTO);
    }

    public accessoryResponse(AccessoryDTO toDTO) {
        super(toDTO);
    }

    public accessoryResponse() {
        super();
    }
}
