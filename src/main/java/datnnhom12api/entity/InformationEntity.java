package datnnhom12api.entity;

import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.request.InformationRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "informations")
@EqualsAndHashCode(callSuper = true)
public class InformationEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="email")
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name="active")
    private int active;
    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;

    public void setData(InformationRequest request) {
        this.fullName = request.getFullName();
        this.email=request.getEmail();
        this.phoneNumber=request.getPhoneNumber();
        this.address=request.getAddress();
//        this.user=request.getUser();
//        this.active = request.getActive();
    }
}