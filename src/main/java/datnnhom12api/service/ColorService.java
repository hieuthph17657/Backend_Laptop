package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ColorDTO;
import datnnhom12api.entity.ColorEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ColorRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ColorService {
    Page<ColorEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);

    ColorEntity create (ColorRequest post);

    ColorEntity update(Long id, ColorRequest post) throws CustomException;

    void delete(Long id);
}
