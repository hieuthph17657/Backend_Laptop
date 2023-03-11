package datnnhom12api.controller.admin;

import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.ScreenEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.BatteryChargerMapper;
import datnnhom12api.mapper.ScreenMapper;
import datnnhom12api.paginationrequest.ScreenPaginationRequest;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.request.ScreenRequest;
import datnnhom12api.response.BatteryChargerResponse;
import datnnhom12api.response.ScreenResponse;
import datnnhom12api.service.ScreenService;
import datnnhom12api.utils.support.BatteryChargerStatus;
import datnnhom12api.utils.support.ScreenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @GetMapping("/screens/getAll")
    public ScreenResponse getAll() {
        return new ScreenResponse(ScreenMapper.getInstance().toListDTO(screenService.findAll()));
    }


    @GetMapping("/auth/screens")
    public ScreenResponse index(@Valid ScreenPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ScreenEntity> page = screenService.paginate(request.getPage(), request.getLimit(), request.getFilters(),
                request.getOrders());
        return new ScreenResponse(ScreenMapper.toPageDTO(page));
    }

    @PostMapping("/staff/screens")
    public ScreenResponse create(@Valid @RequestBody ScreenRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ScreenEntity postEntity = screenService.create(post);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/staff/screens/{id}")
    public ScreenResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ScreenRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ScreenEntity postEntity = screenService.update(id, post);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/admin/screens/{id}")
    public ScreenResponse delete(@PathVariable("id") Long id) throws CustomException {
        screenService.delete(id);
        return new ScreenResponse();
    }
    @PutMapping("/admin/screens/close/{id}")
    public ScreenResponse close(@PathVariable("id") Long id) throws CustomException {
        ScreenEntity postEntity = screenService.close(id);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/admin/screens/open/{id}")
    public ScreenResponse open(@PathVariable("id") Long id) throws CustomException {
        ScreenEntity postEntity = screenService.open(id);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @PostMapping("/staff/screens/draft")
    public ScreenResponse draft(@RequestBody ScreenRequest post, BindingResult bindingResult)throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        post.setStatus("DRAFT");
        ScreenEntity postEntity = screenService.create(post);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }
}
