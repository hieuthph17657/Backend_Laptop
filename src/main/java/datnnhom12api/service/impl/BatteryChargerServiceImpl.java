package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.BatteryChargerRepository;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.service.BatteryChargerService;
import datnnhom12api.specifications.BatteryChargerSpecifications;
import datnnhom12api.utils.support.BatteryChargerStatus;
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

@Service("batteryChargerService")
@Transactional(rollbackFor = Throwable.class)
public class BatteryChargerServiceImpl implements BatteryChargerService {
    @Autowired
    BatteryChargerRepository batteryChargerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public BatteryChargerEntity create(BatteryChargerRequest request) throws CustomException {
        BatteryChargerEntity batteryChargerEntity = new BatteryChargerEntity();
        batteryChargerEntity.setData(request);
//        ProductEntity product = this.productRepository.getById(request.getProductId());
//        batteryChargerEntity.setProductId(product);
        batteryChargerEntity = batteryChargerRepository.save(batteryChargerEntity);
        return batteryChargerEntity;
    }

    @Override
    public Page<BatteryChargerEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<BatteryChargerEntity> specifications = BatteryChargerSpecifications.getInstance().getQueryResult(filters);

        return batteryChargerRepository.findAll(specifications, pageable);
    }

    @Override
    public BatteryChargerEntity update(Long id, BatteryChargerRequest post) throws CustomException {
        Optional<BatteryChargerEntity> batteryChargerEntityOptional = batteryChargerRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (batteryChargerEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        BatteryChargerEntity batteryChargerEntity = batteryChargerEntityOptional.get();
        batteryChargerEntity.setData(post);
        batteryChargerEntity = batteryChargerRepository.save(batteryChargerEntity);
        return batteryChargerEntity;
    }

    @Override
    public BatteryChargerEntity delete(Long id) throws CustomException {
        Optional<BatteryChargerEntity> batteryChargerEntityOptional = batteryChargerRepository.findById(id);
        if (batteryChargerEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        BatteryChargerEntity batteryChargerEntity = batteryChargerRepository.getById(id);
        batteryChargerRepository.delete(batteryChargerEntity);
        return batteryChargerEntity;
    }

    @Override
    public BatteryChargerEntity getByIdBatteryCharger(Long id) throws CustomException {
        Optional<BatteryChargerEntity> batteryChargerEntityOptional = batteryChargerRepository.findById(id);
        if (batteryChargerEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        BatteryChargerEntity discountEntity = batteryChargerRepository.getById(id);
        return discountEntity;
    }
    @Override
    public BatteryChargerEntity open(Long id) throws CustomException {
        Optional<BatteryChargerEntity> batteryChargerEntityOptional = batteryChargerRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id danh mục phải lớn hơn 0");
        }
        if (batteryChargerEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id danh mục muốn sửa");
        }
        BatteryChargerEntity batteryChargerEntity = batteryChargerEntityOptional.get();
        batteryChargerEntity.setStatus(BatteryChargerStatus.ACTIVE);
        batteryChargerEntity = batteryChargerRepository.save(batteryChargerEntity);
        return batteryChargerEntity;
    }

    @Override
    public BatteryChargerEntity close(Long id) throws CustomException {
        Optional<BatteryChargerEntity> batteryChargerEntityOptional = batteryChargerRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id phải lớn hơn 0");
        }
        if (batteryChargerEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy bản ghi muốn sửa");
        }
        BatteryChargerEntity batteryChargerEntity = batteryChargerEntityOptional.get();
        batteryChargerEntity.setStatus(BatteryChargerStatus.INACTIVE);
        batteryChargerEntity = batteryChargerRepository.save(batteryChargerEntity);
        return batteryChargerEntity;
    }

    @Override
    public List<BatteryChargerEntity> findAll() {
        return batteryChargerRepository.findAll();
    }
}
