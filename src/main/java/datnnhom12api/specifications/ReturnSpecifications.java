package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.ExchangeEntity;

public class ReturnSpecifications  extends BaseSpecifications<ExchangeEntity> {
    private static ReturnSpecifications INSTANCE;

    public static ReturnSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReturnSpecifications();
        }
        return INSTANCE;
    }
}