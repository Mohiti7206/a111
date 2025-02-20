package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminInsuranceEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.InsuranceResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InsuranceRepository extends JpaRepository<AdminInsuranceEntityMvc, Integer>, CrudRepository<AdminInsuranceEntityMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.InsuranceResponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "e.description," +
                    "e.enabled,  " +
                    "e.deleted,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminInsuranceEntityMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    List<InsuranceResponseDto> findAll1(
//    Page<InsuranceResponseDto> findAll1(
            Pageable pageable
    );





    @Query(value = "SELECT count(i) FROM AdminInsuranceEntityMvc i where i.deleted = 0 ")
    public long countByIsDeleted();



    //    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @RestResource(path = "allEnabled", rel = "allEnabled")
    @Query("SELECT i FROM AdminInsuranceEntityMvc i where i.deleted = 0 AND i.enabled= 1 ORDER BY i.name")
    @Cacheable(key = "#enabled")
    public List<InsuranceResponseDto> findByAllEnabled();


//
//    @Query("SELECT e FROM AdminArTeams e WHERE e.id = :id")
//    Optional<AdminInsuranceEntityMvc> findById(@Param("id") Integer id);

    AdminInsuranceEntityMvc findByName(String name);
}
