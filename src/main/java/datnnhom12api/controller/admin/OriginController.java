package datnnhom12api.controller.admin;

import datnnhom12api.dto.OriginDTO;
import datnnhom12api.entity.OriginEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.OriginMapper;
import datnnhom12api.paginationrequest.OriginPaginationRequest;
import datnnhom12api.request.OriginRequest;
import datnnhom12api.response.OriginResponse;
import datnnhom12api.service.OriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class OriginController {
    @Autowired
    OriginService originService;

    @GetMapping("/api/staff/origin")
    public OriginResponse index(@Valid OriginPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<OriginEntity> page = originService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new OriginResponse(OriginMapper.toPageDTO(page));
    }

    @PostMapping("/api/staff/origin")
    public OriginResponse create(@Valid @RequestBody OriginRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OriginEntity postEntity = originService.save(post);
        return new OriginResponse(OriginMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/api/staff/origin/{id}")
    public OriginResponse edit(@PathVariable("id") Long id, @Valid @RequestBody OriginRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OriginEntity postEntity = originService.edit(id, post);
        return new OriginResponse(OriginMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/api/staff/origin/{id}")
    public OriginResponse delete(@PathVariable("id") Long id) throws CustomException {
        originService.delete(id);
        return new OriginResponse();
    }

    @PutMapping(("/api/staff/origin/{id}/active"))
    public OriginDTO active (@PathVariable("id") Long id) throws CustomException {
        OriginDTO originDTO = this.originService.active(id);
        return originDTO;
    }

    @PutMapping(("/api/staff/origin/{id}/inactive"))
    public OriginDTO inactive (@PathVariable("id") Long id) throws CustomException {
        OriginDTO originDTO = this.originService.inactive(id);
        return originDTO;
    }
}
