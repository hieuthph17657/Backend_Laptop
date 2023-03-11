package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.OrderEntity;

public class OrderSpecifications extends BaseSpecifications<OrderEntity> {

    private static OrderSpecifications INSTANCE;

    public static OrderSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderSpecifications();
        }

        return INSTANCE;
    }
}
