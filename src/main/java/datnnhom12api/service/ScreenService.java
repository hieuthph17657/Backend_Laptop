package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ScreenEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ScreenRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ScreenService {
    Page<ScreenEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);

    ScreenEntity create(ScreenRequest post);

    ScreenEntity update(Long id, ScreenRequest post) throws CustomException;

    void delete(Long id);

    ScreenEntity open(Long id) throws CustomException;

    ScreenEntity close(Long id) throws CustomException;

    List<ScreenEntity> findAll();
}
