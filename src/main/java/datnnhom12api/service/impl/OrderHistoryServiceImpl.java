package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.OrderHistoryDTO;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.OrderHistoryEntity;
import datnnhom12api.mapper.OrderHistoryMapper;
import datnnhom12api.repository.OrderHistoryRepository;
import datnnhom12api.repository.OrderRepository;
import datnnhom12api.service.OrderHistoryService;
import datnnhom12api.specifications.ManufactureSpecifications;
import datnnhom12api.specifications.OrderHistorySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("orderHistoryService")
@Transactional(rollbackFor = Throwable.class)
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public Page<OrderHistoryEntity> paginate(String searchStartDate, String searchEndDate, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<OrderHistoryEntity> specifications = OrderHistorySpecifications.getInstance().getQueryResult(filters);
        return orderHistoryRepository.findAll(specifications, pageable);
    }

    @Override
    public List<OrderHistoryDTO>  findByOrderId(Long orderId) {
        List<OrderHistoryEntity> orderHistory  = this.orderHistoryRepository.findByOrderId(orderId);
        List<OrderHistoryDTO> orderHistoryDTO = OrderHistoryMapper.toListDTO(orderHistory);
        return orderHistoryDTO;
    }
}
