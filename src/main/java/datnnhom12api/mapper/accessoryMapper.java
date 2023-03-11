package datnnhom12api.mapper;

import datnnhom12api.dto.AccessoryDTO;
import datnnhom12api.entity.AccessoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class accessoryMapper {
    private static accessoryMapper INSTANCE;

    public static accessoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new accessoryMapper();
        }

        return INSTANCE;
    }

    public accessoryMapper() {
    }

    public static AccessoryDTO toDTO(AccessoryEntity accessory) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accessory, AccessoryDTO.class);
    }

    public static AccessoryEntity toEntity(accessoryMapper accessoryMapper) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accessoryMapper, AccessoryEntity.class);
    }

    public static Page<AccessoryDTO> toPageDTO(Page<AccessoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public AccessoryDTO apply(AccessoryEntity entity) {
                return accessoryMapper.toDTO(entity);
            }
        });
    }

    public static List<AccessoryDTO> toListDTO(List<AccessoryEntity> entityList) {
        List<AccessoryDTO> list = new ArrayList<>();
        for (AccessoryEntity e : entityList) {
            list.add(accessoryMapper.toDTO(e));
        }
        return list;
    }
}
