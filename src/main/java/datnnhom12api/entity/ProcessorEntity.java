package datnnhom12api.entity;


import datnnhom12api.core.BaseEntity;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.request.ProcessorRequest;
import datnnhom12api.utils.support.CategoryStatus;
import datnnhom12api.utils.support.ProcessorStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "processors")
@EqualsAndHashCode(callSuper = true)
public class ProcessorEntity extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(mappedBy = "processor")
    private List<ProductEntity> products;

    @Column(name = "cpu_company")
    private String cpuCompany;

    @Column(name = "cpu_technology")
    private String cpuTechnology;

    @Column(name = "cpu_type")
    private String cpuType;

    @Column(name = "cpu_speed")
    private String cpuSpeed;

    @Column(name = "max_speed")
    private String maxSpeed;

    @Column(name = "multiplier")
    private int multiplier;

    @Column(name = "number_of_thread")
    private int numberOfThread;

    @Column(name = "caching")
    private String caching;

    @Enumerated(EnumType.STRING)
    private ProcessorStatus status;

    public void setData(ProcessorRequest request) {
        this.cpuCompany = request.getCpuCompany();
        this.caching = request.getCaching();
        this.cpuSpeed = request.getCpuSpeed();
        this.multiplier = request.getMultiplier();
        this.cpuTechnology = request.getCpuTechnology();
        this.cpuType = request.getCpuType();
        this.maxSpeed = request.getMaxSpeed();
        this.numberOfThread = request.getNumberOfThread();
        this.status = request.getStatus()
                == ProcessorStatus.DRAFT ? ProcessorStatus.DRAFT
                : ProcessorStatus.ACTIVE ;
    }
}
