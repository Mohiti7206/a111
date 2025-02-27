package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorEntity;
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
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<AdminDoctorEntityMvc, Integer>, CrudRepository<AdminDoctorEntityMvc,Integer> {

//    AdminDoctorEntityMvc findByName(String name);

    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse( " +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.accounting ," +
                    "e.deleted ,  " +
                    "e.nonDeposit ,  " +
                    "e.code ,  " +
                    "e.name ," +
                    "e.operations ," +
                    "e.payments ," +
                    "e.enabled , " +
                    "g.name ,"+
                    "company.name ,"+
                    "parent.name ,"+
                    "company.id , " +
                    "g.id , " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminDoctorEntityMvc e " +
                    " LEFT JOIN e.group  g "+
                    " LEFT JOIN e.parent  parent "+
                    " LEFT JOIN e.company  company "+
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    "where (:query is null or  CONCAT(e.name)   LIKE %:query%) "+
                    "AND (:enabled is null or e.enabled = :enabled) "+
                    "AND (:deleted is null or e.deleted = :deleted)"
    )
    Page<AdminDoctorResponse> findAll1(
                                        @Param("query") String query,
                                        Pageable pageable,
			                            @Param("enabled") Boolean enabled,
                                        @Param("deleted") Boolean deleted
                                       );


    @Query("SELECT e FROM AdminDoctorEntityMvc e WHERE e.id = :id")
    Optional<AdminDoctorEntityMvc> findById(@Param("id") Integer id);









//    /	http://localhost:5003/api/doctors?query=zhina%20sadeghi%20md&deleted=0
//	http://localhost:5003/api/doctors?query=yung%20lyou%20md&deleted=0
    //---------------------------------------------EXCEL DOWNLOAD CODE START ----------------------------------------------------------------------------------------------

    @Query("SELECT i FROM AdminDoctorEntityMvc i where  CONCAT(i.name) LIKE %:query% AND (:enabled is null or i.enabled = :enabled) AND" +
            " (:deleted is null or i.deleted = :deleted)  ")
    @Cacheable
    List<AdminDoctorEntityMvc> fetchExcelDownload(
            @Param("query") String query,
            @Param("enabled") Boolean enabled,
            @Param("deleted") Boolean deleted
    );
    //---------------------------------------------EXCEL DOWNLOAD CODE END ----------------------------------------------------------------------------------------------









    AdminDoctorEntityMvc findByName(String name);





















//    @RestResource(path = "findByDoctorCompanygroup", rel = "findByDoctorCompanygroup")
    @Query(value = "SELECT * FROM doctor i where (:groupId is null or i.group_id = :groupId) "
            + "AND (:companyId is null or i.company_id = :companyId) AND (:name is null or i.name=:name)",
            nativeQuery = true)
    public Integer findByDoctorCompanygroup(
            @Param("name") String name,
            @Param("groupId") Integer groupId,
            @Param("companyId") Integer companyId);




//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "allEnabled", rel = "allEnabled")
    @Query("SELECT i FROM AdminDoctorEntityMvc i where i.deleted = 0 AND i.enabled= 1 ORDER BY i.name")
    @Cacheable(key = "#enabled")
    public List<AdminDoctorResponse> findByAllEnabled();





    @Query(value = "SELECT count(i) FROM AdminDoctorEntityMvc i where i.deleted = 0")
    public long countByIsDeleted();



    @Query("SELECT c FROM AdminDoctorCompanyEntityMvc c WHERE c.id = (SELECT d.company FROM AdminDoctorEntityMvc d WHERE d.id = :doctorId)")
    Optional<AdminDoctorCompanyEntityMvc> findCompanyByDoctorId(@Param("doctorId") Integer doctorId);

    @Query("SELECT g FROM AdminDoctorGroupMvc g WHERE g.id = (SELECT d.group FROM AdminDoctorEntityMvc d WHERE d.id = :doctorId)")
    Optional<AdminDoctorGroupMvc> findGroupByDoctorId(@Param("doctorId") Integer doctorId);




    //    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//     @Query("SELECT i FROM AdminDoctorEntityMvc i where i.deleted = 0 AND i.enabled= 1 ORDER BY i.name")
//    @Query(value = "SELECT AdminDoctorEntityMvc FROM doctor i where (:groupId is null or i.group_id = :groupId) " +
//            "AND (:companyId is null or i.company_id = :companyId) AND ( i.status = 1)", nativeQuery = true)
//    @Cacheable(key = "#enabled")
//    public List<AdminDoctorResponse> findByCompanyAndGroup(@Param("companyId") Integer companyId,
//                                                           @Param("groupId") Integer groupId);



    @Query("SELECT d FROM AdminDoctorEntityMvc d " +
            "WHERE (:groupId IS NULL OR d.group.id = :groupId) " +
            "AND (:companyId IS NULL OR d.company.id = :companyId) " +
            "AND d.status = 1")
    @Cacheable(key = "#enabled")
    List<AdminDoctorResponse> findByCompanyAndGroup(@Param("companyId") Integer companyId,
                                                    @Param("groupId") Integer groupId);







}