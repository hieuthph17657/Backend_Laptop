package datnnhom12api.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class StatisticalMonthDTO implements Serializable {

    @Id
    private int month;

    private double total;
}
