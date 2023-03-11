package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.OriginDTO;
import datnnhom12api.entity.OriginEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.OriginRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OriginService {
    OriginEntity save(OriginRequest post) throws CustomException;

    OriginEntity edit(Long id, OriginRequest post) throws CustomException;

    OriginEntity delete(Long id) throws CustomException;

    Page<OriginEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    OriginDTO active(Long id) throws CustomException;

    OriginDTO inactive(Long id) throws CustomException;
}
