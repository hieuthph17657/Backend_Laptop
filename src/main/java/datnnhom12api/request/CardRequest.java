package datnnhom12api.request;


import datnnhom12api.entity.CategoryEntity;
import lombok.Data;

@Data
public class CardRequest {
    private String trandemark;

    private String model;

    private String memory;

    private Double price;

    private CategoryEntity category;

}
