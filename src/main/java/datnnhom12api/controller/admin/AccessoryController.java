package datnnhom12api.controller.admin;

import datnnhom12api.entity.AccessoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.accessoryMapper;
import datnnhom12api.paginationrequest.accessoryPaginationRequest;
import datnnhom12api.request.AccessoryRequest;
import datnnhom12api.response.accessoryResponse;
import datnnhom12api.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AccessoryController {
    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/api/accessory/getAll")
    public accessoryResponse getAll() {
        return new accessoryResponse(accessoryMapper.getInstance().toListDTO(accessoryService.findAll()));
    }

    @GetMapping("/api/auth/accessory")
    public accessoryResponse index(@Valid accessoryPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<AccessoryEntity> page = accessoryService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new accessoryResponse(accessoryMapper.toPageDTO(page));
    }

    @PostMapping("/api/staff/accessory")
    public accessoryResponse create(@Valid @RequestBody AccessoryRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        AccessoryEntity postEntity = accessoryService.create(post);
        return new accessoryResponse(accessoryMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/api/staff/accessory/{id}")
    public accessoryResponse edit(@PathVariable("id") Long id, @Valid @RequestBody AccessoryRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        AccessoryEntity postEntity = accessoryService.edit(id, post);
        return new accessoryResponse(accessoryMapper.getInstance().toDTO(postEntity));
    }
}