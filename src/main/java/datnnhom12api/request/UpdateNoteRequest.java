package datnnhom12api.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateNoteRequest {

    @NotBlank(message = "Ghi chú không được để trống")
    private String note;

}
