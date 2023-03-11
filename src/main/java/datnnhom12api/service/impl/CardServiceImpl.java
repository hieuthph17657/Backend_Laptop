package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.entity.CardEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.specifications.CardSpecifications;
import datnnhom12api.repository.CardRepository;
import datnnhom12api.request.CardRequest;
import datnnhom12api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardEntity create(CardRequest request) throws CustomException {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setData(request);
        this.cardRepository.save(cardEntity);
        return cardEntity;
    }

    @Override
    public Page<CardEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<CardEntity> specifications = CardSpecifications.getInstance().getQueryResult(whereParams);

        return cardRepository.findAll(specifications, pageable);
    }

    @Override
    public CardEntity update(Long id, CardRequest post) throws CustomException {
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (cardEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        CardEntity cardEntity = cardEntityOptional.get();
        cardEntity.setData(post);
        cardEntity = cardRepository.save(cardEntity);
        return cardEntity;
    }

    @Override
    public CardEntity delete(Long id) throws CustomException {
        Optional<CardEntity> cardEntityOptional = cardRepository.findById(id);
        if (cardEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        CardEntity cardEntity = cardRepository.getById(id);
        cardRepository.delete(cardEntity);
        return cardEntity;
    }
    @Override
    public List<CardEntity> findAll(){
        return cardRepository.findAll();
    }
}
