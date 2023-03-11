package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.CartRepository;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.CartRequest;
import datnnhom12api.service.CartService;
import datnnhom12api.specifications.CartSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("cartService")
@Transactional(rollbackFor = Throwable.class)
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public CartEntity create(CartRequest request) throws CustomException {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setData(request);
        ProductEntity product = this.productRepository.getById(request.getProductId());
        cartEntity.setProduct(product);
        cartEntity = cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public Page<CartEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<CartEntity> specifications = CartSpecifications.getInstance().getQueryResult(filters);

        return cartRepository.findAll(specifications, pageable);
    }

    @Override
    public CartEntity update(Long id, CartRequest post) throws CustomException {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (cartEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        CartEntity cartEntity = cartEntityOptional.get();
        cartEntity.setData(post);
        cartEntity = cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public CartEntity delete(Long id) throws CustomException {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (cartEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        CartEntity cartEntity = cartRepository.getById(id);
        cartRepository.delete(cartEntity);
        return cartEntity;
    }
}
