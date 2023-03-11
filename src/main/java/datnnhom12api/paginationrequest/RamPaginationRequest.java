package datnnhom12api.paginationrequest;

import datnnhom12api.core.Filter;
import datnnhom12api.core.PaginationRequest;
import datnnhom12api.core.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RamPaginationRequest extends PaginationRequest {

    private String searchRamCapacity;

    private String searchStatus;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchRamCapacity != null && !searchRamCapacity.isEmpty()) {
            list.add(new Filter("ramCapacity", QueryOperator.LIKE, searchRamCapacity, null));
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        return list;
    }
}
