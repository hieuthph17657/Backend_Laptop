package datnnhom12api.mapper;

import datnnhom12api.dto.OriginDTO;
import datnnhom12api.entity.OriginEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OriginMapper {

    private static OriginMapper INSTANCE;

    public static OriginMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OriginMapper();
        }
        return INSTANCE;
    }

    public OriginMapper() {
    }

    public static OriginDTO toDTO(OriginEntity originEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(originEntity, OriginDTO.class);
    }

    public static OriginEntity toEntity(OriginMapper originMapper) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(originMapper, OriginEntity.class);
    }

    public static Page<OriginDTO> toPageDTO(Page<OriginEntity> page) {
        return page.map(new Function<>() {
            @Override
            public OriginDTO apply(OriginEntity entity) {
                return OriginMapper.toDTO(entity);
            }
        });
    }

    public static List<OriginDTO> toListDTO(List<OriginEntity> entityList) {
        List<OriginDTO> list = new ArrayList<>();
        for (OriginEntity e : entityList) {
            list.add(OriginMapper.toDTO(e));
        }
        return list;
    }
}
