package datnnhom12api.mapper;

import datnnhom12api.dto.CardDTO;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.entity.CardEntity;
import datnnhom12api.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CardMapper {
    private static CardMapper INSTANCE;

    public static CardMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CardMapper();
        }

        return INSTANCE;
    }

    public CardMapper() {
    }

    public static CardDTO toDTO(CardEntity card) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(card, CardDTO.class);
    }

    public static CardEntity toEntity(CardDTO cardDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cardDTO, CardEntity.class);
    }

    public static Page<CardDTO> toPageDTO(Page<CardEntity> page) {
        return page.map(new Function<>() {
            @Override
            public CardDTO apply(CardEntity entity) {
                return CardMapper.toDTO(entity);
            }
        });
    }

    public static List<CardDTO> toListDTO(List<CardEntity> entityList) {
        List<CardDTO> list = new ArrayList<>();
        for (CardEntity e : entityList) {
            list.add(CardMapper.toDTO(e));
        }
        return list;
    }
}
