package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.UserRoleDTO;
import datnnhom12api.entity.UserRoleEntity;
import datnnhom12api.request.UserRoleRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserRoleService {
    List<UserRoleDTO> updateRole(UserRoleRequest userRoleRequest);
}
