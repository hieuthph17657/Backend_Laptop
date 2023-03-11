package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.WinEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.WinRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface WinService {
     Page<WinEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy);

     WinEntity create(WinRequest post);

     WinEntity update(Long id, WinRequest post) throws CustomException;

     void delete(Long id);

     WinEntity open(Long id) throws CustomException;

     WinEntity close(Long id) throws CustomException;

    List<WinEntity> findAll();
}
