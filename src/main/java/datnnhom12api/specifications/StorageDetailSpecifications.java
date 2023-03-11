package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.StorageDetailEntity;

public class StorageDetailSpecifications extends BaseSpecifications<StorageDetailEntity> {
    private static StorageDetailSpecifications INSTANCE;

    public static StorageDetailSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StorageDetailSpecifications();
        }

        return INSTANCE;
    }
}
