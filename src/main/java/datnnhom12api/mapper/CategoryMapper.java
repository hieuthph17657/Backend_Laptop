package datnnhom12api.mapper;

import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CategoryMapper {
    private static CategoryMapper INSTANCE;

    public static CategoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryMapper();
        }

        return INSTANCE;
    }

    public CategoryMapper() {
    }

    public static CategoryDTO toDTO(CategoryEntity cate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cate, CategoryDTO.class);
    }

    public static CategoryEntity toEntity(CategoryMapper cateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cateDTO, CategoryEntity.class);
    }

    public static Page<CategoryDTO> toPageDTO(Page<CategoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public CategoryDTO apply(CategoryEntity entity) {
                return CategoryMapper.toDTO(entity);
            }
        });
    }

    public static List<CategoryDTO> toListDTO(List<CategoryEntity> entityList) {
        List<CategoryDTO> list = new ArrayList<>();
        for (CategoryEntity e : entityList) {
            list.add(CategoryMapper.toDTO(e));
        }
        return list;
    }
}
