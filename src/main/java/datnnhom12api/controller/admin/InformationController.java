package datnnhom12api.controller.admin;

import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.InformationEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.DiscountMapper;
import datnnhom12api.mapper.InformationMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.request.InformationRequest;
import datnnhom12api.response.DiscountResponse;
import datnnhom12api.response.InformationResponse;
import datnnhom12api.service.CategoryService;
import datnnhom12api.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class InformationController {
    @Autowired
    InformationService infoService;

    @GetMapping("/api/information")
    public InformationResponse index(@Valid CategoryPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<InformationEntity> page = infoService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        System.out.println(page);
        return new InformationResponse(InformationMapper.toPageDTO(page));
    }

    @PostMapping("/api/staff/information")
    public InformationResponse create(@Valid @RequestBody InformationRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        InformationEntity postEntity = infoService.save(post);
        return new InformationResponse(InformationMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/api/auth/information/{id}")
    public InformationResponse edit(@PathVariable("id") Long id, @Valid @RequestBody InformationRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        InformationEntity postEntity = infoService.edit(id, post);
        return new InformationResponse(InformationMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/api/admin/information/{id}")
    public InformationResponse delete(@PathVariable("id") Long id) throws CustomException {
        infoService.delete(id);
        return new InformationResponse();
    }

    @GetMapping("/api/information/{id}")
    public InformationResponse getId(@PathVariable("id") Long id) throws CustomException{
        InformationEntity entity =infoService.getById(id);
        return new InformationResponse(InformationMapper.getInstance().toDTO(entity));
    }
}
