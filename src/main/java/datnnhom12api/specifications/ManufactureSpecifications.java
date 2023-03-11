package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.ManufactureEntity;

public class ManufactureSpecifications extends BaseSpecifications<ManufactureEntity> {

    private static ManufactureSpecifications INSTANCE;

    public static ManufactureSpecifications getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ManufactureSpecifications();
        }
        return INSTANCE;
    }
}
