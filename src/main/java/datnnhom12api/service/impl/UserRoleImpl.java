package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.UserRoleDTO;
import datnnhom12api.entity.UserRoleEntity;
import datnnhom12api.repository.UserRoleRepository;
import datnnhom12api.request.UserRoleRequest;
import datnnhom12api.service.UserRoleService;
import datnnhom12api.specifications.UserRoleSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserRoleImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public List<UserRoleDTO> updateRole(UserRoleRequest userRoleRequest) {
        List<UserRoleDTO> userRoles = userRoleRequest.getValues();
        List<Long> listUserIds = new ArrayList<>();
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        for (UserRoleDTO userRole : userRoles) {
            if (!listUserIds.contains(userRole.getUserId())) {
                listUserIds.add(userRole.getUserId());
            }
            for (Long roleId : userRole.getRoleIds()) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(userRole.getUserId());
                userRoleEntity.setRoleId(roleId);
                userRoleEntities.add(userRoleEntity);
            }
        }
        HashSet<Long> userIds = new HashSet<>(listUserIds);
        userRoleRepository.deleteByUserIds(userIds);
        userRoleRepository.saveAll(userRoleEntities);
        return userRoles;
    }
}
