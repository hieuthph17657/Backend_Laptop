package datnnhom12api.mapper;

import datnnhom12api.dto.ProcessorDTO;
import datnnhom12api.entity.ProcessorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProcessorMapper {
    private static ProcessorMapper INSTANCE;

    public static ProcessorMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProcessorMapper();
        }

        return INSTANCE;
    }

    public ProcessorMapper() {
    }

    public static ProcessorDTO toDTO(ProcessorEntity cate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cate, ProcessorDTO.class);
    }

    public static ProcessorEntity toEntity(ProcessorMapper cateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cateDTO, ProcessorEntity.class);
    }

    public static Page<ProcessorDTO> toPageDTO(Page<ProcessorEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProcessorDTO apply(ProcessorEntity entity) {
                return ProcessorMapper.toDTO(entity);
            }
        });
    }

    public static List<ProcessorDTO> toListDTO(List<ProcessorEntity> entityList) {
        List<ProcessorDTO> list = new ArrayList<>();
        for (ProcessorEntity e : entityList) {
            list.add(ProcessorMapper.toDTO(e));
        }
        return list;
    }
}
