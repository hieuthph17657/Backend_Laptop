package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.InformationEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.DiscountRequest;
import datnnhom12api.request.InformationRequest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DiscountService {
    DiscountEntity save(DiscountRequest post) throws CustomException;

    DiscountEntity edit(Long id, DiscountRequest post) throws CustomException;

    DiscountEntity active(Long id) throws CustomException;

    DiscountEntity inActive(Long id) throws CustomException;

    DiscountEntity delete(Long id) throws CustomException;

    DiscountEntity getByIdDiscount(Long id) throws CustomException;

    Page<DiscountEntity> paginate(String startDate, String endDate, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);
}

