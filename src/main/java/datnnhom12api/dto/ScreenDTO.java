package datnnhom12api.dto;


import datnnhom12api.core.BaseDTO;
import datnnhom12api.utils.support.ScreenStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ScreenDTO extends BaseDTO implements Serializable {

    private Long id;

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

    private ScreenStatus status;
}
