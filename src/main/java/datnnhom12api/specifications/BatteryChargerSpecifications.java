package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.BatteryChargerEntity;

public class BatteryChargerSpecifications extends BaseSpecifications<BatteryChargerEntity> {
    private static BatteryChargerSpecifications INSTANCE;

    public static BatteryChargerSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BatteryChargerSpecifications();
        }

        return INSTANCE;
    }
}
