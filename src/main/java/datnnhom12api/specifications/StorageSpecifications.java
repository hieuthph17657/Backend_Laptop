package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.StorageEntity;

public class StorageSpecifications extends BaseSpecifications<StorageEntity> {
    private static StorageSpecifications INSTANCE;

    public static StorageSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StorageSpecifications();
        }

        return INSTANCE;
    }
}
