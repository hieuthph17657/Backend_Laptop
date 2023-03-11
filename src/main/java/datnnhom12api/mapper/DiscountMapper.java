package datnnhom12api.mapper;

import datnnhom12api.dto.DiscountDTO;
import datnnhom12api.entity.DiscountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DiscountMapper {
    private static DiscountMapper INSTANCE;

    public static DiscountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountMapper();
        }

        return INSTANCE;
    }

    public DiscountMapper() {
    }

    public static DiscountDTO toDTO(DiscountEntity discount) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(discount, DiscountDTO.class);
    }

    public static DiscountEntity toEntity(DiscountMapper discountDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(discountDTO, DiscountEntity.class);
    }

    public static Page<DiscountDTO> toPageDTO(Page<DiscountEntity> page) {
        return page.map(new Function<>() {
            @Override
            public DiscountDTO apply(DiscountEntity entity) {
                return DiscountMapper.toDTO(entity);
            }
        });
    }

    public static List<DiscountDTO> toListDTO(List<DiscountEntity> entityList) {
        List<DiscountDTO> list = new ArrayList<>();
        for (DiscountEntity e : entityList) {
            list.add(DiscountMapper.toDTO(e));
        }
        return list;
    }
}
