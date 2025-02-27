package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DoctorGroupResponse;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorGroup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminDoctorGroupRepository extends JpaRepository<AdminDoctorGroupMvc, Integer>, CrudRepository<AdminDoctorGroupMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DoctorGroupResponse(" +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.enabled ,  " +
                    "e.company.name ,  " +
                     "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminDoctorGroupMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    " LEFT JOIN e.company  company "+
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    "where (:query is null or  CONCAT(e.name)   LIKE %:query%) " +
                    "AND (:enabled is null or e.enabled = :enabled)"
    )
    Page<DoctorGroupResponse> findAll1(@Param("query") String query,
                                       @Param("enabled") Boolean enabled,
                                       Pageable pageable
    );




//	http://localhost:5003/api/group?deleted=0&query=yoon,%20hojung%20j.,%20md
//	http://localhost:5003/api/group?deleted=0&query=zweig,%20jennifer%20do
//	http://localhost:5003/api/group?deleted=0&enabled=0&query=
// ---------------------------------Excel Download Code Start------------------------------------------------------------------

    @Query("SELECT i FROM AdminDoctorGroupMvc i where  CONCAT(i.name) LIKE %:query% AND" +
            " (:enabled is null or i.enabled = :enabled) ")
    @Cacheable
    public List<AdminDoctorGroupMvc> fetchExcelDownload(@Param("query") String query,
                                                     @Param("enabled") Boolean enabled
    );
// ---------------------------------Excel Download Code End--------------------------------------------------------------------







    AdminDoctorGroupMvc findByName(String name);

    @Query(value = "select concat(u.first_name, ' ' ,last_name) from user as u where u.email=:",nativeQuery = true)
    String getUserNameByEmailId(@Param("email") String email);




    @Query("SELECT i FROM AdminDoctorGroupMvc i" +
            " WHERE" +
             " (:companyid is null or i.company.id = :companyid) "+
             "AND i.enabled = :enabled")
    @Cacheable
    public List<DoctorGroupResponse> getCompanyGroup(@Param("companyid") Integer companyid,
                                                     @Param("enabled") Boolean enabled);




    @Query(value="SELECT count(i) FROM AdminDoctorGroupMvc i")
    public long countAllDoctorGroup();





}