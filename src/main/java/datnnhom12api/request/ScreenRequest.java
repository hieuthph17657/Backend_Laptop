package datnnhom12api.request;

import datnnhom12api.utils.support.ScreenStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenRequest {

    @NotBlank(message = "size màn hình không được trống")
    private String size;

    @NotBlank(message = "công nghệ màn hình không được trống")
    private String screenTechnology;

    @NotBlank(message = "độ phân giải màn hình không được trống")
    private String resolution;

    @NotBlank(message = "loại màn hình không được trống")
    private String screenType;

    //@NotBlank(message = "tần số quét không được trống")
    private String scanFrequency;

    @NotBlank(message = "tấm nền màn hình không được trống")
    private String backgroundPanel;

    @NotBlank(message = "độ sáng màn hình không được trống")
    private String brightness;

    @NotBlank(message = "độ phủ màu màn hình không được trống")
    private String colorCoverage;

    //@NotBlank(message = "tỉ lệ màn hình không được trống")
    private String screenRatio;

    @NotBlank(message = "màn hình cảm ứng không được trống")
    private String touchScreen;

    //@NotBlank(message ="độ tương phản không được trống")
    private String contrast;

    private String status;
}
