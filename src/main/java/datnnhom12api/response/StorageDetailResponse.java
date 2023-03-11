package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CartDTO;
import datnnhom12api.dto.StorageDetailDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class StorageDetailResponse extends BaseResponse<StorageDetailDTO> {
    public StorageDetailResponse(int status, String message, StorageDetailDTO data) {
        super(status, message, data);
    }

    public StorageDetailResponse() {
    }

    public StorageDetailResponse(StorageDetailDTO data) {
        super(data);
    }

    public StorageDetailResponse(Page<StorageDetailDTO> toPageDTO) {
        super(toPageDTO);
    }

    public StorageDetailResponse(List<StorageDetailDTO> toListDTO) {
        super(toListDTO);
    }
}
