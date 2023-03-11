package datnnhom12api.request;

import datnnhom12api.entity.CardEntity;
import lombok.Data;


@Data
public class GraphicsRequest {
    private CardEntity card_onboard;
    private CardEntity card_discrete;
}
