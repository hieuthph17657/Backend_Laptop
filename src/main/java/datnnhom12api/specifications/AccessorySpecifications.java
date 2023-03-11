package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.AccessoryEntity;

public class AccessorySpecifications extends BaseSpecifications<AccessoryEntity> {
    private static AccessorySpecifications INSTANCE;

    public static AccessorySpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccessorySpecifications();
        }
        return INSTANCE;
    }
}
