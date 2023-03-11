package datnnhom12api.response;
import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CartDTO;
import org.springframework.data.domain.Page;

public class CartResponse extends BaseResponse<CartDTO> {
    public CartResponse(int status, String message, CartDTO data) {
        super(status, message, data);
    }

    public CartResponse() {
    }

    public CartResponse(CartDTO data) {
        super(data);
    }

    public CartResponse(Page<CartDTO> toPageDTO) {
        super(toPageDTO);
    }
}
