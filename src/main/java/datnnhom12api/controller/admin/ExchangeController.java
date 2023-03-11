package datnnhom12api.controller.admin;


import datnnhom12api.dto.ExchangeDetailDTO;
import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ExchangeDetailEntity;
import datnnhom12api.entity.ExchangeEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ExchangeMapper;
import datnnhom12api.paginationrequest.ExchangePaginationRequest;
import datnnhom12api.request.ExchangeDetailRequest;
import datnnhom12api.request.ExchangeRequest2;
import datnnhom12api.response.ReturnResponse;
import datnnhom12api.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ExchangeController {

    @Autowired
    private ReturnService returnService;

    @GetMapping("/staff/returns")
    public ReturnResponse index(@Valid ExchangePaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ExchangeEntity> page = returnService.paginate(request.getSearchStatus(), request.getSearchName(), request.getSearchPhone(), request.getPage(), request.getLimit(),
                request.getFilters(), request.getOrders());
        return new ReturnResponse(ExchangeMapper.getInstance().toPageDTO(page));
    }

    @PostMapping("/auth/returns")
    public ReturnResponse create(@Valid @RequestBody ExchangeRequest2 post,
                                 BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ExchangeEntity postEntity = returnService.insert(post);
        return new ReturnResponse(ExchangeMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/returns/{id}")
    public ReturnResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ExchangeRequest2 post,
                               BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ExchangeEntity postEntity = this.returnService.update(id, post);
        return new ReturnResponse(ExchangeMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/returns/{id}")
    public ReturnResponse delete(@PathVariable("id") Long id) throws CustomException {
        returnService.delete(id);
        return new ReturnResponse();
    }


    @GetMapping("/auth/returns/{id}/detail")
    public ReturnResponse getByIdReturnId(@PathVariable("id") Long id) throws CustomException {
        ExchangeEntity exchangeEntity = this.returnService.getById(id);
        return new ReturnResponse(ExchangeMapper.getInstance().toDTO(exchangeEntity));
    }

    @GetMapping("/auth/returns/{id}/detail/exchange")
    public ExchangeDetailDTO getByIdOrdeExchange(@PathVariable("id") Long id) throws CustomException {
        ExchangeDetailDTO exchangeEntity = this.returnService.getByOrderChange(id);
        return exchangeEntity;
    }

    @GetMapping("/returns/{id}")
    public List<ExchangeDetailDTO> findReturnById(@PathVariable("id") Long id) throws CustomException {
        List<ExchangeDetailDTO> returnDetailEntities = returnService.findById(id);
        return returnDetailEntities;
    }

    @PutMapping("/{id}/updateReturnDetails")
    public UpdateReturnDetailDTO updateByReturnDetail(@PathVariable("id") Long id, @RequestBody ExchangeDetailRequest exchangeDetailRequest) {
        UpdateReturnDetailDTO returnDetailDTO = this.returnService.updateByReturnDetail(id, exchangeDetailRequest);
        return returnDetailDTO;
    }


}