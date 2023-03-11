package datnnhom12api.dto;

import datnnhom12api.core.BaseDTO;
import datnnhom12api.entity.ImagesEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.utils.support.ProductStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductExcelDTO{
//    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
    //private YearDTO year;
    private String imei;
    private Float weight;
    private Float height ;
    //private Integer warrantyPeriod;
    private Float width;
    private Float length;
    private String debut;
    //win
    //private WinDTO win;
    private String nameWin;
    private String version;
    //
    private String description;
    private String material;
//    private String created_by;
//    private String updated_by;
    //cate
    //private List<ProductCategoryDTO> categoryProducts;
    private String nameCategory;
    //

    //manu
    //private ManufactureDTO manufacture;
    private String nameManufacture;
    //
    private String images;

    //color
    //private List<ProductColorDTO> productColors;
    private String nameColor;
    //

    //access
    //private List<AccessoryProductDTO> accessoryProducts;
    private String nameAccessory;
    private String descriptionAccessory;
    //
    private String status;

    //process
    //private ProcessorDTO processor;
    private String cpuCompany;
    private String cpuTechnology;
    private String cpuType;
    private String cpuSpeed;
    private String maxSpeed;
    private int multiplier;
    private int numberOfThread;
    private String caching;
    //

    //ram
    //private RamDTO ram;
    private String ramCapacity;
    private String typeOfRam;
    private String ramSpeed;
    private String maxRamSupport;
    private int onboardRam;
    private int looseSlot;
    private int remainingSlot;
    //

    //screen
    //private ScreenDTO screen;
    private String size;//Kích thước màn hình
    private String screenTechnology;//Công nghệ màn hình
    private String resolution;//Độ phân giải
    private String screenType;//Loại màn hình
    private String scanFrequency; //Tần số quét
    private String backgroundPanel;//Tấm nền
    private String brightness;//Độ sáng
    private String colorCoverage;//Độ phủ màu
    private String screenRatio;//Tỷ lệ màn hình
    private String touchScreen;// màn hình cảm ứng
    private String contrast;// độ tương phản
    //

    //card
    //private CardDTO card;
    private String trandemark;
    private String model;
    private String memory;
    //

    //origin
    //private OriginDTO origin;
    private String nameOrigin;
    //

    //storage
    //private StorageDTO storage;
    private String storageDetailId;
    private int total;
    private int number;
    //

    private String security;

    //cardOnboard
    //private CardDTO cardOnboard;
    private String trandemarkOnboard;
    private String modelOnboard;
    private String memoryOnboard;
    //

    //battery
    //private BatteryChargerDTO battery;
    private String batteryType;//loại pin
    private String battery;//pin
    private String charger;//sac
    //

    public ProductExcelDTO(ProductEntity entity) {
        //this.id = id;
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
//        this.year = entity.getY;
        this.imei = entity.getImei();
        this.weight = entity.getWeight();
        this.height = entity.getHeight();
        //this.warrantyPeriod = entity.getWarrantyPeriod();
        this.width = entity.getWidth();
        this.length = entity.getLength();
        this.debut = entity.getDebut();
        //this.win = entity.getWin();
        this.nameWin = entity.getWin().getName();
        this.version = entity.getWin().getVersion();
        this.description = entity.getDescription();
        this.material = entity.getMaterial();
//        this.created_by = entity.getCreatedBy().getUsername();
//        if(entity.getUpdatedBy()==null){
//            this.updated_by = null;
//        }else{
//            this.updated_by = entity.getUpdatedBy().getUsername();
//        }
        //this.categoryProducts = entity.getCategoryProducts();
        if(entity.getCategoryProducts()==null||entity.getCategoryProducts().isEmpty()){
            this.nameCategory = null;
        }else{
            this.nameCategory = entity.getCategoryProducts().get(0).getCategory().getName();
        }

        //this.manufacture = entity.getManufacture();
        this.nameManufacture = entity.getManufacture().getName();
        if(entity.getImages()==null||entity.getImages().isEmpty()){
            this.images = null;
        }else{
            this.images = entity.getImages().get(0).getName();

        }
        //this.productColors = productColors;
        this.nameColor = entity.getProductColors().get(0).getColor().getName();
        //this.accessoryProducts = accessoryProducts;
        this.nameAccessory = entity.getAccessoryProducts().get(0).getAccessory().getName();
        this.descriptionAccessory=entity.getAccessoryProducts().get(0).getAccessory().getDescription();
        this.status = entity.getStatus();
        //this.processor = processor;
        this.cpuCompany = entity.getProcessor().getCpuCompany();
        this.cpuTechnology = entity.getProcessor().getCpuTechnology();
        this.cpuType = entity.getProcessor().getCpuType();
        this.cpuSpeed = entity.getProcessor().getCpuSpeed();
        this.maxSpeed = entity.getProcessor().getMaxSpeed();
        this.multiplier = entity.getProcessor().getMultiplier();
        this.numberOfThread = entity.getProcessor().getNumberOfThread();
        this.caching = entity.getProcessor().getCaching();
        //this.ram = ram;
        this.ramCapacity = entity.getRam().getRamCapacity();
        this.typeOfRam = entity.getRam().getTypeOfRam();
        this.ramSpeed = entity.getRam().getRamSpeed();
        this.maxRamSupport = entity.getRam().getMaxRamSupport();
        this.onboardRam = entity.getRam().getOnboardRam();
        this.looseSlot = entity.getRam().getLooseSlot();
        this.remainingSlot = entity.getRam().getRemainingSlot();
        //this.screen = screen;
        this.size = entity.getScreen().getSize();
        this.screenTechnology = entity.getScreen().getScreenTechnology();
        this.resolution = entity.getScreen().getResolution();
        this.screenType = entity.getScreen().getScreenType();
        this.scanFrequency = entity.getScreen().getScanFrequency();
        this.backgroundPanel = entity.getScreen().getBackgroundPanel();
        this.brightness = entity.getScreen().getBrightness();
        this.colorCoverage = entity.getScreen().getColorCoverage();
        this.screenRatio = entity.getScreen().getScreenRatio();
        this.touchScreen = entity.getScreen().getTouchScreen();
        this.contrast = entity.getScreen().getContrast();
        //this.card = card;
        this.trandemark = entity.getCard().getTrandemark();
        this.model = entity.getCard().getModel();
        this.memory = entity.getCard().getMemory();
        //this.origin = origin;
        this.nameOrigin = entity.getOrigin().getName();
        //this.storage = storage;
        this.storageDetailId = entity.getStorage().getStorageDetail().getId()+"";
        this.total = entity.getStorage().getTotal();
        this.number = entity.getStorage().getNumber();
        this.security = entity.getSecurity();
        //this.cardOnboard = cardOnboard;
        this.trandemarkOnboard = entity.getCardOnboard().getTrandemark();
        this.modelOnboard = entity.getCardOnboard().getModel();
        this.memoryOnboard = entity.getCardOnboard().getMemory();
        //
        this.batteryType = entity.getBattery().getBatteryType();
        this.battery = entity.getBattery().getBattery();
        this.charger = entity.getBattery().getCharger();
    }


}

