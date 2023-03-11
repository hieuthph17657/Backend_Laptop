package datnnhom12api.mapper;

import datnnhom12api.dto.CartDTO;
import datnnhom12api.dto.StorageDTO;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.StorageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StorageMapper {
    private static StorageMapper INSTANCE;

    public static StorageMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StorageMapper();
        }
        return INSTANCE;
    }

    public StorageMapper() {
    }

    public static StorageDTO toDTO(StorageEntity storage) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storage, StorageDTO.class);
    }

    public static StorageEntity toEntity(StorageDTO storageDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storageDTO, StorageEntity.class);
    }

    public static Page<StorageDTO> toPageDTO(Page<StorageEntity> page) {
        return page.map(new Function<>() {
            @Override
            public StorageDTO apply(StorageEntity entity) {
                return StorageMapper.toDTO(entity);
            }
        });
    }

    public static List<StorageDTO> toListDTO(List<StorageEntity> entityList) {
        List<StorageDTO> list = new ArrayList<>();
        for (StorageEntity e : entityList) {
            list.add(StorageMapper.toDTO(e));
        }
        return list;
    }
}
