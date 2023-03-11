package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.ScreenEntity;

public class ScreenSpecifications extends BaseSpecifications<ScreenEntity> {

    private static ScreenSpecifications INSTANCE;

    public static ScreenSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScreenSpecifications();
        }
        return INSTANCE;
    }
}
