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
public class BatteryChargerPaginationRequest extends PaginationRequest{
        private String searchBatteryType;

        private String searchBattery;

        private String searchCharger;

        public List<Filter> getFilters() {
            List<Filter> list = new ArrayList<>();
            if (searchBatteryType != null && !searchBatteryType.isEmpty()) {
                list.add(new Filter("batteryType", QueryOperator.LIKE, searchBatteryType, null));
            }
            if (searchBattery != null && !searchBattery.isEmpty()) {
                list.add(new Filter("battery", QueryOperator.LIKE, searchBattery, null));
            }
            if (searchCharger != null && !searchCharger.isEmpty()) {
                list.add(new Filter("charger", QueryOperator.LIKE, searchCharger, null));
            }
            return list;
        }
}
