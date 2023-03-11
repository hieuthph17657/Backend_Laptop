package datnnhom12api.mapper;

import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDTO2;
import datnnhom12api.dto.ProductDTOById;
import datnnhom12api.dto.ProductDiscountDTO;
import datnnhom12api.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductMapper {
    private static ProductMapper INSTANCE;

    public static ProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductMapper();
        }

        return INSTANCE;
    }

    public ProductMapper() {
    }

    public static ProductDTO toDTO(ProductEntity product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTO.class);
    }

    public static ProductDTO2 toDTO2(ProductEntity product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTO2.class);
    }

    public static ProductEntity toEntity(ProductMapper productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productDTO, ProductEntity.class);
    }

    public static Page<ProductDTO> toPageDTO(Page<ProductEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProductDTO apply(ProductEntity entity) {
                return ProductMapper.toDTO(entity);
            }
        });
    }

    public static Page<ProductDTO2> toPageDTO2(Page<ProductEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProductDTO2 apply(ProductEntity entity) {
                return ProductMapper.toDTO2(entity);
            }
        });
    }

    public static ProductDTOById toDTOFilter(ProductEntity product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTOById.class);
    }
    public static Page<ProductDTOById> toPageDTOFilter(Page<ProductEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProductDTOById apply(ProductEntity entity) {
                return ProductMapper.toDTOFilter(entity);
            }
        });
    }

    public static List<ProductDTO> toListDTO(List<ProductEntity> entityList) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity e : entityList) {
            list.add(ProductMapper.toDTO(e));
        }
        return list;
    }

    public static List<ProductDTO2> toListDTO2(List<ProductEntity> entityList) {
        List<ProductDTO2> list = new ArrayList<>();
        for (ProductEntity e : entityList) {
            list.add(ProductMapper.toDTO2(e));
        }
        return list;
    }

    public static ProductDiscountDTO toProDiscountDTO(ProductEntity product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDiscountDTO.class);
    }
    public static List<ProductDiscountDTO> toListProDiscountDTO(List<ProductEntity> entityList) {
        List<ProductDiscountDTO> list = new ArrayList<>();
        for (ProductEntity e : entityList) {
            list.add(ProductMapper.toProDiscountDTO(e));
        }
        return list;
    }
}
