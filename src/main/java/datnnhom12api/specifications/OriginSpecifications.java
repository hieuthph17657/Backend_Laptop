package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.OriginEntity;

public class OriginSpecifications extends BaseSpecifications<OriginEntity> {
    private static OriginSpecifications INSTANCE;

    public static OriginSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OriginSpecifications();
        }

        return INSTANCE;
    }
}
