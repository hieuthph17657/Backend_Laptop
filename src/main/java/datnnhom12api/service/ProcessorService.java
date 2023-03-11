package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProcessorDTO;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.ProcessorRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProcessorService {
    Page<ProcessorEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);

    ProcessorEntity create(ProcessorRequest post);

    ProcessorEntity update(Long id, ProcessorRequest post) throws CustomException;

    void delete(Long id);

    ProcessorEntity getById(Long id);

    ProcessorDTO active(Long id) throws CustomException;

    ProcessorDTO inactive(Long id) throws CustomException;

    List<ProcessorEntity> findAll();
}
