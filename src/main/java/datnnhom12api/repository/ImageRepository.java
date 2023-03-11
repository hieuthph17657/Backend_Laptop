package datnnhom12api.repository;

import datnnhom12api.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImagesEntity, Long> {
    @Query("select c from ImagesEntity c where  c.id in :ids")
    List<ImagesEntity> findAllById(@Param("ids") List<String> ids);

    @Query("select c from ImagesEntity c where  c.product.id = :ids")
    List<ImagesEntity> findAllByProductId(@Param("ids") Long ids);

    @Modifying
    @Query("DELETE from ImagesEntity c where  c.product.id = :ids")
    void deleteAllByProductId(@Param("ids") Long ids);
}
