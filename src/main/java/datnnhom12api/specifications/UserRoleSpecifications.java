package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.UserRoleEntity;

public class UserRoleSpecifications extends BaseSpecifications<UserRoleEntity> {

    private static UserRoleSpecifications INSTANCE;

    public static UserRoleSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRoleSpecifications();
        }

        return INSTANCE;
    }



}
