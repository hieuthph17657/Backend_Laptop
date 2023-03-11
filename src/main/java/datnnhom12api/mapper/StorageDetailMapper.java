package datnnhom12api.mapper;

import datnnhom12api.dto.StorageDTO;
import datnnhom12api.dto.StorageDetailDTO;
import datnnhom12api.entity.StorageDetailEntity;
import datnnhom12api.entity.StorageEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StorageDetailMapper {
    private static StorageDetailMapper INSTANCE;

    public static StorageDetailMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StorageDetailMapper();
        }
        return INSTANCE;
    }

    public StorageDetailMapper() {
    }

    public static StorageDetailDTO toDTO(StorageDetailEntity storageDetail) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storageDetail, StorageDetailDTO.class);
    }

    public static StorageDetailEntity toEntity(StorageDetailDTO storageDetailDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(storageDetailDTO, StorageDetailEntity.class);
    }

    public static Page<StorageDetailDTO> toPageDTO(Page<StorageDetailEntity> page) {
        return page.map(new Function<>() {
            @Override
            public StorageDetailDTO apply(StorageDetailEntity entity) {
                return StorageDetailMapper.toDTO(entity);
            }
        });
    }

    public static List<StorageDetailDTO> toListDTO(List<StorageDetailEntity> entityList) {
        List<StorageDetailDTO> list = new ArrayList<>();
        for (StorageDetailEntity e : entityList) {
            list.add(StorageDetailMapper.toDTO(e));
        }
        return list;
    }
}
