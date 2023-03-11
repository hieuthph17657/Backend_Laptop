package datnnhom12api.controller.admin;

import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.UserMapper;
import datnnhom12api.paginationrequest.UserPaginationRequest;
import datnnhom12api.request.UserRequest;
import datnnhom12api.response.UserResponse;
import datnnhom12api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/loadUsers")
    public UserResponse loadUsers(@Valid UserPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<UserEntity> page = userService.loadUsers(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new UserResponse(UserMapper.toPageDTO(page));
    }

    @GetMapping
    public UserResponse index(@Valid UserPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<UserEntity> page = userService.paginate(request.getSearchUsername(), request.getSearchStatus(),
                request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new UserResponse(UserMapper.toPageDTO(page));
    }

    @PostMapping()
    public UserResponse create(@Valid @RequestBody UserRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity postEntity = userService.save(post);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }


    @PostMapping("/clients")
    public UserResponse createClient(@Valid @RequestBody UserRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity postEntity = userService.save(post);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/{id}")
    public UserResponse edit(@PathVariable("id") Long id, @Valid @RequestBody UserRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity postEntity = userService.edit(id, post);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable("id") Long id) throws CustomException {
        userService.delete(id);
        return new UserResponse();
    }

    @PutMapping("/open/{id}")
    public UserResponse open(@PathVariable("id") Long id) throws CustomException {
        UserEntity postEntity = userService.open(id);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/close/{id}")
    public UserResponse close(@PathVariable("id") Long id) throws CustomException {
        UserEntity postEntity = userService.close(id);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }

    @GetMapping("find/{id}")
    public UserResponse find(@PathVariable("id") Long id) throws CustomException {
        UserEntity postEntity = userService.find(id);
        return new UserResponse(UserMapper.getInstance().toDTO(postEntity));
    }
}
