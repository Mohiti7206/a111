package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCompanyResponseDto;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminDoctorCompanyRepository extends JpaRepository<AdminDoctorCompanyEntityMvc, Integer>, CrudRepository<AdminDoctorCompanyEntityMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCompanyResponseDto( " +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.enabled , " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminDoctorCompanyEntityMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    "where (:query is null or  CONCAT(e.name)   LIKE %:query%) "+
                    "AND (:enabled is null or e.enabled = :enabled) "
    )
    Page<AdminCompanyResponseDto> findAll1(
                                        @Param("query") String query,
                                        Pageable pageable,
			                            @Param("enabled") Boolean enabled
                                       );


    @Query("SELECT e FROM AdminDoctorCompanyEntityMvc e WHERE e.id = :id")
    Optional<AdminDoctorCompanyEntityMvc> findById(@Param("id") Integer id);









////    /	http://localhost:5003/api/doctors?query=zhina%20sadeghi%20md&deleted=0
////	http://localhost:5003/api/doctors?query=yung%20lyou%20md&deleted=0
//	http://localhost:5003/api/company?deleted=0&enabled=0&query=
// ---------------------------------Excel Download Code Start------------------------------------------------------------------

    @Query("SELECT i FROM AdminDoctorCompanyEntityMvc i " +
            " where  CONCAT(i.name) LIKE %:query%" +
            " AND (:enabled is null or i.enabled = :enabled) " +
            "ORDER BY i.id")
    @Cacheable
    List<AdminDoctorCompanyEntityMvc> fetchExcelDownload(
            @Param("query") String query,
            @Param("enabled") Boolean enabled
    );

// ---------------------------------Excel Download Code End--------------------------------------------------------------------









    AdminDoctorCompanyEntityMvc findByName(String name);







    @Query("SELECT i FROM AdminDoctorCompanyEntityMvc i WHERE   i.enabled = :enabled")
    @Cacheable
    public List<AdminCompanyResponseDto> getallCompanies(@Param("enabled") Boolean enabled);





    @Query(value="SELECT count(i) FROM AdminDoctorCompanyEntityMvc i")
    public long countAllDoctorCompany();






}