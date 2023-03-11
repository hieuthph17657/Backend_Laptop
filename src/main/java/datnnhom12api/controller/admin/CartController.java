package datnnhom12api.controller.admin;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CartMapper;
import datnnhom12api.paginationrequest.CartPaginationRequest;
import datnnhom12api.request.CartRequest;
import datnnhom12api.response.CartResponse;
import datnnhom12api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public CartResponse index(@Valid CartPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<CartEntity> page = cartService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new CartResponse(CartMapper.toPageDTO(page));
    }

    @PostMapping()
    public CartResponse create(@Valid @RequestBody CartRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CartEntity postEntity = cartService.create(post);
        return new CartResponse(CartMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/{id}")
    public CartResponse edit(@PathVariable("id") Long id, @Valid @RequestBody CartRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CartEntity postEntity = cartService.update(id, post);
        return new CartResponse(CartMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public CartResponse delete(@PathVariable("id") Long id) throws CustomException {
        cartService.delete(id);
        return new CartResponse();
    }



}