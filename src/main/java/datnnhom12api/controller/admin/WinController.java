package datnnhom12api.controller.admin;

import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.WinEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.WinMapper;
import datnnhom12api.paginationrequest.WinPaginationRequest;
import datnnhom12api.request.WinRequest;
import datnnhom12api.response.WinResponse;
import datnnhom12api.service.WinService;
import datnnhom12api.utils.support.WinStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class WinController {
    @Autowired
    private WinService winService;

    @GetMapping("/wins/getAll")
    public WinResponse getAll() {
        return new WinResponse(WinMapper.getInstance().toListDTO(winService.findAll()));
    }

    @GetMapping("/auth/wins")
    public WinResponse index(@Valid WinPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<WinEntity> page = winService.paginate(request.getPage(), request.getLimit(), request.getFilters(),
                request.getOrders());
        return new WinResponse(WinMapper.toPageDTO(page));
    }

    @PostMapping("/staff/wins")
    public WinResponse create(@Valid @RequestBody WinRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        WinEntity postEntity = winService.create(post);
        return new WinResponse(WinMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/staff/wins/{id}")
    public WinResponse edit(@PathVariable("id") Long id, @Valid @RequestBody WinRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        WinEntity postEntity = winService.update(id, post);
        return new WinResponse(WinMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/admin/wins/{id}")
    public WinResponse delete(@PathVariable("id") Long id) throws CustomException {
        winService.delete(id);
        return new WinResponse();
    }
    @PutMapping("/admin/wins/close/{id}")
    public WinResponse close(@PathVariable("id") Long id) throws CustomException {
        WinEntity postEntity = winService.close(id);
        return new WinResponse(WinMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/admin/wins/open/{id}")
    public WinResponse open(@PathVariable("id") Long id) throws CustomException {
        WinEntity postEntity = winService.open(id);
        return new WinResponse(WinMapper.getInstance().toDTO(postEntity));
    }

    @PostMapping("/staff/wins/draft")
    public WinResponse draft(@RequestBody WinRequest post, BindingResult bindingResult)throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        post.setStatus(WinStatus.DRAFT);
        WinEntity postEntity = winService.create(post);
        return new WinResponse(WinMapper.getInstance().toDTO(postEntity));
    }
}
