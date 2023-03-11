package datnnhom12api.request;

import datnnhom12api.utils.support.CategoryStatus;
import datnnhom12api.utils.support.ProcessorStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessorRequest {

    @NotBlank(message = "Hãng cpu không được để trống")
    private String cpuCompany;

    @NotBlank(message = "Công nghệ cpu không được để trống")
    private String cpuTechnology;

    @NotBlank(message = "Loại cpu không được để trống")
    private String cpuType;

    @NotBlank(message = "Tốc độ cpu không được để trống")
    private String cpuSpeed;

    @NotBlank(message = "Tốc độ cpu không được để trống")
    private String maxSpeed;

    @NotNull(message = "Số nhân không được để trống")
    private int multiplier;

    @NotNull(message = "Số luồng không được để trống")
    private int numberOfThread;

    @NotBlank(message = "Bộ nhớ đệm không được để trống")
    private String caching;

    @Enumerated(EnumType.STRING)
    private ProcessorStatus status;
}
