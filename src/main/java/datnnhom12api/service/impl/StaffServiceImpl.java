package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.RoleEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.StaffRepository;
import datnnhom12api.request.UserRequest;
import datnnhom12api.service.StaffService;
import datnnhom12api.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("staffService")
@Transactional(rollbackFor = Throwable.class)
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public UserEntity save(UserRequest userRequest) throws CustomException {
        List<UserEntity> userEntityList = staffRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            if (userRequest.getUsername().equals(userEntity.getUsername())) {
                throw new CustomException(403, "Tài khoản đã tồn tại!");
            }
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setData(userRequest);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = staffRepository.findRoleStaff();
        roleEntityList.add(roleEntity);
        userEntity.setRoles(roleEntityList);
        userEntity = staffRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity edit(Long id, UserRequest userRequest) throws CustomException {
        Optional<UserEntity> userEntityOptional = staffRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (!b.matches(userRequest.getPassword(), userEntity.getPassword())) {
            throw new CustomException(403, "Mật khẩu cũ không chính xác!");
        } else {
            userEntity.setData(userRequest);
            userEntity = staffRepository.save(userEntity);
            return userEntity;
        }
    }

    @Override
    public UserEntity delete(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = staffRepository.findById(id);
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy người dùng");
        }
        UserEntity userEntity = staffRepository.getById(id);
        staffRepository.delete(userEntity);
        return userEntity;
    }

    @Override
    public Page<UserEntity> paginate(String searchUserName, Integer searchStatus, int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);

        if (searchUserName != null && searchStatus != null) {
            return staffRepository.findByUserNameStatus(searchUserName, searchStatus, pageable);
        } else if (searchUserName != null) {
            return staffRepository.findByUserName(searchUserName, pageable);
        } else if (searchStatus != null) {
            return staffRepository.findByStatus(searchStatus, pageable);
        } else {
            return staffRepository.findAllStaff(pageable);
        }
    }

    @Override
    public UserEntity open(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = staffRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(1);
        userEntity = staffRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity close(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = staffRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(0);
        userEntity = staffRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity find(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = staffRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        return userEntity;
    }
}
