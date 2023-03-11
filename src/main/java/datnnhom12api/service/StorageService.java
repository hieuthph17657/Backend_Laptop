package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ScreenEntity;
import datnnhom12api.entity.StorageEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.StorageRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface StorageService {

    StorageEntity create(StorageRequest request) throws CustomException;

    Page<StorageEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    StorageEntity update(Long id, StorageRequest request) throws CustomException;

    StorageEntity delete(Long id) throws CustomException;

    List<StorageEntity> findAll();
}
