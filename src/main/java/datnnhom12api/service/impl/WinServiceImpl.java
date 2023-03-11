package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.WinEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.WinRepository;
import datnnhom12api.request.WinRequest;
import datnnhom12api.service.WinService;
import datnnhom12api.specifications.WinSpecifications;
import datnnhom12api.utils.support.WinStatus;
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

@Service("winService")
@Transactional(rollbackFor = Throwable.class)
public class WinServiceImpl implements WinService {
    @Autowired
    private WinRepository winRepository;

    @Override
    public Page<WinEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<WinEntity> specifications = WinSpecifications.getInstance().getQueryResult(filters);
        return winRepository.findAll(specifications, pageable);
    }

    @Override
    public WinEntity create(WinRequest post) {
        WinEntity winEntity = new WinEntity();
        winEntity.setData(post);
        winEntity = winRepository.save(winEntity);
        return winEntity;
    }

    @Override
    public WinEntity update(Long id, WinRequest post) throws CustomException {
        Optional<WinEntity> processorEntityOptional = winRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (processorEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        WinEntity winEntity = processorEntityOptional.get();
        winEntity.setData(post);
        winEntity = winRepository.save(winEntity);
        return winEntity;
    }

    @Override
    public void delete(Long id) {
        WinEntity winEntity = this.winRepository.getById(id);
        winRepository.delete(winEntity);
    }
    @Override
    public WinEntity open(Long id) throws CustomException {
        Optional<WinEntity> winEntityOptional = winRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id danh mục phải lớn hơn 0");
        }
        if (winEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id danh mục muốn sửa");
        }
        WinEntity winEntity = winEntityOptional.get();
        winEntity.setStatus(WinStatus.ACTIVE);
        winEntity = winRepository.save(winEntity);
        return winEntity;
    }

    @Override
    public WinEntity close(Long id) throws CustomException {
        Optional<WinEntity> winEntityOptional = winRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id phải lớn hơn 0");
        }
        if (winEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy bản ghi muốn sửa");
        }
        WinEntity winEntity = winEntityOptional.get();
        winEntity.setStatus(WinStatus.INACTIVE);
        winEntity = winRepository.save(winEntity);
        return winEntity;
    }

    @Override
    public List<WinEntity> findAll() {
        return winRepository.findAll();
    }
}
