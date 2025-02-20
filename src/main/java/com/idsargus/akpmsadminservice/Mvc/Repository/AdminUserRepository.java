package com.idsargus.akpmsadminservice.Mvc.Repository;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminUserResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdminUserRepository extends JpaRepository<AdminUserMvc, Integer>, CrudRepository<AdminUserMvc,Integer> {

//
//    @Cacheable
//    @Query(
//            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminUserResponseDto(" +
//                    "e.id, " +
//                    "e.createdOn , " +
//                    "e.modifiedOn, " +
//                    "CONCAT(e.firstName, ' ', e.lastName), " +
//                    "e.enabled,  " +
//                    "e.email,  " +
//                    "e.password,  " +
//                    "e.contact,  " +
//                    "e.address,  " +
//                    "e.deleted,  " +
//                    "e.target,  " +
//                    "e.location,  " +
//                    "role.id,  " +
//                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
//                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
//                    "(SELECT COALESCE(function('array_agg', p.id), array[]::text[]) " +
//                    "FROM AdminUserMvc u " +
//                    "LEFT JOIN u.permissions p " +
//                    "WHERE u.id = e.id) " +
//                    ") "+
////                    ") " +
//                    " FROM AdminUserMvc e " +
//                    " LEFT JOIN e.createdBy createdBy " +
//                    " LEFT JOIN e.modifiedBy  modifiedBy "+
//                    " LEFT JOIN e.role  role "
//    )
////    List<ArTeamsResponseDto> findAll1(
//    Page<AdminUserResponseDto> findAll1(
//            Pageable pageable
//    );




//
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminUserResponseDto(" +
                    "e.id, " +
                    "e.createdOn, " +
                    "e.modifiedOn, " +
//                    "CONCAT(e.firstName, ' ', e.lastName), " +
                    " e.firstName,    " +
                    " e.lastName,  "+
                    "e.enabled, " +
                    "e.email, " +
                    "e.password, " +
                    "e.contact, " +
                    "e.address, " +
                    "e.deleted, " +
                    "e.target, " +
                    "e.location, " +
                    "role.id, " +
                    "arTeam.name, " +
                    "CONCAT(createdBy.firstName, '', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, '', modifiedBy.lastName) " +
                    ") " +
                    "FROM AdminUserMvc e " +
                    "LEFT JOIN e.createdBy createdBy " +
                    "LEFT JOIN e.modifiedBy modifiedBy " +
                    "LEFT JOIN e.role role "+
                    "LEFT JOIN e.arTeam arTeam "+
                    "  WHERE (:query IS NULL OR "+
                    "  LOWER(e.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR "+
                    "  LOWER(e.lastName) LIKE LOWER(CONCAT('%', :query, '%')) OR "+
                    "  LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :query, '%')) "+
                    "  ) "+
                    " AND (:enabled is null or e.enabled = :enabled) "+
                    " AND (:deleted is null or e.deleted = :deleted) "
    )
            Page<AdminUserResponseDto> findAll1(
            @Param("enabled") Boolean enabled,
            @Param("deleted") Boolean deleted,
            @Param("query") String query,
            Pageable pageable);



    @Query("SELECT e FROM AdminUserMvc e WHERE e.id = :id")
    Optional<AdminUserMvc> findById(@Param("id") Integer id);


    @Query("SELECT e FROM AdminUserMvc e WHERE LOWER(CONCAT(e.firstName, ' ', e.lastName)) = LOWER(:fullName)")
    AdminUserMvc findByFullName(@Param("fullName") String fullName);


//    @Query("Delete from ")
//    void deleteUserDepartments(Integer userId);

//    AdminUserMvc findByFirstName(String name);











//	http://localhost:5003/api/user?query=arati@idsargus.com&deleted=0
    //---------------------------------------------EXCEL DOWNLOAD CODE START ----------------------------------------------------------------------------------------------

//    @Query("SELECT i FROM User i where CONCAT(i.firstName, '', i.lastName, '', i.email) LIKE %:query% " +
    @Query("SELECT i FROM AdminUserMvc i where CONCAT(i.firstName, '', i.lastName, '', i.email) LIKE %:query% " +
            "AND (:enabled is null or i.enabled = :enabled) " +
            "AND (:role is null or i.role.id = :role) " +
            "AND (COALESCE(:departmentIds) is null or EXISTS (select 1 from i.departments d where d.id IN (:departmentIds))) " +
            "AND (:deleted is null or i.deleted = :deleted) ")
    @Cacheable
//    List<User> fetchExcelDownload(
    List<AdminUserMvc> fetchExcelDownload(
            @Param("query") String query,
            @Param("enabled") Boolean enabled,
            @Param("role") Integer role,
            @Param("departmentIds") List<Integer> departmentIds,
            @Param("deleted") Boolean deleted
    );

    //---------------------------------------------EXCEL DOWNLOAD CODE END ----------------------------------------------------------------------------------------------



    @Query(value = "SELECT count(i) FROM AdminUserMvc i where i.enabled = :enabled")
    long countByEnable(@Param("enabled") Boolean enabled);



         AdminUserMvc findByEmail(String email);










    //    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @RestResource(path = "allEnabled", rel = "allEnabled")
    @Query("SELECT i FROM AdminUserMvc i where  i.enabled= 1 AND i.deleted = 0 ORDER BY i.firstName, i.lastName")
//  @Query("SELECT i FROM AdminUserMvc i where i.deleted = 0 AND i.enabled= 1 ORDER BY i.name")
    @Cacheable(key = "#enabled")
    public List<AdminUserResponseDto> findByAllEnabled();



















}


