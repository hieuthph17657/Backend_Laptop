package datnnhom12api.mapper;

import datnnhom12api.dto.ScreenDTO;
import datnnhom12api.entity.ScreenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ScreenMapper {
    private static ScreenMapper INSTANCE;

    public static ScreenMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScreenMapper();
        }
        return INSTANCE;
    }

    public ScreenMapper() {
    }

    public static ScreenDTO toDTO(ScreenEntity screenEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(screenEntity, ScreenDTO.class);
    }

    public static ScreenEntity toEntity(ScreenMapper cartDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartDTO, ScreenEntity.class);
    }

    public static Page<ScreenDTO> toPageDTO(Page<ScreenEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ScreenDTO apply(ScreenEntity entity) {
                return ScreenMapper.toDTO(entity);
            }
        });
    }

    public static List<ScreenDTO> toListDTO(List<ScreenEntity> entityList) {
        List<ScreenDTO> list = new ArrayList<>();
        for (ScreenEntity e : entityList) {
            list.add(ScreenMapper.toDTO(e));
        }
        return list;
    }
}
