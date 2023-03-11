package datnnhom12api.controller.admin;

import datnnhom12api.dto.StorageDTO;
import datnnhom12api.entity.StorageEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ScreenMapper;
import datnnhom12api.mapper.StorageMapper;
import datnnhom12api.paginationrequest.StoragePaginationRequest;
import datnnhom12api.request.StorageRequest;
import datnnhom12api.response.StorageResponse;
import datnnhom12api.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/getAll")
    public StorageResponse getAll() {
        return new StorageResponse(StorageMapper.getInstance().toListDTO(storageService.findAll()));
    }

    @GetMapping
    public StorageResponse index(@Valid StoragePaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<StorageEntity> page = storageService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new StorageResponse(StorageMapper.toPageDTO(page));
    }

    @PostMapping()
    public StorageResponse create(@Valid @RequestBody StorageRequest storage, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StorageEntity storageEntity = storageService.create(storage);
        return new StorageResponse(StorageMapper.getInstance().toDTO(storageEntity));
    }

    @PutMapping("/{id}")
    public StorageResponse edit(@PathVariable("id") Long id, @Valid @RequestBody StorageRequest storage, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StorageEntity storageEntity = storageService.update(id, storage);
        return new StorageResponse(StorageMapper.getInstance().toDTO(storageEntity));
    }

    @DeleteMapping("/{id}")
    public StorageResponse delete(@PathVariable("id") Long id) throws CustomException {
        storageService.delete(id);
        return new StorageResponse();
    }



}
