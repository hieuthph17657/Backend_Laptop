package datnnhom12api.repository;

import datnnhom12api.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {
}
