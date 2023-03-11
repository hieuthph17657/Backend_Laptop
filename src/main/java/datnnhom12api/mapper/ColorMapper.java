package datnnhom12api.mapper;

import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.ColorDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.ColorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ColorMapper {
    private static ColorMapper INSTANCE;

    public static ColorMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ColorMapper();
        }

        return INSTANCE;
    }

    public ColorMapper() {
    }

    public static ColorDTO toDTO(ColorEntity cate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cate, ColorDTO.class);
    }

    public static ColorEntity toEntity(ColorMapper cateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cateDTO, ColorEntity.class);
    }

    public static Page<ColorDTO> toPageDTO(Page<ColorEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ColorDTO apply(ColorEntity entity) {
                return ColorMapper.toDTO(entity);
            }
        });
    }

    public static List<ColorDTO> toListDTO(List<ColorEntity> entityList) {
        List<ColorDTO> list = new ArrayList<>();
        for (ColorEntity e : entityList) {
            list.add(ColorMapper.toDTO(e));
        }
        return list;
    }
}
