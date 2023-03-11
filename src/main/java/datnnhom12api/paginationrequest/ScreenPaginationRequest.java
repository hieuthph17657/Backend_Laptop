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
public class ScreenPaginationRequest extends PaginationRequest {
    private String searchSize;
    private String searchScreenType;
    private String searchScreenTechnology;
    private String searchStatus;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchSize != null && !searchSize.isEmpty()) {
            list.add(new Filter("size", QueryOperator.LIKE, searchSize, null));
        }
        if (searchScreenType != null && !searchScreenType.isEmpty()) {
            list.add(new Filter("screenType", QueryOperator.LIKE, searchScreenType, null));
        }
        if (searchScreenTechnology != null && !searchScreenTechnology.isEmpty()) {
            list.add(new Filter("screenTechnology", QueryOperator.LIKE, searchScreenTechnology, null));
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        return list;
    }
}
