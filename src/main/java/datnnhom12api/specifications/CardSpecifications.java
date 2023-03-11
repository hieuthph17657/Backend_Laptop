package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.CardEntity;

public class CardSpecifications extends BaseSpecifications<CardEntity> {
    private static CardSpecifications INSTANCE;

    public static CardSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CardSpecifications();
        }

        return INSTANCE;
    }
}
