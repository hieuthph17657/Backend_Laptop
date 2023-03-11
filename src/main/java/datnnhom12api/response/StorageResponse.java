package datnnhom12api.response;
import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CartDTO;
import datnnhom12api.dto.StorageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class StorageResponse extends BaseResponse<StorageDTO> {
    public StorageResponse(int status, String message, StorageDTO data) {
        super(status, message, data);
    }

    public StorageResponse() {
    }

    public StorageResponse(StorageDTO data) {
        super(data);
    }

    public StorageResponse(Page<StorageDTO> toPageDTO) {
        super(toPageDTO);
    }

    public StorageResponse(List<StorageDTO> toListDTO) {
        super(toListDTO);
    }
}
