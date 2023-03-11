package datnnhom12api.paginationrequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import datnnhom12api.core.Filter;
import datnnhom12api.core.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPaginationRequest extends PaginationRequest {


    private String searchStartDate;

    private String searchEndDate;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
//        if (searchStartDate != null && !searchStartDate.isEmpty()) {
//            long milliSeconds = Timestamp.valueOf(searchStartDate).getTime();
//            list.add(new Filter("startDate", QueryOperator.GREATER_THAN, milliSeconds+"", null));
//        }
//        if (searchEndDate != null) {
//            list.add(new Filter("endDate", QueryOperator.LESS_THAN, searchEndDate, null));
//        }
        return list;
    }
}
