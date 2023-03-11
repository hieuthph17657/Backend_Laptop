package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.BatteryChargerRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BatteryChargerService {

    BatteryChargerEntity create(BatteryChargerRequest request) throws CustomException;

    Page<BatteryChargerEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy);

    BatteryChargerEntity update(Long id, BatteryChargerRequest post) throws CustomException;

    BatteryChargerEntity delete(Long id) throws CustomException;

    BatteryChargerEntity getByIdBatteryCharger(Long id) throws CustomException;

    BatteryChargerEntity open(Long id) throws CustomException;

    BatteryChargerEntity close(Long id) throws CustomException;

    List<BatteryChargerEntity> findAll();
}
