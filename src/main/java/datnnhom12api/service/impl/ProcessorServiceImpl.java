package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProcessorDTO;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.ProcessorRepository;
import datnnhom12api.request.ProcessorRequest;
import datnnhom12api.service.ProcessorService;
import datnnhom12api.specifications.ProcessorSpecification;
import datnnhom12api.utils.support.ProcessorStatus;
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

@Service("processorService")
@Transactional(rollbackFor = Throwable.class)
public class ProcessorServiceImpl implements ProcessorService {

    @Autowired
    private ProcessorRepository processorRepository;

    @Override
    public Page<ProcessorEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ProcessorEntity> specifications = ProcessorSpecification.getInstance().getQueryResult(filters);
        return processorRepository.findAll(specifications, pageable);
    }

    @Override
    public ProcessorEntity create(ProcessorRequest post) {
        ProcessorEntity processorEntity = new ProcessorEntity();
        processorEntity.setData(post);
        processorEntity = processorRepository.save(processorEntity);
        return processorEntity;
    }

    @Override
    public ProcessorEntity update(Long id, ProcessorRequest post) throws CustomException {
        Optional<ProcessorEntity> processorEntityOptional = processorRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (processorEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        ProcessorEntity processorEntity = processorEntityOptional.get();
        processorEntity.setData(post);
        processorEntity = processorRepository.save(processorEntity);
        return processorEntity;
    }

    @Override
    public void delete(Long id) {
        ProcessorEntity processorEntity = this.processorRepository.getById(id);
        processorRepository.delete(processorEntity);
    }

    @Override
    public ProcessorEntity getById(Long id) {
        ProcessorEntity processorEntity = this.processorRepository.getById(id);
        return processorEntity;
    }

    @Override
    public ProcessorDTO active(Long id) throws CustomException {
        ProcessorEntity processorEntity = this.processorRepository.getById(id);
        processorEntity.setStatus(ProcessorStatus.ACTIVE);
        this.processorRepository.save(processorEntity);
        ModelMapper modelMapper = new ModelMapper();
        ProcessorDTO processorDTO = modelMapper.map(processorEntity, ProcessorDTO.class);
        return processorDTO;
    }

    @Override
    public ProcessorDTO inactive(Long id) throws CustomException {
        ProcessorEntity processorEntity = this.processorRepository.getById(id);
        processorEntity.setStatus(ProcessorStatus.INACTIVE);
        this.processorRepository.save(processorEntity);
        ModelMapper modelMapper = new ModelMapper();
        ProcessorDTO processorDTO = modelMapper.map(processorEntity, ProcessorDTO.class);
        return processorDTO;
    }

    @Override
    public List<ProcessorEntity> findAll() {
        return processorRepository.findAll();
    }
}
