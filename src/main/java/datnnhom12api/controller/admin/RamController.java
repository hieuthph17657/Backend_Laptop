package datnnhom12api.controller.admin;


import datnnhom12api.dto.RamDTO;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ProcessorMapper;
import datnnhom12api.mapper.RamMapper;
import datnnhom12api.paginationrequest.CartPaginationRequest;
import datnnhom12api.paginationrequest.RamPaginationRequest;
import datnnhom12api.request.ProcessorRequest;
import datnnhom12api.request.RamRequest;
import datnnhom12api.response.ProcessorResponse;
import datnnhom12api.response.RamResponse;
import datnnhom12api.service.RamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RamController {

    @Autowired
    private RamService ramService;

    @GetMapping("/rams/getAll")
    public RamResponse getAll() {
        return new RamResponse(RamMapper.getInstance().toListDTO(ramService.findAll()));
    }

    @GetMapping("auth/rams")
    public RamResponse index(@Valid RamPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<RamEntity> page = ramService.paginate(request.getPage(), request.getLimit(), request.getFilters(),
                request.getOrders());
        return new RamResponse(RamMapper.toPageDTO(page));
    }


    @PostMapping("staff/rams")
    public RamResponse create(@Valid @RequestBody RamRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        RamEntity postEntity = ramService.create(post);
        return new RamResponse(RamMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("staff/rams/{id}")
    public RamResponse edit(@PathVariable("id") Long id, @Valid @RequestBody RamRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        RamEntity postEntity = ramService.update(id, post);
        return new RamResponse(RamMapper.getInstance().toDTO(postEntity));
    }
    @DeleteMapping("/staff/rams/{id}")
    public RamResponse delete(@PathVariable("id") Long id) throws CustomException {
        ramService.delete(id);
        return new RamResponse();
    }


    @PutMapping(("/staff/rams/{id}/active"))
    public RamDTO active (@PathVariable("id") Long id) throws CustomException {
        RamDTO ramDTO = this.ramService.active(id);
        return ramDTO;
    }

    @PutMapping(("/staff/rams/{id}/inactive"))
    public RamDTO inactive (@PathVariable("id") Long id) throws CustomException {
        RamDTO ramEntity = this.ramService.inactive(id);
        return ramEntity;
    }
}
