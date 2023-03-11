package datnnhom12api.controller.admin;

import datnnhom12api.entity.CardEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CardMapper;
import datnnhom12api.paginationrequest.CardPaginationrequest;
import datnnhom12api.request.CardRequest;
import datnnhom12api.response.CardResponse;
import datnnhom12api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @GetMapping("/getAll")
    public CardResponse getAll() {
        return new CardResponse(CardMapper.getInstance().toListDTO(cardService.findAll()));
    }

    @GetMapping
    public CardResponse index(@Valid CardPaginationrequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<CardEntity> page = cardService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new CardResponse(CardMapper.toPageDTO(page));
    }

     @PostMapping()
     public CardResponse create(@Valid @RequestBody CardRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
         if (bindingResult.hasErrors()) {
             throw new CustomValidationException(bindingResult.getAllErrors());
         }
         CardEntity postEntity = cardService.create(post);
         return new CardResponse(CardMapper.getInstance().toDTO(postEntity));
     }

    @PutMapping("/{id}")
    public CardResponse edit(@PathVariable("id") Long id, @Valid @RequestBody CardRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CardEntity postEntity = cardService.update(id, post);
        return new CardResponse(CardMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public CardResponse delete(@PathVariable("id") Long id) throws CustomException {
        cardService.delete(id);
        return new CardResponse();
    }
}
