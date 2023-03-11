package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.InformationEntity;
import datnnhom12api.entity.RoleEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.InformationRepository;
import datnnhom12api.repository.UserRepository;
import datnnhom12api.request.UserRequest;
import datnnhom12api.service.UserService;
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

@Service("userService")
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InformationRepository informationRepository;

    @Override
    public UserEntity save(UserRequest userRequest) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.validateUserName(userRequest.getUsername());
        if (userEntityOptional.isPresent()) {
            throw new CustomException(403, "Tài khoản đã tồn tại");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setData(userRequest);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = userRepository.findRoleCustomer();
        roleEntityList.add(roleEntity);
        userEntity.setRoles(roleEntityList);
        userEntity = userRepository.save(userEntity);
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setUser(userEntity);
        informationEntity.setActive(0);
        informationEntity.setEmail("none");
        informationEntity.setAddress("none");
        informationEntity.setPhoneNumber("none");
        informationEntity.setFullName("none");
        this.informationRepository.save(informationEntity);
        return userEntity;
    }

    @Override
    public UserEntity createClient(UserRequest userRequest) throws CustomException {
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            if (userRequest.getUsername().equals(userEntity.getUsername())) {
                throw new CustomException(403, "Tài khoản đã tồn tại!");
            }
        }
//        UserEntity userEntity = new UserEntity();
//        userEntity.setData(userRequest);
//        userEntity.setRoles(roleEntityList);
//        userEntity = userRepository.save(userEntity);
//        return userEntity;
        return null;
    }

    @Override
    public UserEntity edit(Long id, UserRequest userRequest) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
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
            userEntity = userRepository.save(userEntity);
            return userEntity;
        }
    }

    @Override
    public UserEntity delete(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy người dùng");
        }
        UserEntity userEntity = userRepository.getById(id);
        userRepository.delete(userEntity);
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
            return userRepository.findByUserNameStatus(searchUserName, searchStatus, pageable);
        } else if (searchUserName != null) {
            return userRepository.findByUserName(searchUserName, pageable);
        } else if (searchStatus != null) {
            return userRepository.findByStatus(searchStatus, pageable);
        } else {
            return userRepository.findAllStaff(pageable);
        }
    }

    @Override
    public Page<UserEntity> loadUsers(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);

        return userRepository.findAll(specifications, pageable);
    }

    @Override
    public UserEntity open(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity close(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        userEntity.setStatus(0);
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity find(Long id) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (userEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        UserEntity userEntity = userEntityOptional.get();
        return userEntity;
    }

    @Override
    public SumProductDTO countCustomer() {
        SumProductDTO user = this.userRepository.countCustomer();
        return user;
    }


}
