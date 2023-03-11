package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.WinEntity;

public class WinSpecifications extends BaseSpecifications<WinEntity> {
    private static WinSpecifications INSTANCE;

    public static WinSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WinSpecifications();
        }
        return INSTANCE;
    }
}
