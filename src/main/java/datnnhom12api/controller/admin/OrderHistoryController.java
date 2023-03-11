package datnnhom12api.controller.admin;

import datnnhom12api.dto.OrderHistoryDTO;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.OrderHistoryEntity;
import datnnhom12api.entity.OriginEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.OrderHistoryMapper;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.mapper.OriginMapper;
import datnnhom12api.paginationrequest.OrderPaginationRequest;
import datnnhom12api.request.OrderHistoryRequest;
import datnnhom12api.request.OriginRequest;
import datnnhom12api.response.OrderHistoryResponse;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.response.OriginResponse;
import datnnhom12api.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/orders/history")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping
    public OrderHistoryResponse index(@Valid OrderPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<OrderHistoryEntity> page = orderHistoryService.paginate(request.getSearchStartDate(),request.getSearchEndDate(),
                request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new OrderHistoryResponse(OrderHistoryMapper.toPageDTO(page));
    }

    @GetMapping("/{orderId}")
    public List<OrderHistoryDTO> findByOrderId(@PathVariable("orderId")Long orderId) {
        List<OrderHistoryDTO> historyDTO = this.orderHistoryService.findByOrderId(orderId);
        return historyDTO;
    }


}
