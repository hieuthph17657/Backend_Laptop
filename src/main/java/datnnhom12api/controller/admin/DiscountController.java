package datnnhom12api.controller.admin;

import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.DiscountMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.paginationrequest.DiscountPaginationRequest;
import datnnhom12api.request.DiscountRequest;
import datnnhom12api.response.DiscountResponse;
import datnnhom12api.service.DiscountService;
import datnnhom12api.service.InformationService;
import datnnhom12api.utils.support.DiscountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping("/api/auth/discount")
        public DiscountResponse index(@Valid DiscountPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<DiscountEntity> page = discountService.paginate(request.getSearchStartDate(),request.getSearchEndDate(), request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        System.out.println(page);
        return new DiscountResponse(DiscountMapper.toPageDTO(page));
    }

    @GetMapping("/api/staff/discount/{id}")
    public DiscountResponse getId(@PathVariable("id") Long id) throws CustomException{
        DiscountEntity entity =discountService.getByIdDiscount(id);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(entity));
    }

    @PostMapping("/api/staff/discount")
    public DiscountResponse create(@Valid @RequestBody DiscountRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        DiscountEntity postEntity = discountService.save(post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/api/staff/discount/{id}")
    public DiscountResponse edit(@PathVariable("id") Long id, @Valid @RequestBody DiscountRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        post.setStatus(DiscountStatus.ACTIVE);
        DiscountEntity postEntity = discountService.edit(id, post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }
    @PutMapping("/api/admin/discount/active/{id}")
    public DiscountResponse active(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        DiscountEntity postEntity = discountService.active(id);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }
    @PutMapping("/api/admin/discount/inactive/{id}")
    public DiscountResponse inActive(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        DiscountEntity postEntity = discountService.inActive(id);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }
    @PostMapping("/api/staff/discount/draft")
    public DiscountResponse draft(@RequestBody DiscountRequest post) throws CustomValidationException, CustomException {
        post.setStatus(DiscountStatus.DRAFT);
        DiscountEntity postEntity = discountService.save(post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/api/admin/discount/{id}")
    public DiscountResponse delete(@PathVariable("id") Long id) throws CustomException {
        DiscountEntity entity =discountService.getByIdDiscount(id);
        if (entity.getStatus()==DiscountStatus.DRAFT) {
            discountService.delete(id);
            return new DiscountResponse();
        }else{
            throw new CustomException();
        }
    }
}
