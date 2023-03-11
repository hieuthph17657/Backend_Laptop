package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.InformationEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.InformationRepository;
import datnnhom12api.request.InformationRequest;
import datnnhom12api.service.InformationService;
import datnnhom12api.specifications.InformationSpecifications;
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

@Service("infoService")
@Transactional(rollbackFor = Throwable.class)
public class InformationServiceImpl implements InformationService {

    @Autowired
    InformationRepository inforRepository;

    @Override
    public InformationEntity save(InformationRequest inforRequest) throws CustomException {
        InformationEntity inforEntity = new InformationEntity();
        inforEntity.setData(inforRequest);
        inforEntity = inforRepository.save(inforEntity);
        return inforEntity;
    }

    @Override
    public InformationEntity edit(Long id, InformationRequest categoryRequest) throws CustomException {
        Optional<InformationEntity> inforEntityOptional = inforRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (inforEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        InformationEntity inforEntity = inforEntityOptional.get();
        inforEntity.setData(categoryRequest);
        inforEntity = inforRepository.save(inforEntity);
        return inforEntity;
    }

    @Override
    public InformationEntity delete(Long id) throws CustomException{
        Optional<InformationEntity> inforEntityOptional = inforRepository.findById(id);
        if (inforEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        InformationEntity inforEntity = inforRepository.getById(id);
        inforRepository.delete(inforEntity);
        return inforEntity;
    }

    @Override
    public Page<InformationEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<InformationEntity> specifications = InformationSpecifications.getInstance().getQueryResult(filters);

        return inforRepository.findAll(specifications, pageable);
    }

    @Override
    public InformationEntity getById(Long id) throws CustomException {
        Optional<InformationEntity> informationEntityOptional = inforRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (informationEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id");
        }
        InformationEntity informationEntity = informationEntityOptional.get();
        return informationEntity;
    }
}
