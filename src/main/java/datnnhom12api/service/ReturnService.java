package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.InventoryDTO;
import datnnhom12api.dto.ExchangeDetailDTO;
import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ExchangeDetailEntity;
import datnnhom12api.entity.ExchangeEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ExchangeDetailRequest;
import datnnhom12api.request.ExchangeRequest2;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ReturnService {

    ExchangeEntity insert(ExchangeRequest2 post) throws CustomException;

    ExchangeEntity update(Long id, ExchangeRequest2 post) throws CustomException;

    Page<ExchangeEntity> paginate(String searchStatus, String searchName, String searchPhone, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    void delete(Long id);

    List<ExchangeDetailDTO> findById(Long id);

    UpdateReturnDetailDTO updateByReturnDetail(Long id, ExchangeDetailRequest request);

    ExchangeEntity getById(Long id);

    List<InventoryDTO> getAllInventory();

    ExchangeDetailDTO getByOrderChange(Long id);
}