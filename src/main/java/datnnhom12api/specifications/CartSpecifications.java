package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.CartEntity;

public class CartSpecifications extends BaseSpecifications<CartEntity> {
    private static CartSpecifications INSTANCE;

    public static CartSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CartSpecifications();
        }

        return INSTANCE;
    }
}
