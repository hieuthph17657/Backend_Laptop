package datnnhom12api.mapper;

import datnnhom12api.dto.WinDTO;
import datnnhom12api.entity.WinEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WinMapper {
    private static WinMapper INSTANCE;

    public static WinMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WinMapper();
        }
        return INSTANCE;
    }

    public WinMapper() {
    }

    public static WinDTO toDTO(WinEntity winEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(winEntity, WinDTO.class);
    }

    public static WinEntity toEntity(WinMapper cartDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartDTO, WinEntity.class);
    }

    public static Page<WinDTO> toPageDTO(Page<WinEntity> page) {
        return page.map(new Function<>() {
            @Override
            public WinDTO apply(WinEntity entity) {
                return WinMapper.toDTO(entity);
            }
        });
    }

    public static List<WinDTO> toListDTO(List<WinEntity> entityList) {
        List<WinDTO> list = new ArrayList<>();
        for (WinEntity e : entityList) {
            list.add(WinMapper.toDTO(e));
        }
        return list;
    }
}
