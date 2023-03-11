package datnnhom12api.mapper;

import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.OrderDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderMapper {
    private static OrderMapper INSTANCE;

    public static OrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderMapper();
        }

        return INSTANCE;
    }

    public OrderMapper() {
    }

    public static OrderDTO toDTO(OrderEntity orderEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderEntity, OrderDTO.class);
    }

    public static OrderEntity toEntity(OrderMapper orderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderDTO, OrderEntity.class);
    }

    public static Page<OrderDTO> toPageDTO(Page<OrderEntity> page) {
        return page.map(new Function<>() {
            @Override
            public OrderDTO apply(OrderEntity entity) {
                return OrderMapper.toDTO(entity);
            }
        });
    }

    public static List<OrderDTO> toListDTO(List<OrderEntity> entityList) {
        List<OrderDTO> list = new ArrayList<>();
        for (OrderEntity e : entityList) {
            list.add(OrderMapper.toDTO(e));
        }
        return list;
    }
}
