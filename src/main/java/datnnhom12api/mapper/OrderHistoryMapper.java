package datnnhom12api.mapper;

import datnnhom12api.dto.BatteryChargerDTO;
import datnnhom12api.dto.OrderHistoryDTO;
import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.entity.OrderHistoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderHistoryMapper {
    private static OrderHistoryMapper INSTANCE;

    public static OrderHistoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderHistoryMapper();
        }
        return INSTANCE;
    }

    public OrderHistoryMapper() {
    }

    public static OrderHistoryDTO toDTO(OrderHistoryEntity orderHistoryEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderHistoryEntity,OrderHistoryDTO.class);
    }

    public static OrderHistoryEntity toEntity(OrderHistoryMapper BatteryChargerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(BatteryChargerDTO, OrderHistoryEntity.class);
    }

    public static Page<OrderHistoryDTO> toPageDTO(Page<OrderHistoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public OrderHistoryDTO apply(OrderHistoryEntity entity) {
                return OrderHistoryMapper.toDTO(entity);
            }
        });
    }

    public static List<OrderHistoryDTO> toListDTO(List<OrderHistoryEntity> entityList) {
        List<OrderHistoryDTO> list = new ArrayList<>();
        for (OrderHistoryEntity e : entityList) {
            list.add(OrderHistoryMapper.toDTO(e));
        }
        return list;
    }
}
