package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDTO2;
import datnnhom12api.dto.ProductDTOById;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductEntity insert(ProductRequest post) throws CustomException;

    ProductEntity update(Long id, ProductRequest post) throws CustomException;

    ProductEntity delete(Long id) throws CustomException;

    Page<ProductEntity> paginate(int page, int limit, List<Filter> filters, String searchProductKey, String searchPn, String searchStatus, String searchPrice, Map<String, String> sortBy);

    Page<ProductEntity> indexProductsDiscount(int page, int limit, List<Filter> filters, Map<String, String> sortBy);

    ProductEntity create(ProductEntity productEntity);

    ProductDTOById findById(Long id);

    ProductEntity noDiscount(Long id, Long idProduct) throws CustomException;

    ProductEntity active(Long id) throws CustomException;

    ProductEntity inActive(Long id) throws CustomException;

    List<ProductEntity> discount(Long id, List<Long> idProduct) throws CustomException;

    SumProductDTO sumProduct();

    ProductEntity copyProduct(ProductRequest productRequest, Long id);

    List<ProductDTOById> findProductByCategory(Long id);

    Page<ProductEntity> findProductByStatus(int page, int limit, List<Filter> filters, Map<String, String> sortBy);

    List<ProductDTO> findAll();
}
