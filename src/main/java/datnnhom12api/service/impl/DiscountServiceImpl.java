package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.DiscountRepository;
import datnnhom12api.request. DiscountRequest;
import datnnhom12api.service.DiscountService;
import datnnhom12api.specifications.DiscountSpecifications;
import datnnhom12api.utils.support.DiscountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("discountService")
@Transactional(rollbackFor = Throwable.class)
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    @Override
    public DiscountEntity save( DiscountRequest  discountRequest) throws CustomException {
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setData( discountRequest);
        discountEntity = discountRepository.save(discountEntity);
        return discountEntity;
    }

    @Override
    public DiscountEntity edit(Long id,  DiscountRequest discountRequest) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id giảm giá phải lớn hơn 0");
        }
        if (discountEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id giảm giá muốn sửa");
        }
        DiscountEntity discountEntity = discountEntityOptional.get();
        discountEntity.setData(discountRequest);
        discountEntity = discountRepository.save(discountEntity);
        return discountEntity;
    }
    @Override
    public DiscountEntity active(Long id) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id giảm giá phải lớn hơn 0");
        }
        if (discountEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id giảm giá muốn active");
        }
        DiscountEntity discountEntity = discountEntityOptional.get();
        discountEntity.setStatus(DiscountStatus.ACTIVE);
        discountEntity = discountRepository.save(discountEntity);
        return discountEntity;
    }

    @Override
    public DiscountEntity inActive(Long id) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id giảm giá phải lớn hơn 0");
        }
        if (discountEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id giảm giá muốn active");
        }
        DiscountEntity discountEntity = discountEntityOptional.get();
        discountEntity.setStatus(DiscountStatus.INACTIVE);
        discountEntity = discountRepository.save(discountEntity);
        return discountEntity;
    }

    @Override
    public DiscountEntity delete(Long id) throws CustomException{
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        if (discountEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        DiscountEntity discountEntity = discountRepository.getById(id);
        discountRepository.delete(discountEntity);
        return discountEntity;
    }

    @Override
    public DiscountEntity getByIdDiscount(Long id) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        if (discountEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        DiscountEntity discountEntity = discountRepository.getById(id);
        return discountEntity;
    }

    @Override
    public Page<DiscountEntity> paginate(String startDate, String endDate, int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<DiscountEntity> specifications = DiscountSpecifications.getInstance().getQueryResult(filters);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(startDate==null||endDate==null||startDate==""||endDate==""||startDate.isEmpty()||endDate.isEmpty()){
            return discountRepository.findAll(pageable);
        }else{
            return discountRepository.betweenDate(LocalDateTime.parse(startDate,dateTimeFormatter),LocalDateTime.parse(endDate,dateTimeFormatter), pageable);
        }

    }
}
