package datnnhom12api.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDTO implements Serializable {

    private Long id;
    private String trandemark;
    private String model;
    private String memory;
}
