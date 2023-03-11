package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CardEntity;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CardRequest;
import datnnhom12api.request.CartRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CardService {
    CardEntity create(CardRequest request) throws CustomException;

    Page<CardEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    CardEntity update(Long id, CardRequest post) throws CustomException;

    CardEntity delete(Long id) throws CustomException;

    List<CardEntity> findAll();
}
