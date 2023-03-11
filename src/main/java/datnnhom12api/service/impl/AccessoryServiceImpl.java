package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.AccessoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.AccessoryRepository;
import datnnhom12api.request.AccessoryRequest;
import datnnhom12api.service.AccessoryService;
import datnnhom12api.specifications.AccessorySpecifications;
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

@Service("accessoryService")
@Transactional(rollbackFor = Throwable.class)
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    AccessoryRepository accessoryRepo;

    @Override
    public AccessoryEntity create(AccessoryRequest accessoryRequest) throws CustomException {
        AccessoryEntity accessoryEntity = new AccessoryEntity();
        accessoryEntity.setData(accessoryRequest);
        accessoryEntity = accessoryRepo.save(accessoryEntity);
        return accessoryEntity;
    }

    @Override
    public AccessoryEntity edit(Long id, AccessoryRequest accessoryRequest) throws CustomException {
        Optional<AccessoryEntity> accessoryEntityOptional = accessoryRepo.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id truyền vào phải lớn hơn 0");
        }
        if (accessoryEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id phụ kiện muốn sửa");
        }
        AccessoryEntity accessoryEntity = accessoryEntityOptional.get();
        accessoryEntity.setData(accessoryRequest);
        accessoryEntity = accessoryRepo.save(accessoryEntity);
        return accessoryEntity;
    }

    @Override
    public Page<AccessoryEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<AccessoryEntity> specifications = AccessorySpecifications.getInstance().getQueryResult(filters);
        return accessoryRepo.findAll(specifications, pageable);
    }

    @Override
    public List<AccessoryEntity> findAll() {
        return accessoryRepo.findAll();
    }
}
