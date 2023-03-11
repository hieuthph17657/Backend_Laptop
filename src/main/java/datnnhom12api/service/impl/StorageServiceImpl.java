package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.*;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.StorageRequest;
import datnnhom12api.service.CartService;
import datnnhom12api.service.StorageService;
import datnnhom12api.specifications.CartSpecifications;
import datnnhom12api.specifications.StorageSpecifications;
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

@Service("storageService")
@Transactional(rollbackFor = Throwable.class)
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    StorageDetailRepository storageDetailRepository;

    @Override
    public StorageEntity create(StorageRequest request) throws CustomException {
        StorageEntity storageEntity = new StorageEntity();
        storageEntity.setData(request);
        StorageDetailEntity storageDetailEntity = this.storageDetailRepository.getById(request.getStorageDetailId());
        storageEntity.setStorageDetail(storageDetailEntity);
        storageEntity = storageRepository.save(storageEntity);
        return storageEntity;
    }

    @Override
    public Page<StorageEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<StorageEntity> specifications = StorageSpecifications.getInstance().getQueryResult(filters);

        return storageRepository.findAll(specifications, pageable);
    }

    @Override
    public StorageEntity update(Long id, StorageRequest post) throws CustomException {
        Optional<StorageEntity> storageEntityOptional = storageRepository.findById(id);
        StorageEntity storageEntity = storageEntityOptional.get();
        storageEntity.setData(post);
        StorageDetailEntity storageDetailEntity = this.storageDetailRepository.getById(post.getStorageDetailId());
        storageEntity.setStorageDetail(storageDetailEntity);
        storageEntity = storageRepository.save(storageEntity);
        return storageEntity;
    }

    @Override
    public StorageEntity delete(Long id) throws CustomException {
        StorageEntity storageEntity = storageRepository.getById(id);
        storageRepository.delete(storageEntity);
        return storageEntity;
    }

    @Override
    public List<StorageEntity> findAll() {
        return storageRepository.findAll();
    }


}
