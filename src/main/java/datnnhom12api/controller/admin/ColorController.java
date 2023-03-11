package datnnhom12api.controller.admin;


import datnnhom12api.core.PaginationRequest;
import datnnhom12api.dto.ColorDTO;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.ColorEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CategoryMapper;
import datnnhom12api.mapper.ColorMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.paginationrequest.ColorPaginationRequest;
import datnnhom12api.request.ColorRequest;
import datnnhom12api.response.CategoryResponse;
import datnnhom12api.response.ColorResponse;
import datnnhom12api.service.CategoryService;
import datnnhom12api.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/auth/colors")
    public ColorResponse index(@Valid ColorPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ColorEntity> page = colorService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new ColorResponse(ColorMapper.toPageDTO(page));
    }

    @PostMapping("staff/color")
    public ColorResponse create(@Valid @RequestBody ColorRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()){
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ColorEntity postEntity = colorService.create(post);
        return new ColorResponse(ColorMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("staff/color/{id}")
    public ColorResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ColorRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()){
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ColorEntity postEntity = colorService.update(id, post);
        return new ColorResponse(ColorMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("staff/color/{id}")
    public ColorResponse delete(@PathVariable("id") Long id) throws CustomException{
        colorService.delete(id);
        return new ColorResponse();
    }

}
