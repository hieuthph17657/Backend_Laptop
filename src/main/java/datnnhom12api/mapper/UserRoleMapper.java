package datnnhom12api.mapper;

import datnnhom12api.dto.UserRoleDTO;
import datnnhom12api.entity.UserRoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserRoleMapper {
    private static UserRoleMapper INSTANCE;

    public static UserRoleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRoleMapper();
        }

        return INSTANCE;
    }

    public UserRoleMapper() {
    }

    public static UserRoleDTO toDTO(UserRoleEntity userRole) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRole, UserRoleDTO.class);
    }

    public static UserRoleEntity toEntity(UserRoleDTO userRoleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRoleDTO, UserRoleEntity.class);
    }

    public static Page<UserRoleDTO> toPageDTO(Page<UserRoleEntity> page) {
        return page.map(new Function<>() {
            @Override
            public UserRoleDTO apply(UserRoleEntity entity) {
                return UserRoleMapper.toDTO(entity);
            }
        });
    }

    public static List<UserRoleDTO> toListDTO(List<UserRoleEntity> entityList) {
        List<UserRoleDTO> list = new ArrayList<>();
        for (UserRoleEntity e : entityList) {
            list.add(UserRoleMapper.toDTO(e));
        }
        return list;
    }
}
