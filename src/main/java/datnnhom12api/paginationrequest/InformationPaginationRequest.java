package datnnhom12api.paginationrequest;

import datnnhom12api.core.Filter;
import datnnhom12api.core.QueryOperator;

import java.util.ArrayList;
import java.util.List;

public class InformationPaginationRequest {
    private String searchName;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchName != null && !searchName.isEmpty()) {
            list.add(new Filter("name", QueryOperator.LIKE, searchName, null));
        }
        return list;
    }
}
