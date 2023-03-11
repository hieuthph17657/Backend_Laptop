package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.UserEntity;

public class UserSpecifications extends BaseSpecifications<UserEntity> {

    private static UserSpecifications INSTANCE;

    public static UserSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserSpecifications();
        }

        return INSTANCE;
    }



}
