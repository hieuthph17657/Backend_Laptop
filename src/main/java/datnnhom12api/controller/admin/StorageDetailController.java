package datnnhom12api.controller.admin;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.StorageDetailEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CartMapper;
import datnnhom12api.mapper.StorageDetailMapper;
import datnnhom12api.mapper.StorageMapper;
import datnnhom12api.paginationrequest.StoragePaginationRequest;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.StorageDetailRequest;
import datnnhom12api.response.CartResponse;
import datnnhom12api.response.StorageDetailResponse;
import datnnhom12api.response.StorageResponse;
import datnnhom12api.service.StorageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/storage_details")
public class StorageDetailController {

    @Autowired
    private StorageDetailService storageDetailService;

    @GetMapping("/getAll")
    public StorageDetailResponse getAll() {
        return new StorageDetailResponse(StorageDetailMapper.getInstance().toListDTO(storageDetailService.findAll()));
    }

    @GetMapping
    public StorageDetailResponse index(@Valid StoragePaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<StorageDetailEntity> page = storageDetailService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new StorageDetailResponse(StorageDetailMapper.toPageDTO(page));
    }

    @PostMapping()
    public StorageDetailResponse create(@Valid @RequestBody StorageDetailRequest storageDetail, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StorageDetailEntity storageDetailEntity = storageDetailService.create(storageDetail);
        return new StorageDetailResponse(StorageDetailMapper.getInstance().toDTO(storageDetailEntity));
    }

    @PutMapping("/{id}")
    public StorageDetailResponse edit(@PathVariable("id") Long id, @Valid @RequestBody StorageDetailRequest storageDetail, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StorageDetailEntity storageDetailEntity = storageDetailService.update(id, storageDetail);
        return new StorageDetailResponse(StorageDetailMapper.getInstance().toDTO(storageDetailEntity));
    }

    @DeleteMapping("/{id}")
    public StorageDetailResponse delete(@PathVariable("id") Long id) throws CustomException {
        storageDetailService.delete(id);
        return new StorageDetailResponse();
    }



}
