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
public class StaffPaginationRequest extends PaginationRequest {

    private String searchUsername;

    private Integer searchStatus;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchUsername != null && !searchUsername.isEmpty()) {
            list.add(new Filter("username", QueryOperator.LIKE, searchUsername, null));
        }
        if (searchStatus != null) {
            list.add(new Filter("status", QueryOperator.EQUALS, String.valueOf(searchStatus), null));
        }
        return list;
    }
}
