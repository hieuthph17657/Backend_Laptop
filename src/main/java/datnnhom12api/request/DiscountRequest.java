package datnnhom12api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import datnnhom12api.utils.support.DiscountStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountRequest {

    @NotBlank(message = "tiêu đề không được để trống")
    private String name;

    @NotNull(message = "tỉ lệ không được để trống")
    private Double ratio;

    @NotNull(message = "ngày bắt đầu không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @NotNull(message = "ngày kết thúc không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private DiscountStatus status;
}
