package datnnhom12api.mapper;

import datnnhom12api.dto.ExchangeDTO;
import datnnhom12api.dto.OrderDTO;
import datnnhom12api.dto.OrderDetailDTO;
import datnnhom12api.dto.OriginDTO;
import datnnhom12api.entity.ExchangeEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.OriginEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExchangeMapper {
    private static ExchangeMapper INSTANCE;

    public static ExchangeMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExchangeMapper();
        }

        return INSTANCE;
    }

    public ExchangeMapper() {
    }

    public static OrderDetailDTO toDTO(OrderDetailEntity orderEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderEntity, OrderDetailDTO.class);
    }

    public static List<OrderDetailDTO> toListDTO(List<OrderDetailEntity> entityList) {
        List<OrderDetailDTO> list = new ArrayList<>();
        for (OrderDetailEntity e : entityList) {
            list.add(ExchangeMapper.toDTO(e));
        }
        return list;
    }

    public Page<ExchangeDTO> toPageDTO(Page<ExchangeEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ExchangeDTO apply(ExchangeEntity entity) {
                return ExchangeMapper.toDTO(entity);
            }
        });
    }

    public static ExchangeDTO toDTO(ExchangeEntity originEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(originEntity, ExchangeDTO.class);
    }
}
