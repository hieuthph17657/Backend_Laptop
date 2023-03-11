package datnnhom12api.mapper;

import datnnhom12api.dto.UserDTO;
import datnnhom12api.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserMapper {
    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }

        return INSTANCE;
    }

    public UserMapper() {
    }

    public static UserDTO toDTO(UserEntity user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public static UserEntity toEntity(UserMapper userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public static Page<UserDTO> toPageDTO(Page<UserEntity> page) {
        return page.map(new Function<>() {
            @Override
            public UserDTO apply(UserEntity entity) {
                return UserMapper.toDTO(entity);
            }
        });
    }

    public static List<UserDTO> toListDTO(List<UserEntity> entityList) {
        List<UserDTO> list = new ArrayList<>();
        for (UserEntity e : entityList) {
            list.add(UserMapper.toDTO(e));
        }
        return list;
    }
}
