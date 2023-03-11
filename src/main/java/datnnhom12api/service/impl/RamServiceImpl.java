package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.dto.RamDTO;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.RamRepository;
import datnnhom12api.request.RamRequest;
import datnnhom12api.service.RamService;
import datnnhom12api.specifications.RamSpecifications;
import datnnhom12api.utils.support.RamStatus;
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

@Service("ramService")
@Transactional(rollbackFor = Throwable.class)
public class RamServiceImpl implements RamService {
    @Autowired
    private RamRepository ramRepository;

    @Override
    public Page<RamEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<RamEntity> specifications = RamSpecifications.getInstance().getQueryResult(filters);
        return ramRepository.findAll(specifications, pageable);
    }

    @Override
    public RamEntity create(RamRequest post) {
        RamEntity ramEntity = new RamEntity();
        ramEntity.setData(post);
        ramEntity = ramRepository.save(ramEntity);
        return ramEntity;
    }

    @Override
    public RamEntity update(Long id, RamRequest post) throws CustomException {
        Optional<RamEntity> processorEntityOptional = ramRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (processorEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        RamEntity ramEntity = processorEntityOptional.get();
        ramEntity.setData(post);
        ramEntity = ramRepository.save(ramEntity);
        return ramEntity;
    }

    @Override
    public void delete(Long id) {
        RamEntity processorEntity = this.ramRepository.getById(id);
        ramRepository.delete(processorEntity);
    }

    @Override
    public RamDTO active(Long id) throws CustomException {
        RamEntity ramEntity = this.ramRepository.getById(id);
        ramEntity.setStatus(RamStatus.ACTIVE);
        this.ramRepository.save(ramEntity);
        ModelMapper modelMapper = new ModelMapper();
        RamDTO ramDTO = modelMapper.map(ramEntity, RamDTO.class);
        return ramDTO;
    }

    @Override
    public RamDTO inactive(Long id) throws CustomException {
        RamEntity ramEntity = this.ramRepository.getById(id);
        ramEntity.setStatus(RamStatus.INACTIVE);
        this.ramRepository.save(ramEntity);
        ModelMapper modelMapper = new ModelMapper();
        RamDTO ramDTO = modelMapper.map(ramEntity, RamDTO.class);
        return ramDTO;
    }

    @Override
    public List<RamEntity> findAll() {
        return ramRepository.findAll();
    }
}
