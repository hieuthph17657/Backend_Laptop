package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.RamDTO;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.RamRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface RamService {

    Page<RamEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);

    RamEntity create(RamRequest post);

    RamEntity update(Long id, RamRequest post) throws CustomException;

    void delete(Long id);

    RamDTO active(Long id) throws CustomException;

    RamDTO inactive(Long id) throws CustomException;

    List<RamEntity> findAll();
}
