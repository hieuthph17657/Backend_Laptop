package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.StorageDetailEntity;
import datnnhom12api.entity.StorageTypeEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.StorageDetailRepository;
import datnnhom12api.repository.StorageTypeRepository;
import datnnhom12api.request.StorageDetailRequest;
import datnnhom12api.service.StorageDetailService;
import datnnhom12api.specifications.StorageDetailSpecifications;
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

@Service("storageDetailService")
@Transactional(rollbackFor = Throwable.class)
public class StorageDetailServiceImpl implements StorageDetailService {

    @Autowired
    StorageDetailRepository storageDetailRepository;

    @Autowired
    StorageTypeRepository storageTypeRepository;

    @Override
    public StorageDetailEntity create(StorageDetailRequest request) throws CustomException {
        StorageDetailEntity storageDetailEntity = new StorageDetailEntity();
        storageDetailEntity.setData(request);
        StorageTypeEntity storageTypeEntity = this.storageTypeRepository.getById(request.getStorageTypeId());
        storageDetailEntity.setStorageType(storageTypeEntity);
        storageDetailEntity = storageDetailRepository.save(storageDetailEntity);
        return storageDetailEntity;
    }

    @Override
    public Page<StorageDetailEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<StorageDetailEntity> specifications = StorageDetailSpecifications.getInstance().getQueryResult(filters);

        return storageDetailRepository.findAll(specifications, pageable);
    }

    @Override
    public StorageDetailEntity update(Long id, StorageDetailRequest storageDetailRequest) throws CustomException {
        Optional<StorageDetailEntity> storageDetailEntityOptional = storageDetailRepository.findById(id);
        StorageDetailEntity storageDetailEntity = storageDetailEntityOptional.get();
        storageDetailEntity.setData(storageDetailRequest);
        StorageTypeEntity storageTypeEntity = this.storageTypeRepository.getById(storageDetailRequest.getStorageTypeId());
        storageDetailEntity.setStorageType(storageTypeEntity);
        storageDetailEntity = storageDetailRepository.save(storageDetailEntity);
        return storageDetailEntity;
    }

    @Override
    public StorageDetailEntity delete(Long id) throws CustomException {
        StorageDetailEntity storageDetailEntity = storageDetailRepository.getById(id);
        storageDetailRepository.delete(storageDetailEntity);
        return storageDetailEntity;
    }

    @Override
    public List<StorageDetailEntity> findAll() {
        return storageDetailRepository.findAll();
    }
}
