package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.InformationEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.request.InformationRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface InformationService {
    InformationEntity save(InformationRequest post) throws CustomException;

    InformationEntity edit(Long id, InformationRequest post) throws CustomException;

    InformationEntity delete(Long id) throws CustomException;

    Page<InformationEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    InformationEntity getById(Long id) throws CustomException;
}

