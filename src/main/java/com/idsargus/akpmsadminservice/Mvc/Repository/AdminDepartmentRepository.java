package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDepartmentResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DeptResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DoctorGroupResponse;
//import com.idsargus.akpmsadminservice.entity.AdminDepartmentEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminDepartmentRepository extends JpaRepository<AdminDepartmentEntityMvc, Integer>, CrudRepository<AdminDepartmentEntityMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDepartmentResponseDto(" +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.target ," +
                    "e.noidaTarget ," +
                    "e.description ," +
                    "e.location ," +
                    "e.noidaLocation ," +
                    "e.enabled ,  " +
                    "e.deleted ,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ", e.parent " +
                    ", e.parent.name " +
                    ") " +
                    " FROM AdminDepartmentEntityMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    "WHERE (:query is null or (e.name LIKE %:query% OR e.parent.name LIKE %:query%)) " +
                    "AND (:enabled is null or e.enabled = :enabled)"
    )
    Page<AdminDepartmentResponseDto> findAll1(@Param("query") String query,
                                              @Param("enabled") Boolean enabled,
                                              Pageable pageable
    );





//
//    //	http://localhost:5003/api/department?deleted=0&query=Self%20Pay%20and%20CEP
////-------------------------------------EXCEL DOWNLOAD CODE START -----------------------------------------------------------------
//    @Query("SELECT d FROM AdminDepartmentEntity d WHERE " +
//            "(d.deleted = 0 OR d.deleted = :deleted) AND " +
//            "(:query IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%')))")
//    public List<AdminDepartmentEntity> findByQueryAll(
//            @Param("query") String query,
//            @Param("deleted") Integer deleted
//    );
////-------------------------------------EXCEL DOWNLOAD CODE END -----------------------------------------------------------------
//



    AdminDepartmentEntityMvc findByName(String name);

    @Query(value = "select concat(u.first_name, ' ' ,last_name) from user as u where u.email=:",nativeQuery = true)
    String getUserNameByEmailId(@Param("email") String email);


    @Query(value = "SELECT u.department_id FROM akpms_prod.user_department as u where u.user_id = :userId", nativeQuery = true)
    List<Integer> findByUserId(@Param("userId") Integer userId); // Change return type to List<String>



    @Query(value = "SELECT u.email_template_id FROM  user_email_template as u where u.user_id = :userId", nativeQuery = true)
    List<Integer> findByUserIdEt(@Param("userId") Integer userId); //









    @Query(value="SELECT count(i) FROM AdminDepartmentEntityMvc i where i.deleted = 0")
    public long countByIsDeleted();






//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "parentonly", rel = "parentonly")
    @Query("SELECT i FROM AdminDepartmentEntityMvc i WHERE i.deleted = 0 AND parent = null ORDER BY i.name")
    @Cacheable(key = "#parent-only")
    public List<AdminDepartmentResponseDto> findParentForAdmin();



//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "subdeptonly", rel = "subdeptonly")
    @Query("SELECT i FROM AdminDepartmentEntityMvc i WHERE i.deleted = 0 and i.enabled=1 AND parent != null ORDER BY i.name")
    @Cacheable(key = "#all-sub-department-only")
    public List<AdminDepartmentResponseDto> findSubDepartment();








//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "subdeptbydept", rel = "subdeptbydept")
    @Query("SELECT i FROM AdminDepartmentEntityMvc i where i.deleted = 0 and i.parent.id = :deptId and i.enabled=1")
    @Cacheable(key = "#sub-department-only-by-deptid")
    public List<AdminDepartmentResponseDto> findSubDepartmentByDeptId(@Param("deptId") Integer deptId);




//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "name", rel = "name")
    @Query("SELECT i FROM AdminDepartmentEntityMvc i where i.deleted = 0 AND i.name=:name")
    @Cacheable(key = "#name")
    public AdminDepartmentResponseDto findByNameForAdmin(@Param("name") String name);





    @Query("SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DeptResponseDto(" +
            "dept.id, " +
            "dept.createdOn, " +
            "dept.modifiedOn ," +
            "dept.name, " +
            "dept.target, " +
            "dept.noidaTarget, " +
            "dept.description, " +
            "dept.location, " +
            "dept.noidaLocation, " +
            "dept.enabled, " +
            "dept.deleted, " +
            "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
            "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName), " +
            "parent.id, " +
            "parent.name " +
            " ) " +
            " FROM AdminDepartmentEntityMvc dept "+
            " LEFT JOIN dept.parent parent " +
            " LEFT JOIN dept.createdBy createdBy " +
            " LEFT JOIN dept.modifiedBy modifiedBy"
    )
    List<DeptResponseDto> getalldepartments();

}