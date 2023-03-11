package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CreateUserOnOrderRequest;
import datnnhom12api.request.InformationRequest;
import datnnhom12api.request.UserRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserEntity save(UserRequest user) throws CustomException;

    UserEntity createClient(UserRequest userRequest) throws CustomException;

    UserEntity edit(Long id, UserRequest user) throws CustomException;

    UserEntity delete(Long id) throws CustomException;

    Page<UserEntity> paginate(String searchUserName, Integer searchStatus, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    Page<UserEntity> loadUsers(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    UserEntity open(Long id) throws CustomException;

    UserEntity close(Long id) throws CustomException;

    UserEntity find(Long id) throws CustomException;


    SumProductDTO countCustomer();
}
