package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    CategoryEntity save(CategoryRequest post) throws CustomException;

    CategoryEntity edit(Long id, CategoryRequest post) throws CustomException;

    CategoryEntity delete(Long id) throws CustomException;

    Page<CategoryEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    CategoryEntity open(Long id) throws CustomException;

    CategoryEntity close(Long id) throws CustomException;

    List<CategoryEntity> findAll();
}
