package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPermissionEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminPermissionsResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AdmnPermissionRepository extends JpaRepository<AdminPermissionEntityMvc, Integer>, CrudRepository<AdminPermissionEntityMvc,Integer> {



    @Query(value = "SELECT p.permission_id FROM akpms_prod.user_permission as p WHERE p.user_id = :userId", nativeQuery = true)
    List<String> findByUserId(@Param("userId") Integer userId); // Change return type to List<String>



    @Query(value = "SELECT * FROM akpms_prod.permission as p WHERE p.id IN (:pid)", nativeQuery = true)
    List<AdminPermissionEntityMvc> findAllById(@Param("pid") Set<String> permissionIds);




    @Query("SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminPermissionsResponseDto(i.id, i.name, i.description) " +
            "FROM AdminPermissionEntityMvc i ORDER BY i.id")
    @Cacheable
    List<AdminPermissionsResponseDto> findAll1();


    
}