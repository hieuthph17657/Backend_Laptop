package datnnhom12api.mapper;

import datnnhom12api.dto.ManufactureDTO;
import datnnhom12api.dto.UserDTO;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ManufactureMapper {

    private static ManufactureMapper INSTANCE;

    public static ManufactureMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ManufactureMapper();
        }

        return INSTANCE;
    }

    public ManufactureMapper() {
    }

    public static ManufactureDTO toDTO(ManufactureEntity manufacture) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(manufacture, ManufactureDTO.class);
    }

    public static ManufactureEntity toEntity(ManufactureMapper manufactureDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(manufactureDTO, ManufactureEntity.class);
    }

    public static Page<ManufactureDTO> toPageDTO(Page<ManufactureEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ManufactureDTO apply(ManufactureEntity entity) {
                return ManufactureMapper.toDTO(entity);
            }
        });
    }

    public static List<ManufactureDTO> toListDTO(List<ManufactureEntity> entityList) {
        List<ManufactureDTO> list = new ArrayList<>();
        for (ManufactureEntity e : entityList) {
            list.add(ManufactureMapper.toDTO(e));
        }
        return list;
    }
}
