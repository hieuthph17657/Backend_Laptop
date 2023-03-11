package datnnhom12api.dto;

import datnnhom12api.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDTO implements Serializable {

    private String name;

    private Long return_id;

    private Long exchange_id;
}
