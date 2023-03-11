package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.CategoryRepository;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.service.CategoryService;
import datnnhom12api.specifications.CategorySpecifications;
import datnnhom12api.utils.support.CategoryStatus;
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

@Service("cateService")
@Transactional(rollbackFor = Throwable.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository cateRepository;

    @Override
    public CategoryEntity save(CategoryRequest categoryRequest) throws CustomException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setData(categoryRequest);
        categoryEntity = cateRepository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public CategoryEntity edit(Long id, CategoryRequest categoryRequest) throws CustomException {
        Optional<CategoryEntity> cateEntityOptional = cateRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (cateEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        CategoryEntity categoryEntity = cateEntityOptional.get();
//        categoryEntity.setData(categoryRequest);
        categoryEntity.setName(categoryRequest.getName());
        categoryEntity = cateRepository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public CategoryEntity delete(Long id) throws CustomException{
        Optional<CategoryEntity> cateEntityOptional = cateRepository.findById(id);
        if (cateEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        CategoryEntity categoryEntity = cateRepository.getById(id);
        cateRepository.delete(categoryEntity);
        return categoryEntity;
    }

    @Override
    public Page<CategoryEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<CategoryEntity> specifications = CategorySpecifications.getInstance().getQueryResult(filters);

        return cateRepository.findAll(specifications, pageable);
    }

    @Override
    public CategoryEntity open(Long id) throws CustomException {
        Optional<CategoryEntity> categoryEntityOptional = cateRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id danh mục phải lớn hơn 0");
        }
        if (categoryEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id danh mục muốn sửa");
        }
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        categoryEntity.setStatus(CategoryStatus.ACTIVE);
        categoryEntity = cateRepository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public CategoryEntity close(Long id) throws CustomException {
        Optional<CategoryEntity> categoryEntityOptional = cateRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id danh mục phải lớn hơn 0");
        }
        if (categoryEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id danh mục muốn sửa");
        }
        CategoryEntity categoryEntity = categoryEntityOptional.get();
        categoryEntity.setStatus(CategoryStatus.INACTIVE);
        categoryEntity = cateRepository.save(categoryEntity);
        return categoryEntity;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return cateRepository.findAll();
    }
}
