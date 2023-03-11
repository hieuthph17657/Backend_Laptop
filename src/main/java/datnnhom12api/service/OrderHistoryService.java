package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.OrderHistoryDTO;
import datnnhom12api.entity.OrderHistoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderHistoryService {
    Page<OrderHistoryEntity> paginate(String searchStartDate, String searchEndDate, Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);


    List<OrderHistoryDTO> findByOrderId(Long orderId);
}
