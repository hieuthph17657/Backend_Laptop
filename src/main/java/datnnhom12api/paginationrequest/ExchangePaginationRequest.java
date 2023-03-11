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
public class ExchangePaginationRequest extends PaginationRequest {
    private String searchStatus;

    private String check;

    private String searchName;

    private String searchPhone;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        if (check != null && !check.isEmpty()) {
            list.add(new Filter("isCheck", QueryOperator.LIKE, check, null));
        }
        return list;
    }
}