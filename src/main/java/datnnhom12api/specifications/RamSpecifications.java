package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.RamEntity;

public class RamSpecifications extends BaseSpecifications<RamEntity> {
    private static RamSpecifications INSTANCE;

    public static RamSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RamSpecifications();
        }
        return INSTANCE;
    }
}
