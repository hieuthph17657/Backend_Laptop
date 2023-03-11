package datnnhom12api.mapper;

import datnnhom12api.dto.ProcessorDTO;
import datnnhom12api.dto.RamDTO;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.RamEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RamMapper {
    private static RamMapper INSTANCE;

    public static RamMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RamMapper();
        }

        return INSTANCE;
    }

    public RamMapper() {
    }

    public static RamDTO toDTO(RamEntity cate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cate, RamDTO.class);
    }

    public static RamEntity toEntity(RamMapper cateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cateDTO, RamEntity.class);
    }

    public static Page<RamDTO> toPageDTO(Page<RamEntity> page) {
        return page.map(new Function<>() {
            @Override
            public RamDTO apply(RamEntity entity) {
                return RamMapper.toDTO(entity);
            }
        });
    }

    public static List<RamDTO> toListDTO(List<RamEntity> entityList) {
        List<RamDTO> list = new ArrayList<>();
        for (RamEntity e : entityList) {
            list.add(RamMapper.toDTO(e));
        }
        return list;
    }
}
