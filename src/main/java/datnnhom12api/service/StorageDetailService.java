package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.StorageDetailEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.StorageDetailRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface StorageDetailService {

    StorageDetailEntity create(StorageDetailRequest request) throws CustomException;

    Page<StorageDetailEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    StorageDetailEntity update(Long id, StorageDetailRequest request) throws CustomException;

    StorageDetailEntity delete(Long id) throws CustomException;

    List<StorageDetailEntity> findAll();
}
