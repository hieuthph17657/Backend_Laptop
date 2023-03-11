package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.OriginDTO;
import datnnhom12api.entity.OriginEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.OriginRepository;
import datnnhom12api.request.OriginRequest;
import datnnhom12api.service.OriginService;
import datnnhom12api.specifications.OriginSpecifications;
import datnnhom12api.utils.support.OriginStatus;
import org.modelmapper.ModelMapper;
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

@Service("originService")
@Transactional(rollbackFor = Throwable.class)
public class OriginServiceImpl implements OriginService {

    @Autowired
    OriginRepository OriginRepo;


    @Override
    public OriginEntity save(OriginRequest originRequest) throws CustomException {
        OriginEntity originEntity = new OriginEntity();
        originEntity.setData(originRequest);
        originEntity = OriginRepo.save(originEntity);
        return originEntity;
    }

    @Override
    public OriginEntity edit(Long id, OriginRequest originRequest) throws CustomException {
        Optional<OriginEntity> originEntityOptional = OriginRepo.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id truyền vào phải lớn hơn 0");
        }
        if (originEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id nước sản xuất muốn sửa");
        }
        OriginEntity originEntity = originEntityOptional.get();
        originEntity.setData(originRequest);
        originEntity = OriginRepo.save(originEntity);
        return originEntity;
    }

    @Override
    public OriginEntity delete(Long id) throws CustomException {
        Optional<OriginEntity> originEntityOptional = OriginRepo.findById(id);
        if (originEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        OriginEntity originEntity = OriginRepo.getById(id);
        OriginRepo.delete(originEntity);
        return originEntity;
    }

    @Override
    public Page<OriginEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<OriginEntity> specifications = OriginSpecifications.getInstance().getQueryResult(filters);

        return OriginRepo.findAll(specifications, pageable);
    }

    @Override
    public OriginDTO active(Long id) throws CustomException {
        OriginEntity originEntity = this.OriginRepo.getById(id);
        originEntity.setStatus(OriginStatus.ACTIVE);
        this.OriginRepo.save(originEntity);
        ModelMapper modelMapper = new ModelMapper();
        OriginDTO originDTO = modelMapper.map(originEntity, OriginDTO.class);
        return originDTO;
    }

    @Override
    public OriginDTO inactive(Long id) throws CustomException {
        OriginEntity originEntity = this.OriginRepo.getById(id);
        originEntity.setStatus(OriginStatus.INACTIVE);
        this.OriginRepo.save(originEntity);
        ModelMapper modelMapper = new ModelMapper();
        OriginDTO originDTO = modelMapper.map(originEntity, OriginDTO.class);
        return originDTO;
    }
}
