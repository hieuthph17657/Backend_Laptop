package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.*;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.jwt.user.CustomUserDetails;
import datnnhom12api.repository.*;
import datnnhom12api.request.*;
import datnnhom12api.service.OrderService;
import datnnhom12api.specifications.OrderSpecifications;
import datnnhom12api.utils.support.OrderDetailStatus;
import datnnhom12api.utils.support.OrderStatus;
import datnnhom12api.utils.support.ReturnDetailStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service("orderService")
@Transactional(rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ExchangeDetailRepository exchangeDetailRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    public OrderEntity save(OrderRequest orderRequest) throws CustomException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setData(orderRequest);
        if (orderRequest.getUserId() == 0) {
            orderEntity.setUser(null);
        } else {
            UserEntity userEntity = userRepository.getById(orderRequest.getUserId());
            orderEntity.setUser(userEntity);
        }
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderEntity = orderRepository.save(orderEntity);
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        orderHistory.setOrderId(orderEntity);
        orderHistory.setVerifier(authentication1.getUsername());
        orderHistory.setTotal(orderEntity.getTotal());
        orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
        this.orderHistoryRepository.save(orderHistory);
        List<OrderDetailRequest> list = orderRequest.getOrderDetails();
        for (OrderDetailRequest orderDetailRequest : list) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setData(orderDetailRequest);
            orderDetailEntity.setProduct(this.productRepository.getById(orderDetailRequest.getProductId()));
            orderDetailEntity.setOrder(orderEntity);
            orderDetailRepository.save(orderDetailEntity);
        }
        List<CartEntity> listCard = this.cartRepository.findAll();
        if (!orderRequest.getStatus().equals("CHUA_THANH_TOAN")) {
            this.cartRepository.deleteAll(listCard);
        }

        if (orderRequest.getMoney() > 0 && !orderRequest.getPayment().equals("DAT_COC")) {
            System.out.println("----------------- VÀO TRONG TRỪ SỐ LƯỢNG SẢN PHẨM");
            for (OrderDetailRequest orderDetailEntity : orderRequest.getOrderDetails()) {
                ProductEntity product = this.productRepository.getById(orderDetailEntity.getProductId());
                product.setQuantity(product.getQuantity() - orderDetailEntity.getQuantity());
                this.productRepository.save(product);
            }
        }


        return orderEntity;
    }

    @Override
    public OrderEntity saveNoLogin(OrderRequest orderRequest) throws CustomException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setData(orderRequest);
        UserEntity userEntity = null;
        orderEntity.setUser(userEntity);
        orderEntity = orderRepository.save(orderEntity);
        List<OrderDetailRequest> list = orderRequest.getOrderDetails();
        for (OrderDetailRequest orderDetailRequest : list) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setData(orderDetailRequest);
            orderDetailEntity.setProduct(this.productRepository.getById(orderDetailRequest.getProductId()));
            orderDetailEntity.setOrder(orderEntity);
            orderDetailRepository.save(orderDetailEntity);
        }
        for (OrderDetailRequest orderDetailEntity : orderRequest.getOrderDetails()) {
            System.out.println(orderDetailEntity.getProductId());
            ProductEntity product = this.productRepository.getById(orderDetailEntity.getProductId());
            product.setQuantity(product.getQuantity() - orderDetailEntity.getQuantity());
            this.productRepository.save(product);
        }
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
        orderHistory.setOrderId(orderEntity);
        orderHistory.setTotal(orderEntity.getTotal());
        orderHistory.setVerifier(null);
        this.orderHistoryRepository.save(orderHistory);
        return orderEntity;
    }

    @Override
    public OrderEntity saveOfUser(OrderRequest orderRequest) throws CustomException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setData(orderRequest);
        orderEntity.setStatus(String.valueOf(OrderStatus.CHO_XAC_NHAN));
        UserEntity userEntity = userRepository.getById(orderRequest.getUserId());
        orderEntity.setUser(userEntity);
        orderEntity = orderRepository.save(orderEntity);
        List<OrderDetailRequest> list = orderRequest.getOrderDetails();
        for (OrderDetailRequest orderDetailRequest : list) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setData(orderDetailRequest);
            orderDetailEntity.setProduct(this.productRepository.getById(orderDetailRequest.getProductId()));
            orderDetailEntity.setOrder(orderEntity);
            orderDetailRepository.save(orderDetailEntity);
        }
//        for (OrderDetailRequest orderDetailEntity : orderRequest.getOrderDetails()) {
//            System.out.println(orderDetailEntity.getProductId());
//            ProductEntity product = this.productRepository.getById(orderDetailEntity.getProductId());
//            product.setQuantity(product.getQuantity() - orderDetailEntity.getQuantity());
//            this.productRepository.save(product);
//        }
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
        orderHistory.setOrderId(orderEntity);
        orderHistory.setTotal(orderEntity.getTotal());
        orderHistory.setVerifier(authentication1.getUsername());
        this.orderHistoryRepository.save(orderHistory);
        return orderEntity;
    }

    @Override
    public OrderEntity edit(Long id, OrderRequest orderRequest) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id đơn hàng phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id đơn hàng muốn sửa");
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        orderEntity.setData(orderRequest);
        orderEntity = orderRepository.save(orderEntity);
        String status = String.valueOf(orderEntity.getStatus());
        Long orderId = orderEntity.getId();
        System.out.println(orderRequest.getOrderDetails().toArray().length);
        if (orderRequest.getOrderDetails().get(0).getProductId() != null) {
            orderRequest.getOrderDetails().forEach(orderDetailRequest -> {

                Long productId = orderDetailRequest.getProductId();
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity = this.orderDetailRepository.
                        getOrderDetailEntityByOrderIsAndAndProduct(orderId, productId);
                System.out.println(orderDetailEntity.getProduct() + " " + orderDetailEntity.getQuantity() + orderDetailEntity.getTotal());
                if (orderDetailEntity != null) {
                    orderDetailEntity.setId(orderDetailEntity.getId());
                    orderDetailEntity.setStatus(OrderDetailStatus.valueOf(status));
                    orderDetailEntity.setTotal(orderDetailEntity.getProduct().getPrice() * orderDetailRequest.getQuantity());

                    orderDetailEntity.setProduct(orderDetailEntity.getProduct());
                    orderDetailEntity.setIsCheck(orderDetailRequest.getIsCheck());
                    orderDetailEntity.setQuantity(orderDetailRequest.getQuantity());
                    orderDetailEntity.setOrder(orderDetailEntity.getOrder());
                    this.orderDetailRepository.save(orderDetailEntity);
                }

            });
            double count = 0;
            List<OrderDetailEntity> list = this.orderDetailRepository.getOrderDetailEntityById(orderId);
            for (OrderDetailEntity od : list) {
                count += od.getTotal();
            }
            orderEntity.setTotal(count + orderRequest.getShippingFree());
            this.orderRepository.save(orderEntity);

            System.out.println("cập nhật lại lịch sử đơn hàng");
            CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            OrderHistoryEntity orderHistory = new OrderHistoryEntity();
            orderHistory.setStatus(orderRequest.getStatus());
            orderHistory.setOrderId(orderEntity);
            orderHistory.setTotal(orderEntity.getTotal());
            orderHistory.setVerifier(authentication1.getUsername());
            this.orderHistoryRepository.save(orderHistory);
        } else {
            List<OrderDetailEntity> list = this.orderDetailRepository.getOrderDetailEntityById(orderEntity.getId());
            list.forEach(
                    list1 -> {
                        list1.setId(list1.getId());
                        list1.setTotal(list1.getTotal());
                        list1.setProduct(list1.getProduct());
                        list1.setStatus(OrderDetailStatus.valueOf(status));
                        list1.setQuantity(list1.getQuantity());
                        OrderEntity order = orderRepository.getById(orderId);
                        list1.setOrder(order);
                    });
            this.orderDetailRepository.saveAll(list);
        }

        return orderEntity;
    }

    @Override
    public OrderEntity delete(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đơn hàng");
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.getOrderDetails().forEach(orderDetailEntity -> {
            ProductEntity product = this.productRepository.getById(orderDetailEntity.getProduct().getId());
            product.setQuantity(product.getQuantity() + orderDetailEntity.getQuantity());
            this.productRepository.save(product);
        });
        orderRepository.delete(orderEntity);
        return orderEntity;
    }

    @Override
    public Page<OrderEntity> paginate(String searchPayment, String searchName, String searchStatus, String searchPhone, String startDate, String endDate, int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndPaymentAndName(searchPayment, searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.findByPhoneAndStatusAndPaymentAndName(searchPayment, searchName, searchStatus, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndPayment(searchPayment, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndStatusAndPayment(searchPayment, searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndName(searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatus(searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPaymentAndStatus(searchStatus, searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndStatus(searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndStatus(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchNameAndPhoneAndStatus(searchStatus, searchName, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchNameAndPaymentAndStatus(searchStatus, searchName, searchPayment, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchPaymentAndPhoneAndStatus(searchStatus, searchPayment, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty()) {
            return orderRepository.searchNameAndStatus(searchStatus, searchName, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchPaymentAndStatus(searchStatus, searchPayment, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchPhoneAndStatus(searchStatus, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !startDate.isEmpty()) {
            return orderRepository.betweenDateAndStatus(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty()) {
            return orderRepository.findAllAndStatus(searchStatus, specifications, pageable);
        } else if (searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndPaymentAndName(searchPayment, searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndPayment(searchPayment, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndPayment(searchPayment, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndName(searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchNameAndPhone(searchName, searchPhone, specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchNameAndPayment(searchName, searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchPhoneAndPayment(searchPhone, searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhone(searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPayment(searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndName(searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDate(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty()) {
            return orderRepository.searchName(searchName, specifications, pageable);
        } else if (!searchPayment.isEmpty()) {
            return orderRepository.searchPayment(searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty()) {
            return orderRepository.searchPhone(searchPhone, specifications, pageable);
        } else if (!startDate.isEmpty()) {
            return orderRepository.betweenDate(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else {
            return orderRepository.findAll(specifications, pageable);
        }
    }

    @Override
    public Page<OrderEntity> ordersFilterPrice(String searchMoney, String searchPayment, String searchName, String searchStatus, String searchPhone, String startDate, String endDate, int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (searchMoney.equals("0")) {
            if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndPaymentAndNameDontMoney(searchPayment, searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndPaymentDontMoney(searchPayment, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndStatusAndPaymentDontMoney(searchPayment, searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndNameDontMoney(searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusDontMoney(searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPaymentAndStatusDontMoney(searchStatus, searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndStatusDontMoney(searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndStatusDontMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchNameAndPhoneAndStatusDontMoney(searchStatus, searchName, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchNameAndPaymentAndStatusDontMoney(searchStatus, searchName, searchPayment, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchPaymentAndPhoneAndStatusDontMoney(searchStatus, searchPayment, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty()) {
                return orderRepository.searchNameAndStatusDontMoney(searchStatus, searchName, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchPaymentAndStatusDontMoney(searchStatus, searchPayment, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchPhoneAndStatusDontMoney(searchStatus, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !startDate.isEmpty()) {
                return orderRepository.betweenDateAndStatusDontMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty()) {
                return orderRepository.findAllAndStatusDontMoney(searchStatus, specifications, pageable);
            } else if (searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndPaymentAndNameDontMoney(searchPayment, searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndPaymentDontMoney(searchPayment, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndPaymentDontMoney(searchPayment, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndNameDontMoney(searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchNameAndPhoneDontMoney(searchName, searchPhone, specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchNameAndPaymentDontMoney(searchName, searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchPhoneAndPaymentDontMoney(searchPhone, searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneDontMoney(searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPaymentDontMoney(searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameDontMoney(searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateDontMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty()) {
                return orderRepository.searchNameDontMoney(searchName, specifications, pageable);
            } else if (!searchPayment.isEmpty()) {
                return orderRepository.searchPaymentDontMoney(searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty()) {
                return orderRepository.searchPhoneDontMoney(searchPhone, specifications, pageable);
            } else if (!startDate.isEmpty()) {
                return orderRepository.betweenDateDontMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else {
                return orderRepository.findAllDontMoney(specifications, pageable);
            }
        } else {
            if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndPaymentAndNameMoney(searchPayment, searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndPaymentMoney(searchPayment, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndStatusAndPaymentMoney(searchPayment, searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusAndNameMoney(searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndStatusMoney(searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPaymentAndStatusMoney(searchStatus, searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndStatusMoney(searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndStatusMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchNameAndPhoneAndStatusMoney(searchStatus, searchName, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchNameAndPaymentAndStatusMoney(searchStatus, searchName, searchPayment, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchPaymentAndPhoneAndStatusMoney(searchStatus, searchPayment, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchName.isEmpty()) {
                return orderRepository.searchNameAndStatusMoney(searchStatus, searchName, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchPaymentAndStatusMoney(searchStatus, searchPayment, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchPhoneAndStatusMoney(searchStatus, searchPhone, specifications, pageable);
            } else if (!searchStatus.isEmpty() && !startDate.isEmpty()) {
                return orderRepository.betweenDateAndStatusMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchStatus.isEmpty()) {
                return orderRepository.findAllAndStatusMoney(searchStatus, specifications, pageable);
            } else if (searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndPaymentAndNameMoney(searchPayment, searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndPaymentMoney(searchPayment, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameAndPaymentMoney(searchPayment, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneAndNameMoney(searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPhone.isEmpty()) {
                return orderRepository.searchNameAndPhoneMoney(searchName, searchPhone, specifications, pageable);
            } else if (!searchName.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchNameAndPaymentMoney(searchName, searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty() && !searchPayment.isEmpty()) {
                return orderRepository.searchPhoneAndPaymentMoney(searchPhone, searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPhoneMoney(searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndPaymentMoney(searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateAndNameMoney(searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!startDate.isEmpty() && !endDate.isEmpty()) {
                return orderRepository.betweenDateMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else if (!searchName.isEmpty()) {
                return orderRepository.searchNameMoney(searchName, specifications, pageable);
            } else if (!searchPayment.isEmpty()) {
                return orderRepository.searchPaymentMoney(searchPayment, specifications, pageable);
            } else if (!searchPhone.isEmpty()) {
                return orderRepository.searchPhoneMoney(searchPhone, specifications, pageable);
            } else if (!startDate.isEmpty()) {
                return orderRepository.betweenDateMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
            } else {
                return orderRepository.findAllMoney(specifications, pageable);
            }
        }
    }

    @Override
    public Page<OrderEntity> orderFinishMoney(String searchPayment, String searchName, String searchStatus, String searchPhone, String startDate, String endDate, int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndPaymentAndNameFinishMoney(searchPayment, searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndPaymentFinishMoney(searchPayment, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndStatusAndPaymentFinishMoney(searchPayment, searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusAndNameFinishMoney(searchName, searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndStatusFinishMoney(searchStatus, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPaymentAndStatusFinishMoney(searchStatus, searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndStatusFinishMoney(searchStatus, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndStatusFinishMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchNameAndPhoneAndStatusFinishMoney(searchStatus, searchName, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchNameAndPaymentAndStatusFinishMoney(searchStatus, searchName, searchPayment, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchPaymentAndPhoneAndStatusFinishMoney(searchStatus, searchPayment, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchName.isEmpty()) {
            return orderRepository.searchNameAndStatusFinishMoney(searchStatus, searchName, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchPaymentAndStatusFinishMoney(searchStatus, searchPayment, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchPhoneAndStatusFinishMoney(searchStatus, searchPhone, specifications, pageable);
        } else if (!searchStatus.isEmpty() && !startDate.isEmpty()) {
            return orderRepository.betweenDateAndStatusFinishMoney(searchStatus, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchStatus.isEmpty()) {
            return orderRepository.findAllAndStatusFinishMoney(searchStatus, specifications, pageable);
        } else if (searchPayment.isEmpty() && !searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndPaymentAndNameFinishMoney(searchPayment, searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndPaymentFinishMoney(searchPayment, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameAndPaymentFinishMoney(searchPayment, searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneAndNameFinishMoney(searchName, searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPhone.isEmpty()) {
            return orderRepository.searchNameAndPhoneFinishMoney(searchName, searchPhone, specifications, pageable);
        } else if (!searchName.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchNameAndPaymentFinishMoney(searchName, searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty() && !searchPayment.isEmpty()) {
            return orderRepository.searchPhoneAndPaymentFinishMoney(searchPhone, searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPhoneFinishMoney(searchPhone, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchPayment.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndPaymentFinishMoney(searchPayment, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateAndNameFinishMoney(searchName, LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!startDate.isEmpty() && !endDate.isEmpty()) {
            return orderRepository.betweenDateFinishMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else if (!searchName.isEmpty()) {
            return orderRepository.searchNameFinishMoney(searchName, specifications, pageable);
        } else if (!searchPayment.isEmpty()) {
            return orderRepository.searchPaymentFinishMoney(searchPayment, specifications, pageable);
        } else if (!searchPhone.isEmpty()) {
            return orderRepository.searchPhoneFinishMoney(searchPhone, specifications, pageable);
        } else if (!startDate.isEmpty()) {
            return orderRepository.betweenDateFinishMoney(LocalDateTime.parse(startDate, dateTimeFormatter), LocalDateTime.parse(endDate, dateTimeFormatter), specifications, pageable);
        } else {
            return orderRepository.findAllFinishMoney(specifications, pageable);
        }
    }

    @Override
    public UserEntity createUser(CreateUserOnOrderRequest createUserOnOrderRequest) throws CustomException {
        Optional<UserEntity> userEntityOptional = userRepository.validateUserName(createUserOnOrderRequest.getUsername());
        if (userEntityOptional.isPresent()) {
            throw new CustomException(403, "Tài khoản đã tồn tại!");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserOnOrderRequest.getUsername());
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        userEntity.setPassword(b.encode(createUserOnOrderRequest.getNewPassword()));
        userEntity.setStatus(1);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = userRepository.findRoleCustomer();
        roleEntityList.add(roleEntity);
        userEntity.setRoles(roleEntityList);
        userEntity = userRepository.save(userEntity);
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setFullName(createUserOnOrderRequest.getFullName());
        informationEntity.setEmail(createUserOnOrderRequest.getEmail());
        informationEntity.setPhoneNumber(createUserOnOrderRequest.getPhoneNumber());
        informationEntity.setAddress(createUserOnOrderRequest.getAddress());
        informationEntity.setUser(userEntity);
        informationEntity.setActive(1);
        informationRepository.save(informationEntity);
        return userEntity;
    }

    @Override
    public List<OrderDetailDTO> findByOrder(Long id) {
        List<OrderDetailEntity> orderDEntity = this.orderDetailRepository.findByOrder(id);
        this.enrichProduct(orderDEntity);
        ModelMapper modelMapper = new ModelMapper();
        List<OrderDetailDTO> dtos = orderDEntity
                .stream()
                .map(user -> modelMapper.map(user, OrderDetailDTO.class))
                .collect(Collectors.toList());

        return dtos;

    }

    private void enrichProduct(List<OrderDetailEntity> orderDEntity) {
        orderDEntity.forEach(orderDetailEntity -> {
            ProductEntity product = this.productRepository.getById(orderDetailEntity.getProduct().getId());
            orderDetailEntity.enrichProduct(product);
        });
    }

    @Override
    public OrderByIdDTO findById(Long id) {
        OrderEntity orderEntity = this.orderRepository.getById(id);
        ModelMapper modelMapper = new ModelMapper();
        OrderByIdDTO orderDTO = modelMapper.map(orderEntity, OrderByIdDTO.class);
        return orderDTO;
    }

    @Override
    public OrderEntity cancelled(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "ID hóa đơn phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy id hóa đơn muốn hủy");
        }
        OrderEntity order = orderEntityOptional.get();
        order.setStatus("DA_HUY");
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public OrderEntity received(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "ID hóa đơn phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy id hóa đơn muốn hủy");
        }
        OrderEntity order = orderEntityOptional.get();
        order.setStatus("DA_NHAN");
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public OrderDetailDTO update(Long id, OrderDetailRequest orderDetailRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Integer idCheck = Math.toIntExact(id);
        OrderDetailDTO orderDTO = new OrderDetailDTO();
        OrderDetailEntity orderDetailEntity = this.orderDetailRepository.getById(id);
        OrderDetailEntity list = this.orderDetailRepository.getOrderDetailByIsCheckAndProductId(idCheck, orderDetailRequest.getProductId());
        if (orderDetailRequest.getIsUpdate() == null) {
            OrderDetailEntity orderDetail = this.orderDetailRepository.getById(orderDetailEntity.getId());
            if (list != null) {
                list.setIsCheck(1);
            }
            orderDetailEntity.setIsCheck(2);
            this.orderDetailRepository.save(orderDetail);
            orderDTO = modelMapper.map(orderDetail, OrderDetailDTO.class);
            orderDetailEntity.setQuantity(orderDetailEntity.getQuantity() - 1);
            if (orderDetailEntity.getQuantity() == 0) {
                orderDetailEntity.setTotal(0);
                orderDetailEntity.setIsCheck(10);
                this.orderDetailRepository.save(orderDetailEntity);
            } else {
                this.orderDetailRepository.save(orderDetailEntity);
                orderDTO = modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
            }
        } else if (orderDetailRequest.getIsUpdate() == 2) {
            orderDetailEntity.setIsCheck(2);

            this.orderDetailRepository.save(orderDetailEntity);
        } else {

            orderDetailEntity.setProduct(orderDetailEntity.getProduct());
            orderDetailEntity.setIsCheck(1);
            this.orderDetailRepository.save(orderDetailEntity);
            orderDTO = modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
        }
        return orderDTO;
    }

    @Override
    public UpdateOrderDetailDTO findByOrderDetailDTO(Long id, UpdateOrderDetailRequest orderDetailRequest) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDetailEntity orderDetailEntity = this.orderDetailRepository.
                getOrderDetailEntityByOrderIsAndAndProduct(id, orderDetailRequest.getProductId());
        if (orderDetailRequest.getQuantity() == orderDetailEntity.getQuantity()) {
            orderDetailEntity.setTotal(0);
        } else if (orderDetailRequest.getQuantity() < orderDetailEntity.getQuantity()) {
            ProductEntity product = this.productRepository.getById(orderDetailRequest.getProductId());
            Double price = product.getPrice();
            int quantityRequest = orderDetailRequest.getQuantity();
            int quantityOD = orderDetailEntity.getQuantity();
            int quantity = quantityOD - quantityRequest;
            Double total = price * quantity;
            orderDetailEntity.setTotal(total);
            orderDetailEntity.setQuantity(orderDetailEntity.getQuantity() - orderDetailRequest.getQuantity());
            product.setQuantity(product.getQuantity() - orderDetailRequest.getQuantity());
            this.productRepository.save(product);
        }
        this.orderDetailRepository.save(orderDetailEntity);
        UpdateOrderDetailDTO orderDTO = modelMapper.map(orderDetailEntity, UpdateOrderDetailDTO.class);
        return orderDTO;
    }

    @Override
    public List<OrderEntity> findUserName(String username) {
        return orderRepository.findUserName(username);
    }

    @Override
    public List<OrderEntity> findPhone(String phone) {
        return orderRepository.findPhone(phone);
    }

    public List<OrderEntity> findByDate(String createdAt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (createdAt == null || createdAt == "" || createdAt.isEmpty()) {
            return orderRepository.findAll();
        } else {
            return orderRepository.findByDate(LocalDateTime.parse(createdAt, dateTimeFormatter));
        }
    }

    @Override
    public List<OrderDetailEntity> createOrderDetail(List<ExchangeRequest> exchangeRequest) {
        OrderEntity orderEntity = this.orderRepository.getById(exchangeRequest.stream().findFirst().get().getOrderId());
        List<OrderDetailEntity> list = new ArrayList<>();
        exchangeRequest.forEach(exchangeEntity -> {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrder(orderEntity);
            orderDetailEntity.setQuantity(exchangeEntity.getQuantity());
            orderDetailEntity.setProduct(this.productRepository.getById(exchangeEntity.getProductId()));
            orderDetailEntity.setTotal(this.productRepository.getById(exchangeEntity.getProductId()).getPrice());
            orderDetailEntity.setStatus(OrderDetailStatus.CHO_XAC_NHAN);
            orderDetailEntity.setIsCheck(exchangeEntity.getIsCheck());
            this.orderDetailRepository.save(orderDetailEntity);
            list.add(orderDetailEntity);
        });

        return list;
    }

    @Override
    public List<OrderDetailEntity> createOrderDetail2(List<ExchangeRequest3> exchangeRequest) {

        System.out.println(exchangeRequest.stream().findFirst().get().getOrderId());
        List<OrderDetailEntity> list = new ArrayList<>();
        Set<Long> itemId = new HashSet<>();

        exchangeRequest.forEach(exchangeEntity -> {
            System.out.println("quantity: " + exchangeEntity.getQuantity());
            OrderEntity orderEntity = this.orderRepository.getById(exchangeRequest.stream().findFirst().get().getOrderId());
            System.out.println("Total order; " + exchangeRequest.stream().findFirst().get().getTotalOrder());
            Optional<OrderDetailEntity> optional = orderEntity.getOrderDetails().stream().filter(o -> o.getProduct().getId()
                    == exchangeEntity.getProductId() && o.getStatus() != OrderDetailStatus.DA_HUY).findFirst();
            if (optional.isPresent()) {
                OrderDetailEntity oldOrderDetailEntity = optional.get();
                Integer oldQuantity = exchangeEntity.getQuantity() + oldOrderDetailEntity.getQuantity();
                oldOrderDetailEntity.setQuantity(exchangeEntity.getQuantity() + oldOrderDetailEntity.getQuantity());
                System.out.println("Tổng tiền: " + Integer.valueOf((int) (oldOrderDetailEntity.getProduct().getPrice() *
                        oldQuantity)));
                oldOrderDetailEntity.setTotal(Integer.valueOf((int) (oldOrderDetailEntity.getProduct().getPrice() *
                        oldQuantity)));
            } else {
                OrderDetailEntity newOrderDetailEntity = new OrderDetailEntity();
                newOrderDetailEntity.setOrder(orderEntity);
                newOrderDetailEntity.setQuantity(exchangeEntity.getQuantity());
                newOrderDetailEntity.setProduct(this.productRepository.getById(exchangeEntity.getProductId()));
                newOrderDetailEntity.setTotal(this.productRepository.getById(exchangeEntity.getProductId()).getPrice());
                newOrderDetailEntity.setStatus(OrderDetailStatus.CHO_XAC_NHAN);
                this.orderDetailRepository.save(newOrderDetailEntity);
            }
        });
        OrderEntity orderEntity = this.orderRepository.getById(exchangeRequest.stream().findFirst().get().getOrderId());
        List<OrderDetailEntity> listOrder = this.orderDetailRepository.getOrderDetailEntityById(orderEntity.getId());
        double count = 0;

        for (OrderDetailEntity od : listOrder) {
            count += od.getTotal();
        }
        System.out.println(count);
        if (count < 0) {
            orderEntity.setTotal(0);
        } else {
            if (exchangeRequest.stream().findFirst().get().getShipping() == null) {
                orderEntity.setTotal(count);
            } else {
                orderEntity.setTotal(count + exchangeRequest.stream().findFirst().get().getShipping());
            }

        }
//        orderEntity.setTotal(exchangeRequest.stream().findFirst().get().getTotalOrder() + exchangeRequest.stream().findFirst().get().getShipping());
        if (exchangeRequest.stream().findFirst().get().getShipping() != null) {
            orderEntity.setShippingFree(exchangeRequest.stream().findFirst().get().getShipping());
        }

        this.orderRepository.save(orderEntity);


        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
        orderHistory.setOrderId(orderEntity);
        orderHistory.setTotal(orderEntity.getTotal());
        orderHistory.setVerifier(authentication1.getUsername());
        this.orderHistoryRepository.save(orderHistory);
        return list;
    }

    @Override
    public OrderDetailDTO updateOrderDetail(Long id, OrderDetailRequest orderDetailRequest) {
        OrderDetailEntity orderDetailEntity = this.orderDetailRepository.getById(id);
        OrderEntity order = this.orderRepository.getById(orderDetailEntity.getOrder().getId());
        System.out.println("order id :" + order.getId());
        System.out.println("isCheck: "+ orderDetailEntity.getIsCheck());
        System.out.println("order detail id :" + orderDetailEntity.getId());
        List<OrderDetailEntity> list = this.orderDetailRepository.findByOrderAndIscheck2(order.getId(), Math.toIntExact(orderDetailEntity.getId()));
        System.out.println("list: "+ list.size());
        list.forEach(orderDetailEntity1 -> {
            System.out.println("item: " + orderDetailEntity1.getId());
        });
        if(orderDetailEntity.getQuantity() == list.size()) {
            System.out.println("--------- vào if");
            orderDetailEntity.setIsCheck(0);
        }else if(orderDetailEntity.getQuantity() > list.size()) {
            System.out.println("------ vào else");
            Integer quantity =  orderDetailEntity.getQuantity() - list.size();
            orderDetailEntity.setIsCheck(-quantity);
        }
//        System.out.println("Số lượng sản phẩm truyền vào: "+ orderDetailRequest.getQuantity());
//        Integer quantity = orderDetailEntity.getQuantity() - orderDetailRequest.getQuantity();
//        System.out.println("số lượng sau khi cập nhật lại" + quantity);
//        if(quantity > 0 ) {
//            orderDetailEntity.setIsCheck(-quantity);
//        }else  {
//            orderDetailEntity.setIsCheck(0);
//        }
        this.orderDetailRepository.save(orderDetailEntity);
        ModelMapper modelMapper = new ModelMapper();
        OrderDetailDTO orderDetailDTO = modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
        return orderDetailDTO;
    }

    @Override
    public UpdateOrderDetailDTO updateWithReturn(
            Long orderId, Long orderDetailId,
            UpdateOrderDetailRequest orderDetailRequest
    ) {
        OrderEntity orderEntity = this.orderRepository.getById(orderId);
        OrderDetailEntity orderDetailEntity = this.orderDetailRepository.findByIdAndOrderId(orderDetailId, orderId);
        if (orderDetailEntity != null) {
            int quantity = orderDetailEntity.getQuantity() - orderDetailRequest.getQuantity();
            System.out.println("quantity: " + quantity);
            orderDetailEntity.setQuantity(quantity);
            orderDetailEntity.setTotal(orderDetailEntity.getProduct().getPrice() * quantity);
            this.orderDetailRepository.save(orderDetailEntity);
            System.out.println("số lượng: " + orderDetailEntity.getQuantity());
            System.out.println("tổng tiền: " + orderDetailEntity.getTotal());
            double count = 0;
            List<OrderDetailEntity> list = this.orderDetailRepository.getOrderDetailEntityById(orderId);
            for (OrderDetailEntity od : list) {
                count += od.getTotal();
            }
            System.out.println(count);
            if (count < 0) {
                orderEntity.setTotal(0);
            } else {
                orderEntity.setTotal(count);
            }

            this.orderRepository.save(orderEntity);
        } else {
            throw new RuntimeException("không tìm thấy hoá đơn chi tiết");
        }
        ModelMapper modelMapper = new ModelMapper();
        UpdateOrderDetailDTO orderDetailDTO = modelMapper.map(orderDetailEntity, UpdateOrderDetailDTO.class);
        return orderDetailDTO;
    }

    @Override
    public List<OrderConfirmDTO> findByIdOrderId(List<OrderConfirmDTO> id) {

        id.forEach(orderId -> {
            OrderEntity orderEntity = this.orderRepository.getById(orderId.getId());
            List<OrderDetailEntity> orderDetail = this.orderDetailRepository.findByOrder(orderEntity.getId());
            if (!orderId.getStatus().equals("DA_HUY") && orderEntity.getMoney() == 0) {
                orderEntity.setMoney(orderEntity.getTotal());
            }
            if (orderId.getStatus().equals("DA_HUY") && !orderEntity.getStatus().equals("CHO_XAC_NHAN")) {
                System.out.println("vào công lại số lượng");
                orderDetail.forEach(orderDetailEntity1 -> {
                    ProductEntity product = this.productRepository.getById(orderDetailEntity1.getProduct().getId());
                    product.setQuantity(product.getQuantity() + orderDetailEntity1.getQuantity());
                    this.productRepository.save(product);
                });
                this.orderRepository.save(orderEntity);
            }
            if (orderId.getStatus().equals("CHO_LAY_HANG") || orderId.getStatus().equals("DA_NHAN")) {
                for (OrderDetailEntity orderDetailEntity : orderDetail) {
                    System.out.println("------- vào trừ số lượng sản phẩm ----------");
                    ProductEntity product = this.productRepository.getById(orderDetailEntity.getProduct().getId());
                    product.setQuantity(product.getQuantity() - orderDetailEntity.getQuantity());
                    this.productRepository.save(product);
                }
            }
            System.out.println("Phương thức thang toán" + orderEntity.getPayment());
            System.out.println("Trạng thái đơn hàng: " + orderId.getStatus());
            OrderHistoryEntity orderHistory = new OrderHistoryEntity();
            orderEntity.setStatus(orderId.getStatus());
            if (orderId.getStatus().equals("DA_HUY") && orderEntity.getPayment().equals("NGAN_HANG")) {
                System.out.println("----------------- vào TH1");
                orderEntity.setMoney(0);
            } else if (orderId.getStatus().equals("DA_HUY") && orderEntity.getPayment().equals("DAT_COC")) {
                orderEntity.setMoney(orderEntity.getMoney());
                System.out.println("----------------- vào TH2");
            }
//            else if (orderId.getStatus().equals("DA_HUY")
//                    && orderEntity.getTotal() == orderEntity.getMoney()
//            ) {
//                orderEntity.setMoney(orderEntity.getTotal() / 10);
//                System.out.println("----------------- vào TH3");
//            }

            if (!orderEntity.getStatus().equals("CHO_XAC_NHAN")) {
                System.out.println("Đơn hàng khác chờ xác nhận");
                System.out.println(orderEntity.getStatus());
            }

            CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
            orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
            orderHistory.setOrderId(orderEntity);
            orderHistory.setTotal(orderEntity.getTotal());
            orderHistory.setVerifier(authentication1.getUsername());
            this.orderHistoryRepository.save(orderHistory);
        });
        return null;
    }

    @Override
    public OrderConfirmDTO findByIdOrderId(OrderConfirmDTO request) {
        OrderEntity orderEntity = this.orderRepository.getById(request.getId());
        orderEntity.setShippingFree(0);
        orderEntity.setTotal(0);
        orderEntity.setStatus(String.valueOf(OrderDetailStatus.DA_HUY));
        this.orderRepository.save(orderEntity);
        orderEntity.getOrderDetails().forEach(orderDetailEntity -> {
            orderDetailEntity.setQuantity(0);
            orderDetailEntity.setTotal(0);
            this.orderDetailRepository.save(orderDetailEntity);
        });
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderHistory.setStatus(String.valueOf(orderEntity.getStatus()));
        orderHistory.setOrderId(orderEntity);
        orderHistory.setTotal(orderEntity.getTotal());
        orderHistory.setVerifier(authentication1.getUsername());
        this.orderHistoryRepository.save(orderHistory);
        return null;
    }

    @Override
    public List<OrderExchangeDTO> updateWhenExchangeCancel(List<OrderExchangeDTO> request, Long orderId) {
        request.forEach(orderExchangeDTO -> {
            OrderDetailEntity orderDetail = this.orderDetailRepository.getById(Long.valueOf(orderExchangeDTO.getIsCheck()));
            orderDetail.setIsCheck(3);

            orderDetail.setTotal(0);
            this.orderDetailRepository.save(orderDetail);
            if (orderExchangeDTO.getIsBoolean().equals("false")) {
                ExchangeDetailEntity exchangeDetail = this.exchangeDetailRepository.getByOrderChange(Math.toIntExact(orderDetail.getId()));
                exchangeDetail.setStatus(ReturnDetailStatus.KHONG_XAC_NHAN);
                this.exchangeDetailRepository.save(exchangeDetail);
                ExchangeEntity exchangeEntity = this.exchangeRepository.getById(exchangeDetail.getExchange().getId());
                exchangeEntity.setStatus("DA_XU_LY");
                this.exchangeRepository.save(exchangeEntity);
            }
        });
        return null;
    }

    @Override
    public OrderDetailDTO updateInCancel(Long id, OrderDetailRequest odRequest) {
        OrderDetailEntity od = this.orderDetailRepository.getById(id);
        od.setTotal(0);
        od.setStatus(OrderDetailStatus.DA_HUY);
        this.orderDetailRepository.save(od);
        double count = 0;
        OrderEntity order = this.orderRepository.getById(od.getOrder().getId());
        List<OrderDetailEntity> list = this.orderDetailRepository.findByOrderAndIscheck(od.getOrder().getId());
        for (OrderDetailEntity od1 : list) {
            count += od1.getTotal();
        }
        System.out.println(count);
        if (count < 0) {
            order.setTotal(0);
        } else {
            order.setTotal(count);
        }

        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderHistory.setStatus(String.valueOf(order.getStatus()));
        orderHistory.setOrderId(order);
        orderHistory.setTotal(order.getTotal());
        orderHistory.setVerifier(authentication1.getUsername());
        this.orderHistoryRepository.save(orderHistory);
        return null;
    }

    @Override
    public List<OrderConfirmDTO> findByIdOrderId2(List<OrderConfirmDTO> requests) {
        requests.forEach(orderConfirmDTO -> {
            OrderEntity order = this.orderRepository.getById(orderConfirmDTO.getId());
            order.setMoney(order.getTotal() / 10);
            this.orderRepository.save(order);
        });
        return null;
    }

    @Override
    public List<OrderConfirmDTO> findByIdOrderId3(List<OrderConfirmDTO> requests) {
        requests.forEach(orderConfirmDTO -> {
            OrderEntity orderEntity = this.orderRepository.getById(orderConfirmDTO.getId());
            orderEntity.setMoney(orderEntity.getTotal());
            orderEntity.setStatus(orderConfirmDTO.getStatus());
            this.orderRepository.save(orderEntity);
        });
        return null;
    }

    @Override
    public List<OrderExchangeDTO> updateWhenExchange(List<OrderExchangeDTO> request, Long orderId) {


        System.out.println(request.size());
        request.forEach(orderExchangeDTO -> {
            if (orderExchangeDTO.getIsBoolean().equals("true")) {
                System.out.println(orderExchangeDTO.getId());
                Integer Id = Math.toIntExact(orderExchangeDTO.getId());
                System.out.println("isCheck:" + Id);
                System.out.println("productId trong isCheck:" + orderExchangeDTO.getProductId());
                //sản phẩm gửi
                OrderDetailEntity orderDetail = this.orderDetailRepository.getById(Long.valueOf(orderExchangeDTO.getIsCheck()));
                //sản phẩm trước đó
                OrderDetailEntity orderDetailEntity = this.orderDetailRepository.getById(Long.valueOf(orderDetail.getIsCheck()));
                System.out.println("----- sản phẩm trước đó:");
                System.out.println(orderDetailEntity.getId());
                System.out.println(orderDetailEntity.getProduct().getId());
                System.out.println(orderDetailEntity.getQuantity());

                ExchangeDetailEntity exchangeDetail = this.exchangeDetailRepository.
                        getByOrderChange(Math.toIntExact(orderDetail.getId()));

                if (exchangeDetail.getIsCheck() == null) {
                    // cộng lại sản phẩm nếu đổi hàng thành công
                    ProductEntity product2 = this.productRepository.getById(orderDetailEntity.getProduct().getId());
                    System.out.println("---- Cộng số lượng sản phẩm nếu đổi hàng thành công");
                    product2.setQuantity(product2.getQuantity() + orderExchangeDTO.getQuantity());
                    this.productRepository.save(product2);
                }

                //trừ số lượng sản phẩm nếu đổi hàng thành công
                ProductEntity product = this.productRepository.getById(orderExchangeDTO.getProductId());
                System.out.println("---- Trù số lượng sản phẩm nếu đổi hàng thành công");
                product.setQuantity(product.getQuantity() - orderExchangeDTO.getQuantity());
                this.productRepository.save(product);


                if (orderExchangeDTO.getStatus().equals("1")) {
                    System.out.println("------------ vào if đầu tiên");
                    ProductEntity productEntity = this.productRepository.getById(orderDetailEntity.getProduct().getId());
                    productEntity.setQuantity(product.getQuantity() + orderExchangeDTO.getQuantity());
                    this.productRepository.save(productEntity);
                }

                if (orderDetail.getQuantity() == orderDetailEntity.getQuantity()) {
                    System.out.println("------ vào if thứ 2");
                    orderDetailEntity.setTotal(0);
                    orderDetailEntity.setQuantity(0);
                    this.orderDetailRepository.save(orderDetailEntity);
                    orderDetail.setIsCheck(1);
                    this.orderDetailRepository.save(orderDetail);

                } else if (orderDetailEntity.getQuantity() > 0 && orderDetailEntity.getQuantity() > orderDetail.getQuantity()) {
                    System.out.println("--------- vào if cuối cùng");
                    int quantity = Integer.valueOf(orderDetailEntity.getQuantity()) - Integer.valueOf(orderDetail.getQuantity());

                    orderDetailEntity.setTotal(
                            orderDetailEntity.getProduct().getPrice() * quantity);
                    orderDetailEntity.setQuantity(orderDetailEntity.getQuantity() - orderDetail.getQuantity());
                    this.orderDetailRepository.save(orderDetailEntity);
                    orderDetail.setIsCheck(1);
                    this.orderDetailRepository.save(orderDetail);
                }
            }
        });
        OrderEntity order = this.orderRepository.getById(orderId);
        double count = 0;
        List<OrderDetailEntity> list = this.orderDetailRepository.findByOrderAndIscheck(order.getId());
        for (OrderDetailEntity od : list) {
            count += od.getTotal();
        }
        System.out.println(count);
        if (count < 0) {
            order.setTotal(0);
        } else {
            order.setTotal(count);
            order.setMoney(count);
        }
        return null;
    }

    @Override
    public List<StatisticalMonthDTO> statisticalByYear(Integer year) {
        List<StatisticalMonthDTO> list = this.orderRepository.statisticalByYear(year);
        HashMap<Integer, Double> map = new HashMap<>();

        list.forEach((item) -> {
            map.put(item.getMonth(), item.getTotal());
        });
        list.clear();
        for (int i = 1; i <= 12; i++) {
            StatisticalMonthDTO ob = new StatisticalMonthDTO();
            if (map.containsKey(i)) {
                ob.setMonth(i);
                ob.setTotal(map.get(i).doubleValue());
            } else {
                ob.setMonth(i);
                ob.setTotal(0);
            }

            list.add(ob);
        }
        return list;
    }

    @Override
    public List<StatisticalOrderDTO> statisticalByOrder(Integer month, Integer year) {
        List<StatisticalOrderDTO> order = this.orderRepository.statisticalByOrder(month, year);
        System.out.println(order.size());
        return order;
    }

    @Override
    public List<StatisticalProductDTO> statisticalByProduct() {
        List<StatisticalProductDTO> order = this.orderRepository.statisticalByProduct();
        return order;
    }

    @Override
    public SumProductDTO countOrder() {
        SumProductDTO order = this.orderRepository.countOrder();
        return order;
    }

    @Override
    public ImageDTO addImageOrder(ImageOrderRequest request) {

        ImagesEntity imagesEntity = new ImagesEntity();
        imagesEntity.setName(request.getName());
        imagesEntity.setOrder(this.orderRepository.getById(request.getOrder_id()));
        this.imageRepository.save(imagesEntity);
        ModelMapper modelMapper = new ModelMapper();
        ImageDTO imageDTO = modelMapper.map(imagesEntity, ImageDTO.class);
        OrderEntity order = this.orderRepository.getById(request.getOrder_id());
        OrderHistoryEntity orderHistory = new OrderHistoryEntity();
        CustomUserDetails authentication1 = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Tài khoàn đang đăng nhập" + authentication1.getUsername());
        orderHistory.setStatus(String.valueOf(OrderStatus.CHO_XAC_NHAN));
        orderHistory.setOrderId(order);
        orderHistory.setTotal(order.getTotal());
        orderHistory.setVerifier(authentication1.getUsername());
        this.orderHistoryRepository.save(orderHistory);
        return imageDTO;
    }

    @Override
    public OrderEntity updateNoteRequest(Long id, UpdateNoteRequest request) {
        OrderEntity order = this.orderRepository.getById(id);
        order.setNote(request.getNote());
        return order;
    }

    @Override
    public SumProductDTO numberOfProductsSold() {
        SumProductDTO order = this.orderRepository.numberOfProductsSold();
        return order;
    }


}
