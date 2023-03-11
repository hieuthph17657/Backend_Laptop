package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.UserRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface StaffService {

    UserEntity save(UserRequest post) throws CustomException;

    UserEntity edit(Long id, UserRequest post) throws CustomException;

    UserEntity delete(Long id) throws CustomException;

    Page<UserEntity> paginate(String searchUserName, Integer searchStatus, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    UserEntity open(Long id) throws CustomException;

    UserEntity close(Long id) throws CustomException;

    UserEntity find(Long id) throws CustomException;
}
