package datnnhom12api.paginationrequest;

import datnnhom12api.core.Filter;
import datnnhom12api.core.PaginationRequest;
import datnnhom12api.core.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaginationRequest extends PaginationRequest {
    private String searchAddress;

    private String searchStatus;

    private String searchPayment;

    private String searchUserId;

    private String searchName;

    private String searchStartDate;

    private String searchEndDate;

    private String searchPhone;

    private String searchMoney;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchAddress != null && !searchAddress.isEmpty()) {
            list.add(new Filter("address", QueryOperator.LIKE, searchAddress, null));
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        if (searchName != null && !searchName.isEmpty()) {
            list.add(new Filter("customerName", QueryOperator.LIKE, searchName, null));
        }
        if (searchPayment != null && !searchPayment.isEmpty()) {
            list.add(new Filter("payment", QueryOperator.EQUALS, searchPayment, null));
        }
        if (searchPhone != null && !searchPhone.isEmpty()) {
            list.add(new Filter("phone", QueryOperator.EQUALS, searchPhone, null));
        }
        return list;
    }
}
