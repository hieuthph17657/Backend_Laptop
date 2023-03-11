package datnnhom12api.controller.admin;

import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.BatteryChargerMapper;
import datnnhom12api.paginationrequest.BatteryChargerPaginationRequest;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.response.BatteryChargerResponse;
import datnnhom12api.service.BatteryChargerService;
import datnnhom12api.utils.support.BatteryChargerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BatteryChargerController {
    @Autowired
    private BatteryChargerService batteryChargerService;

    @GetMapping("/api/batteryCharger/getAll")
    public BatteryChargerResponse getAll() {
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toListDTO(batteryChargerService.findAll()));
    }

    @GetMapping("/api/auth/batteryCharger")
    public BatteryChargerResponse index(@Valid BatteryChargerPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<BatteryChargerEntity> page = batteryChargerService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new BatteryChargerResponse(BatteryChargerMapper.toPageDTO(page));
    }

    @PostMapping("/api/staff/batteryCharger")
    public BatteryChargerResponse create(@Valid @RequestBody BatteryChargerRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BatteryChargerEntity postEntity = batteryChargerService.create(post);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @GetMapping("/api/staff/batteryCharger/{id}")
    public BatteryChargerResponse getId(@PathVariable("id") Long id) throws CustomException{
        BatteryChargerEntity entity =batteryChargerService.getByIdBatteryCharger(id);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(entity));
    }

    @PutMapping("/api/admin/batteryCharger/{id}")
    public BatteryChargerResponse edit(@PathVariable("id") Long id, @Valid @RequestBody BatteryChargerRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BatteryChargerEntity postEntity = batteryChargerService.update(id, post);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/api/admin/batteryCharger/{id}")
    public BatteryChargerResponse delete(@PathVariable("id") Long id) throws CustomException {
        batteryChargerService.delete(id);
        return new BatteryChargerResponse();
    }

    @PutMapping("/api/admin/batteryCharger/close/{id}")
    public BatteryChargerResponse close(@PathVariable("id") Long id) throws CustomException {
        BatteryChargerEntity postEntity = batteryChargerService.close(id);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/api/admin/batteryCharger/open/{id}")
    public BatteryChargerResponse open(@PathVariable("id") Long id) throws CustomException {
        BatteryChargerEntity postEntity = batteryChargerService.open(id);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @PostMapping("/api/staff/batteryCharger/draft")
    public BatteryChargerResponse draft(@RequestBody BatteryChargerRequest post, BindingResult bindingResult)throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        post.setStatus(BatteryChargerStatus.DRAFT);
        BatteryChargerEntity postEntity = batteryChargerService.create(post);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }
}
