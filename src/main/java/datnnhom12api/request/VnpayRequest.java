package datnnhom12api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VnpayRequest implements Serializable {
    private String vnp_OrderInfo;
    private String orderType;
    private int amount;
    private String bankCode;
    private String language;
}
