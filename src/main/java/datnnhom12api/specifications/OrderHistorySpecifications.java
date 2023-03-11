package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.OrderHistoryEntity;

public class OrderHistorySpecifications extends BaseSpecifications<OrderHistoryEntity> {

    private static OrderHistorySpecifications INSTANCE;

    public static OrderHistorySpecifications getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrderHistorySpecifications();
        }
        return INSTANCE;
    }
}
