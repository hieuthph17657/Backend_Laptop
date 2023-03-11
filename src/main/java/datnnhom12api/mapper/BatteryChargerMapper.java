package datnnhom12api.mapper;

import datnnhom12api.dto.BatteryChargerDTO;
import datnnhom12api.entity.BatteryChargerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BatteryChargerMapper {
    private static BatteryChargerMapper INSTANCE;

    public static BatteryChargerMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BatteryChargerMapper();
        }
        return INSTANCE;
    }

    public BatteryChargerMapper() {
    }

    public static BatteryChargerDTO toDTO(BatteryChargerEntity BatteryCharger) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(BatteryCharger, BatteryChargerDTO.class);
    }

    public static BatteryChargerEntity toEntity(BatteryChargerMapper BatteryChargerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(BatteryChargerDTO, BatteryChargerEntity.class);
    }

    public static Page<BatteryChargerDTO> toPageDTO(Page<BatteryChargerEntity> page) {
        return page.map(new Function<>() {
            @Override
            public BatteryChargerDTO apply(BatteryChargerEntity entity) {
                return BatteryChargerMapper.toDTO(entity);
            }
        });
    }

    public static List<BatteryChargerDTO> toListDTO(List<BatteryChargerEntity> entityList) {
        List<BatteryChargerDTO> list = new ArrayList<>();
        for (BatteryChargerEntity e : entityList) {
            list.add(BatteryChargerMapper.toDTO(e));
        }
        return list;
    }
}
