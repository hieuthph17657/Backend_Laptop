package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.*;
import datnnhom12api.entity.ImagesEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderEntity save(OrderRequest order) throws CustomException;

    OrderEntity saveNoLogin(OrderRequest orderRequest) throws CustomException;

    OrderEntity saveOfUser(OrderRequest order) throws CustomException;

    OrderEntity edit(Long id, OrderRequest order) throws CustomException;

    OrderEntity delete(Long id) throws CustomException;

    Page<OrderEntity> paginate(String searchPayment, String searchName, String searchStatus, String searchPhone, String searchStartDate, String searchEndDate, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    Page<OrderEntity> ordersFilterPrice(String searchMoney, String searchPayment, String searchName, String searchStatus, String searchPhone, String searchStartDate, String searchEndDate, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    Page<OrderEntity> orderFinishMoney(String searchPayment, String searchName, String searchStatus, String searchPhone, String searchStartDate, String searchEndDate, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    UserEntity createUser(CreateUserOnOrderRequest createUserOnOrderRequest) throws CustomException;

    List<OrderDetailDTO> findByOrder(Long id);

    OrderByIdDTO findById(Long id);

    OrderEntity cancelled(Long id) throws CustomException;

    OrderEntity received(Long id) throws CustomException;

    OrderDetailDTO update(Long id, OrderDetailRequest orderDetailRequest);


    UpdateOrderDetailDTO findByOrderDetailDTO(Long id, UpdateOrderDetailRequest orderDetailRequest);

    List<OrderEntity> findUserName(String username);

    List<OrderEntity> findPhone(String phone);

    List<OrderEntity> findByDate(String createdAt);

    List<OrderDetailEntity> createOrderDetail(List<ExchangeRequest> exchangeRequest);

    List<OrderDetailEntity> createOrderDetail2(List<ExchangeRequest3> exchangeRequest);

    OrderDetailDTO updateOrderDetail(Long id, OrderDetailRequest orderDetailRequest);

    UpdateOrderDetailDTO updateWithReturn(Long orderId, Long orderDetailId, UpdateOrderDetailRequest orderDetailRequest);

    List<OrderConfirmDTO> findByIdOrderId(List<OrderConfirmDTO> id);

    OrderConfirmDTO findByIdOrderId(OrderConfirmDTO id);

    List<OrderExchangeDTO> updateWhenExchange(List<OrderExchangeDTO> request, Long orderId);

    List<StatisticalMonthDTO> statisticalByYear(Integer year);

    List<StatisticalOrderDTO> statisticalByOrder(Integer month, Integer year);

    List<StatisticalProductDTO> statisticalByProduct();

    SumProductDTO countOrder();

    ImageDTO addImageOrder(ImageOrderRequest request);

    OrderEntity updateNoteRequest(Long id, UpdateNoteRequest request);

    SumProductDTO numberOfProductsSold();

    List<OrderExchangeDTO> updateWhenExchangeCancel(List<OrderExchangeDTO> request, Long orderId);

    OrderDetailDTO updateInCancel(Long id, OrderDetailRequest odRequest);

    List<OrderConfirmDTO> findByIdOrderId2(List<OrderConfirmDTO> requests);

    List<OrderConfirmDTO> findByIdOrderId3(List<OrderConfirmDTO> requests);
}
