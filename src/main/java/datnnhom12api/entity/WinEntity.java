package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.WinRequest;
import datnnhom12api.utils.support.WinStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wins")
@EqualsAndHashCode(callSuper = true)
public class WinEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private String version;
    @Enumerated(EnumType.STRING)
    private WinStatus status;

    public void setData(WinRequest request) {
        this.name = request.getName();
        this.version = request.getVersion();
        this.status = request.getStatus() == WinStatus.DRAFT ? WinStatus.DRAFT :
                (request.getStatus() == WinStatus.ACTIVE ? WinStatus.ACTIVE : WinStatus.INACTIVE) ;
    }
}
