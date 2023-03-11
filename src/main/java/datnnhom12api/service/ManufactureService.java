package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ManufactureRequest;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Map;

public interface ManufactureService {

    ManufactureEntity insert(ManufactureRequest request) throws CustomException;

    Page<ManufactureEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    ManufactureEntity create(ManufactureRequest post);

    ManufactureEntity update(Long id, ManufactureRequest post) throws CustomException;

    void delete(Long id);

    List<ManufactureEntity> findAll();
}
