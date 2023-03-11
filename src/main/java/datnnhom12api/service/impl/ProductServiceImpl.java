package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDTO2;
import datnnhom12api.dto.ProductDTOById;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.*;
import datnnhom12api.request.ImageRequest;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.service.ProductService;
import datnnhom12api.specifications.ProductSpecifications;
import datnnhom12api.utils.support.ProductStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service("productService")
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ManufactureRepository manufactureRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AccessoryProductRepository accessoryProductRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    AccessoryRepository accessoryRepository;

    @Autowired
    OriginRepository originRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BatteryChargerRepository batteryChargerRepository;

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    RamRepository ramRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    YearRepository yearRepository;


    @Autowired
    WinRepository winRepository;

    @Override
    public ProductEntity insert(ProductRequest productRequest) throws CustomException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setData(productRequest);
        productEntity.setOrigin(this.originRepository.getById(productRequest.getOriginId()));
        productEntity.setScreen(this.screenRepository.getById(productRequest.getScreenId()));
        productEntity.setWin(this.winRepository.getById(productRequest.getWinId()));
        productEntity.setStorage(this.storageRepository.getById(productRequest.getStorageId()));
        productEntity.setRam(this.ramRepository.getById(productRequest.getRamId()));
        productEntity.setProcessor(this.processorRepository.getById(productRequest.getProcessorId()));
        productEntity.setCard(this.cardRepository.getById(productRequest.getCardId()));
        productEntity.setCardOnboard(this.cardRepository.getById(productRequest.getCardOnboard()));
        productEntity.setBattery(this.batteryChargerRepository.getById(productRequest.getBatteryId()));
        List<ImageRequest> list = productRequest.getImages();
        for (ImageRequest imageRequest : list) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }
        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufacture(manufacture);
        productEntity = productRepository.save(productEntity);
        Long id = productEntity.getId();
        productRequest.getAccessoryId().forEach(access -> {
            AccessoryProductEntity accessoryProductEntity = new AccessoryProductEntity();
            accessoryProductEntity.setProduct(productRepository.getById(id));
            accessoryProductEntity.setAccessory(accessoryRepository.getById(access));
            this.accessoryProductRepository.save(accessoryProductEntity);
        });
        productRequest.getColorId().forEach(color -> {
            ProductColorEntity pColorEntity = new ProductColorEntity();
            pColorEntity.setColor(colorRepository.getById(color));
            pColorEntity.setProduct(productRepository.getById(id));
            this.productColorRepository.save(pColorEntity);
        });

        productRequest.getCategoryId().forEach(cate -> {
            ProductCategoryEntity productCategory = new ProductCategoryEntity();
            productCategory.setProduct(productRepository.getById(id));
            productCategory.setCategory(categoryRepository.getById(cate));
            this.productCategoryRepository.save(productCategory);
        });
        YearEntity year = new YearEntity();
        int yearCurrent = Year.now().getValue();
        year.setProduct(this.productRepository.getById(id));
        year.setPrice(productRequest.getPrice());
        year.setYear(String.valueOf(yearCurrent));
        this.yearRepository.save(year);
        return productEntity;
    }

    @Override
    public ProductEntity update(Long id, ProductRequest productRequest) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "Mã sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
        }
        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setData(productRequest);
        productEntity.setRam(this.ramRepository.getById(productRequest.getRamId()));
        productEntity.setWin(this.winRepository.getById(productRequest.getWinId()));
        productEntity.setScreen(this.screenRepository.getById(productRequest.getScreenId()));
        productEntity.setStorage(this.storageRepository.getById(productRequest.getStorageId()));
        productEntity.setCard(this.cardRepository.getById(productRequest.getCardId()));
        productEntity.setCardOnboard(this.cardRepository.getById(productRequest.getCardOnboard()));
        productEntity.setBattery(this.batteryChargerRepository.getById(productRequest.getBatteryId()));
        productEntity.setProcessor(this.processorRepository.getById(productRequest.getProcessorId()));
//        if (productEntity.getId() != null) {
//            imageRepository.deleteAllByProductId(productEntity.getId());
//        }
        this.productCategoryRepository.deleteAllProductCategoryByProductId(id);
        productRequest.getCategoryId().forEach(cate -> {
            ProductCategoryEntity productCategoryE = new ProductCategoryEntity();
            productCategoryE.setProduct(productRepository.getById(id));
            productCategoryE.setCategory(categoryRepository.getById(cate));
            this.productCategoryRepository.save(productCategoryE);
        });

        List<ImageRequest> list = productRequest.getImages();
        System.out.println("--------------- images request");
        System.out.println(productRequest.getImages().size());
        for (ImageRequest imageRequest : list) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }

        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufacture(manufacture);
        productEntity = productRepository.save(productEntity);


        productColorRepository.deleteAllProductColorByProductId(id);
        List<Long> longList = productRequest.getColorId();
        for (Long color : longList) {
            ProductColorEntity pColorEntity = new ProductColorEntity();
            pColorEntity.setColor(colorRepository.getById(color));
            pColorEntity.setProduct(productRepository.getById(id));
            this.productColorRepository.save(pColorEntity);
        }
        accessoryProductRepository.deleteAllAccessoryProductByProductId(id);
        List<Long> longs = productRequest.getAccessoryId();
        for (Long access : longs) {
            AccessoryProductEntity accessoryProductEntity = new AccessoryProductEntity();
            accessoryProductEntity.setAccessory(accessoryRepository.getById(access));
            accessoryProductEntity.setProduct(productRepository.getById(id));
            this.accessoryProductRepository.save(accessoryProductEntity);
        }
        return productEntity;
    }

    @Override
    public ProductEntity delete(Long id) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy sản phẩm");
        }
        ProductEntity productEntity = productRepository.getById(id);
        productRepository.delete(productEntity);
        return productEntity;
    }

    @Override
    public Page<ProductEntity> paginate(int page, int limit, List<Filter> filters, String searchProductKey, String searchPn, String searchStatus, String searchPrice, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ProductEntity> specifications = ProductSpecifications.getInstance().getQueryResult(filters);
        Double endPrice = Double.valueOf(0);
        if (searchPrice != null && !searchPrice.equals("") && Integer.parseInt(searchPrice) < 10000000) {
            searchPrice = 0 + "";
            endPrice = Double.valueOf(9999999);
        } else if (searchPrice != null && !searchPrice.equals("") && Integer.parseInt(searchPrice) >= 10000000 && Integer.parseInt(searchPrice) < 15000000) {
            searchPrice = 10000000 + "";
            endPrice = Double.valueOf(15000000);
        } else if (searchPrice != null && !searchPrice.equals("") && Integer.parseInt(searchPrice) >= 15000000 && Integer.parseInt(searchPrice) < 20000000) {
            searchPrice = 15000000 + "";
            endPrice = Double.valueOf(20000000);
        } else if (searchPrice != null && !searchPrice.equals("") && Integer.parseInt(searchPrice) >= 20000000) {
            searchPrice = 20000001 + "";
            endPrice = Double.valueOf(100000000);
        }
        if ((searchProductKey != null && searchPn != null && searchStatus != null && searchPrice != null) && (!searchProductKey.equals("") && !searchPn.equals("") && !searchStatus.equals("") && !searchPrice.equals(""))) {
            return productRepository.findProductByKeyAll(searchProductKey, searchPn, searchStatus, Double.valueOf(searchPrice), endPrice, specifications, pageable);
        } else if ((searchProductKey != null && searchPn != null && searchStatus != null) && (!searchProductKey.equals("") && !searchPn.equals("") && !searchStatus.equals(""))) {
            return productRepository.findProductByKeyDontPrice(searchProductKey, searchPn, searchStatus, specifications, pageable);
        } else if ((searchProductKey != null && searchPn != null && searchPrice != null) && (!searchProductKey.equals("") && !searchPn.equals("") && !searchPrice.equals(""))) {
            return productRepository.findProductByKeyDontStatus(searchProductKey, searchPn, Double.valueOf(searchPrice), endPrice, specifications, pageable);
        } else if ((searchProductKey != null && searchStatus != null && searchPrice != null) && (!searchProductKey.equals("") && !searchStatus.equals("") && !searchPrice.equals(""))) {
            return productRepository.findProductByKeyDontImei(searchProductKey, searchStatus, Double.valueOf(searchPrice), endPrice, specifications, pageable);
        } else if ((searchProductKey != null && searchPn != null) && (!searchProductKey.equals("") && !searchPn.equals(""))) {
            return productRepository.findProductByKeyDontPriceAndStatus(searchProductKey, searchPn, specifications, pageable);
        } else if ((searchProductKey != null && searchStatus != null) && (!searchProductKey.equals("") && !searchStatus.equals(""))) {
            return productRepository.findProductByKeyDontPriceAndImei(searchProductKey, searchStatus, specifications, pageable);
        } else if ((searchProductKey != null && searchPrice != null) && (!searchProductKey.equals("") && !searchPrice.equals(""))) {
            return productRepository.findProductByKeyDontStatusAndImei(searchProductKey, Double.valueOf(searchPrice), endPrice, specifications, pageable);
        } else if (!searchPrice.isEmpty() && !searchPn.isEmpty()) {
            return productRepository.findProductByPriceAndImei(Double.valueOf(searchPrice), endPrice, searchPn, specifications, pageable);
        } else if (!searchPrice.isEmpty() && !searchStatus.isEmpty()) {
            return productRepository.findProductByPriceAndStatus(Double.valueOf(searchPrice), endPrice, searchStatus, specifications, pageable);
        }else if (!searchPn.isEmpty() && !searchStatus.isEmpty()) {
            return productRepository.findProductByImeiAndStatus(searchPn, searchStatus, specifications, pageable);
        }else if (!searchProductKey.isEmpty() && !searchStatus.isEmpty()) {
            return productRepository.findProductByProductKeyAndStatus(searchProductKey, searchStatus, specifications, pageable);
        } else if ((searchProductKey != null) && (!searchProductKey.equals(""))) {
            return productRepository.findProductByKeyDontPriceAndStatusAndImei(searchProductKey, specifications, pageable);
        } else if ((searchPrice != null) && (!searchPrice.equals(""))) {
            return productRepository.findProductByPrice(Double.valueOf(searchPrice), endPrice, specifications, pageable);
        } else {
            return productRepository.findAll(specifications, pageable);
        }
    }

    @Override
    public Page<ProductEntity> indexProductsDiscount(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepository.findProductsHasDiscount(pageable);
    }

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public ProductDTOById findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity productEntity = this.productRepository.getById(id);
        this.enrichImage(productEntity);
        ProductDTOById productDTO = modelMapper.map(productEntity, ProductDTOById.class);
        return productDTO;
    }

    @Override
    public List<ProductEntity> discount(Long id, List<Long> idProduct) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        DiscountEntity discountEntity = discountEntityOptional.get();
        List<ProductEntity> list = productRepository.findAll();
        ProductEntity productEntity = null;
        List<ProductEntity> listdiscountProduct = new ArrayList<>();
        for (ProductEntity product : list) {
            for (Long iP : idProduct) {
                if (product.getId() == iP && product.getDiscount() == null) {
                    Optional<ProductEntity> productEntityOptional = productRepository.findById(iP);
                    if (id <= 0) {
                        throw new CustomException(403, "Mã sản phẩm phải lớn hơn 0");
                    }
                    if (productEntityOptional.isEmpty()) {
                        throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
                    }
                    productEntity = productEntityOptional.get();
                    productEntity.setDiscount(discountEntity);
                    productEntity.setPrice(Math.ceil(productEntity.getPrice() - (productEntity.getPrice() * discountEntity.getRatio() / 100)));
                    productEntity = productRepository.save(productEntity);
                    listdiscountProduct.add(productEntity);
                }
            }

        }
        return listdiscountProduct;
    }

    @Override
    public ProductEntity noDiscount(Long id, Long idPro) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        DiscountEntity discountEntity = discountEntityOptional.get();
        List<ProductEntity> list = productRepository.findAll();
        ProductEntity productEntity = null;
        Optional<ProductEntity> productEntityOptional = productRepository.findById(idPro);
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
        }
        for (ProductEntity product : list) {
            if (product.getDiscount() != null && product.getId() == idPro) {
                productEntity = productEntityOptional.get();
                productEntity.setDiscount(null);
                productEntity.setPrice(Math.ceil(productEntity.getPrice() / ((100 - discountEntity.getRatio()) / 100)));
                productEntity = productRepository.save(productEntity);
            }
        }

        return productEntity;
    }

    private void enrichImage(ProductEntity productEntity) {
        List<ImagesEntity> imagesEntities = this.imageRepository.findAllByProductId(productEntity.getId());
        productEntity.enrichListImage(imagesEntities);
    }

    @Override
    public ProductEntity active(Long id) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id sản phẩm muốn active");
        }
        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setStatus("ACTIVE");
        productEntity = productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity inActive(Long id) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id sản phẩm muốn active");
        }
        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setStatus("INACTIVE");
        productEntity = productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public SumProductDTO sumProduct() {
        SumProductDTO productDTO = this.productRepository.sumProduct();
        return productDTO;
    }

    @Override
    public ProductEntity copyProduct(ProductRequest productRequest, Long productId) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setData(productRequest);
        productEntity.setOrigin(this.originRepository.getById(productRequest.getOriginId()));
        productEntity.setScreen(this.screenRepository.getById(productRequest.getScreenId()));
        productEntity.setWin(this.winRepository.getById(productRequest.getWinId()));
        productEntity.setStorage(this.storageRepository.getById(productRequest.getStorageId()));
        productEntity.setRam(this.ramRepository.getById(productRequest.getRamId()));
        productEntity.setProcessor(this.processorRepository.getById(productRequest.getProcessorId()));
        productEntity.setCard(this.cardRepository.getById(productRequest.getCardId()));
        productEntity.setCardOnboard(this.cardRepository.getById(productRequest.getCardOnboard()));
        productEntity.setBattery(this.batteryChargerRepository.getById(productRequest.getBatteryId()));


        List<ImagesEntity> list = this.imageRepository.findAllByProductId(productId);
        for (ImagesEntity imageRequest : list) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setName(imageRequest.getName());
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }
        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufacture(manufacture);
        productEntity = productRepository.save(productEntity);
        Long id = productEntity.getId();
        productRequest.getAccessoryId().forEach(access -> {
            AccessoryProductEntity accessoryProductEntity = new AccessoryProductEntity();
            accessoryProductEntity.setProduct(productRepository.getById(id));
            accessoryProductEntity.setAccessory(accessoryRepository.getById(access));
            this.accessoryProductRepository.save(accessoryProductEntity);
        });
        productRequest.getColorId().forEach(color -> {
            ProductColorEntity pColorEntity = new ProductColorEntity();
            pColorEntity.setColor(colorRepository.getById(color));
            pColorEntity.setProduct(productRepository.getById(id));
            this.productColorRepository.save(pColorEntity);
        });

        productRequest.getCategoryId().forEach(cate -> {
            ProductCategoryEntity productCategory = new ProductCategoryEntity();
            productCategory.setProduct(productRepository.getById(id));
            productCategory.setCategory(categoryRepository.getById(cate));
            this.productCategoryRepository.save(productCategory);
        });
        YearEntity year = new YearEntity();
        int yearCurrent = Year.now().getValue();
        year.setProduct(this.productRepository.getById(id));
        year.setPrice(productRequest.getPrice());
        year.setYear(String.valueOf(yearCurrent));
        this.yearRepository.save(year);

        return productEntity;
    }

    @Override
    public List<ProductDTOById> findProductByCategory(Long id) {
        List<ProductEntity> list = this.productRepository.findProductByCategory(id);
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTOById> productDTO = list.stream().map(p -> modelMapper.map(p, ProductDTOById.class))
                .collect(Collectors.toList());
        return productDTO;
    }

    @Override
    public Page<ProductEntity> findProductByStatus(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ProductEntity> specifications = ProductSpecifications.getInstance().getQueryResult(filters);

        return productRepository.findProductByStatus(specifications, pageable);
    }

    @Override
    public List<ProductDTO> findAll() {
        List <ProductEntity> list = this.productRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDTO> productDTO = list.stream().map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTO;

    }
}
