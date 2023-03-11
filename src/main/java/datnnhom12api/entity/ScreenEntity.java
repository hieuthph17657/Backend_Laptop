package datnnhom12api.entity;
import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.ScreenRequest;
import datnnhom12api.utils.support.RamStatus;
import datnnhom12api.utils.support.ScreenStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "screens")
@EqualsAndHashCode(callSuper = true)
public class ScreenEntity  extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "screen")
    private List<ProductEntity> products;

    @Column(name = "size")
    private String size;

    @Column(name = "screen_technology")
    private String screenTechnology;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "screen_type")
    private String screenType;

    @Column(name = "scanFrequency")
    private String scanFrequency;

    @Column(name = "background_panel")
    private String backgroundPanel;

    @Column(name = "brightness")
    private String brightness;

    @Column(name = "color_coverage")
    private String colorCoverage;

    @Column(name = "screen_ratio")
    private String screenRatio;

    @Column(name = "touch_screen")
    private String touchScreen;

    @Column(name = "contrast")
    private String contrast;

    private String status;

    public void setData(ScreenRequest request) {
        this.contrast=request.getContrast();
        this.touchScreen = request.getTouchScreen();
        this.colorCoverage = request.getColorCoverage();
        this.brightness = request.getBrightness();
        this.backgroundPanel = request.getBackgroundPanel();
        this.screenRatio=request.getScreenRatio();
        this.screenType = request.getScreenType();
        this.scanFrequency=request.getScanFrequency();
        this.resolution = request.getResolution();
        this.screenTechnology = request.getScreenTechnology();
        this.size = request.getSize();
        this.status = request.getStatus();
    }
}
