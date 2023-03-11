package datnnhom12api.controller.admin;

import datnnhom12api.dto.*;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ProductMapper;
import datnnhom12api.paginationrequest.ProductPaginationRequest;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.response.*;
import datnnhom12api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public ProductResponse index(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.paginate(
                request.getPage(), request.getLimit(), request.getFilters(), request.getSearchProductKey(), request.getSearchPn(), request.getSearchStatus(), request.getSearchPrice(), request.getOrders());
        return new ProductResponse(ProductMapper.toPageDTO(page));
    }


    @GetMapping("/products/getAll")
    public ProductResponse2 getALl(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.paginate(
                request.getPage(), request.getLimit(), request.getFilters(), request.getSearchProductKey(), request.getSearchPn(), request.getSearchStatus(), request.getSearchPrice(), request.getOrders());
        return new ProductResponse2(ProductMapper.toPageDTO2(page));
    }

    @GetMapping("/indexProductsDiscount")
    public ProductResponse indexProductsDiscount(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.indexProductsDiscount(
                request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new ProductResponse(ProductMapper.toPageDTO(page));
    }

    @GetMapping("/products/all")
    public ProductResponse getAll() {
        List<ProductEntity> product = this.productRepository.findAll();
        return new ProductResponse(ProductMapper.toListDTO(product));
    }
    @GetMapping("/products/exportExcel")
    public List<ProductExcelDTO> exportExcel() {
        List<ProductEntity> product = this.productRepository.findAll();
        List<ProductExcelDTO> pro=new ArrayList<>();
        int i;
        for(i=0;i<product.size();i++){
            ProductExcelDTO proExcel=new ProductExcelDTO(product.get(i));
            pro.add(proExcel);
        }
        return pro;
    }
    @GetMapping("/products/checkQuantity")
    public List<ProductQuantityDTO> checkQuantity() {
        List<ProductEntity> product = this.productRepository.findAll();
        List<ProductQuantityDTO> pro=new ArrayList<>();
        int i;
        for(i=0;i<product.size();i++){
            ProductQuantityDTO proQ=new ProductQuantityDTO(product.get(i));
            pro.add(proQ);
        }
        return pro;
    }

    @GetMapping("/products/getAllProAccess")
    public ProductFilterResponse getAllProAccess(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.paginate(
                request.getPage(), request.getLimit(), request.getFilters(), request.getSearchProductKey(), request.getSearchPn(), request.getSearchStatus(), request.getSearchPrice(), request.getOrders());
        return new ProductFilterResponse(ProductMapper.toPageDTOFilter(page));
    }

    @GetMapping("/products/allProDiscount")
    public ProductResponse getAllProDiscount(ProductPaginationRequest productPaginationRequest) {
        Page<ProductEntity> product = this.productService.findProductByStatus(productPaginationRequest.getPage(), productPaginationRequest.getLimit(), productPaginationRequest.getFilters(), productPaginationRequest.getOrders());
        return new ProductResponse(ProductMapper.getInstance().toPageDTO(product));
    }

    @GetMapping("/products/allProWithDiscount")
    public ProductResponse2 getAllProWithDiscount() {
        List<ProductEntity> product = this.productRepository.getProductWithDiscount();
        return new ProductResponse2(ProductMapper.getInstance().toListDTO2(product));
    }

    @GetMapping("/products/{id}")
    public ProductDTOById getById(@PathVariable Long id) {
        ProductDTOById product = this.productService.findById(id);
        return product;
    }

    @PostMapping("/staff/products")
    public ProductResponse create(
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult)
            throws CustomException,
            CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.insert(productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @PutMapping("/products/discountProduct/{id}")
    public ProductResponse discount(
            @PathVariable("id") Long id,
            @Valid @RequestBody List<Long> idProduct,
            BindingResult bindingResult
    ) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        List<ProductEntity> productEntity = productService.discount(id, idProduct);
        return new ProductResponse(ProductMapper.getInstance().toListDTO(productEntity));
    }

    @PutMapping("/products/noDiscountProduct/{id}/{idPro}")
    public ProductResponse nodiscount(
            @PathVariable("id") Long id,
            @PathVariable("idPro") Long idPro
    ) throws CustomValidationException, CustomException {
        ProductEntity productEntity = productService.noDiscount(id, idPro);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }


    @PutMapping("/products/{id}")
    public ProductResponse edit(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.update(id, productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @PutMapping("/products/active/{id}")
    public ProductResponse active(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        ProductEntity postEntity = productService.active(id);
        return new ProductResponse(ProductMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/products/inactive/{id}")
    public ProductResponse inActive(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        ProductEntity postEntity = productService.inActive(id);
        return new ProductResponse(ProductMapper.getInstance().toDTO(postEntity));
    }

    @PostMapping("/staff/products/copy/{productId}")
    public ProductResponse copyProduct(
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult, @PathVariable("productId") Long productId)
            throws CustomException,
            CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.copyProduct(productRequest, productId);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    //lấy ra những sản phẩm cùng loại
    @GetMapping("/auth/product/{id}/category")
    public List<ProductDTOById> getProductByCategory(@PathVariable("id") Long id) {
        List<ProductDTOById> product = this.productService.findProductByCategory(id);
        return product;
    }
}
