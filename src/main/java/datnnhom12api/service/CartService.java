package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.CategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CartService {

    CartEntity create(CartRequest request) throws CustomException;

    Page<CartEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    CartEntity update(Long id, CartRequest post) throws CustomException;

    CartEntity delete(Long id) throws CustomException;
}
