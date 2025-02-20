package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.Patient;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.PatientResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//public class AdminPatientRepository {
//}
@Repository
public interface AdminPatientRepository extends JpaRepository<Patient, Integer>, CrudRepository<Patient,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.PatientResponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM Patient e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
//    List<ArTeamsResponseDto> findAll1(
    Page<PatientResponseDto> findAll1(
            Pageable pageable
    );

}