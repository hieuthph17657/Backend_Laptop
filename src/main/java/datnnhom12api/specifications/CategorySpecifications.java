package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.CategoryEntity;

public class CategorySpecifications extends BaseSpecifications<CategoryEntity> {

    private static CategorySpecifications INSTANCE;

    public static CategorySpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategorySpecifications();
        }

        return INSTANCE;
    }
}
