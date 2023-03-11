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
public class CardPaginationrequest extends PaginationRequest {
    private String searchTrandemark;

    private String searchModel;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchTrandemark != null && !searchTrandemark.isEmpty()) {
            list.add(new Filter("trandemark", QueryOperator.LIKE, searchTrandemark, null));
        }
        if (searchModel != null && !searchModel.isEmpty()) {
            list.add(new Filter("model", QueryOperator.LIKE, searchModel, null));
        }
        return list;
    }
}
