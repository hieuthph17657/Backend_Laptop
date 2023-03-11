package datnnhom12api.dto;

import datnnhom12api.entity.UserEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InformationDTO implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private int active;
    private Long userId;
}
