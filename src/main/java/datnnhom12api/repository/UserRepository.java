package datnnhom12api.repository;

import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.RoleEntity;
import datnnhom12api.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @Query("SELECT c FROM UserEntity c WHERE c.username = ?1 and c.status = 1")
    UserEntity findByUsername(String username);

    @Query("SELECT c FROM UserEntity c WHERE LOWER(c.username) = ?1 and c.status = 1")
    UserEntity findByUsernameHasValidate(String username);

    @Query("SELECT c FROM UserEntity c WHERE LOWER(c.username) = ?1")
    Optional<UserEntity> validateUserName(String username);

    @Query("select u from UserEntity u join u.roles r where r.role = 'CUSTOMER' and u.username like %?1% and u.status = ?2")
    Page<UserEntity> findByUserNameStatus(String searchUserName, Integer searchStatus, Pageable pageable);

    @Query("select u from UserEntity u join u.roles r WHERE r.role = 'CUSTOMER' and u.username like %?1%")
    Page<UserEntity> findByUserName(String searchUserName, Pageable pageable);

    @Query("select u from UserEntity u join u.roles r WHERE r.role = 'CUSTOMER' and u.status = ?1")
    Page<UserEntity> findByStatus(Integer searchStatus, Pageable pageable);

    @Query("select u from UserEntity u join u.roles r where r.role = 'CUSTOMER'")
    Page<UserEntity> findAllStaff(Pageable pageable);

    @Query("select r from RoleEntity r where r.role = 'CUSTOMER'")
    RoleEntity findRoleCustomer();

    @Query("SELECT new SumProductDTO(count(ul)) FROM UserRoleEntity ul WHERE ul.roleId = 3")
    SumProductDTO countCustomer();

}
