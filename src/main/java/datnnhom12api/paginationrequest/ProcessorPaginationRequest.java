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
public class ProcessorPaginationRequest extends PaginationRequest {
    private String searchCpuCompany;

    private String searchStatus;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchCpuCompany != null && !searchCpuCompany.isEmpty()) {
            list.add(new Filter("cpuCompany", QueryOperator.LIKE, searchCpuCompany, null));
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        return list;
    }
}
