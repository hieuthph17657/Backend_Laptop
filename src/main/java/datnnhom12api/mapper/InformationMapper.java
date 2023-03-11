package datnnhom12api.mapper;

import datnnhom12api.dto.InformationDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.InformationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InformationMapper {
    private static InformationMapper INSTANCE;

    public static InformationMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InformationMapper();
        }

        return INSTANCE;
    }

    public InformationMapper() {
    }

    public static InformationDTO toDTO(InformationEntity info) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(info, InformationDTO.class);
    }

    public static InformationEntity toEntity(InformationMapper infoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(infoDTO, InformationEntity.class);
    }

    public static Page<InformationDTO> toPageDTO(Page<InformationEntity> page) {
        return page.map(new Function<>() {
            @Override
            public InformationDTO apply(InformationEntity entity) {
                return InformationMapper.toDTO(entity);
            }
        });
    }

    public static List<InformationDTO> toListDTO(List<InformationEntity> entityList) {
        List<InformationDTO> list = new ArrayList<>();
        for (InformationEntity e : entityList) {
            list.add(InformationMapper.toDTO(e));
        }
        return list;
    }
}
