package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.InformationEntity;

public class InformationSpecifications extends BaseSpecifications<InformationEntity> {
    private static InformationSpecifications INSTANCE;

    public static InformationSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InformationSpecifications();
        }

        return INSTANCE;
    }
}
