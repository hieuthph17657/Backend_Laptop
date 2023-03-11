package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

    public void setData(RoleEntity request) {
        this.role = request.getRole();
    }
}
