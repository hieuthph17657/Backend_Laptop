package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.dto.ColorDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.ColorEntity;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.ColorRepository;
import datnnhom12api.request.ColorRequest;
import datnnhom12api.service.ColorService;
import datnnhom12api.specifications.CategorySpecifications;
import datnnhom12api.specifications.ColorSpecifications;
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

@Service("colorService")
@Transactional(rollbackFor = Throwable.class)
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public Page<ColorEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<ColorEntity> specifications = ColorSpecifications.getInstance().getQueryResult(filters);

        return colorRepository.findAll(specifications, pageable);
    }

    @Override
    public ColorEntity create(ColorRequest post) {
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setName(post.getName());
        colorEntity = colorRepository.save(colorEntity);
        return colorEntity;
    }

    @Override
    public ColorEntity update(Long id, ColorRequest post) throws CustomException {
        Optional<ColorEntity> processorEntityOptional = colorRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (processorEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        ColorEntity colorEntity = processorEntityOptional.get() ;
        colorEntity.setName(post.getName());
        colorEntity = colorRepository.save(colorEntity);
        return colorEntity;
    }

    @Override
    public void delete(Long id) {
        ColorEntity processorEntity = this.colorRepository.getById(id);
        colorRepository.delete(processorEntity);
    }
}
