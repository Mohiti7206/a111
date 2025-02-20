package com.idsargus.akpmsadminservice.Mvc.Repository;

//public interface AdminHourlyTaskRepo {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskNameMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HourlyTaskResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.LocationResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminHourlyTaskRepo extends JpaRepository<AdminHourlyTaskNameMvc, Integer>, CrudRepository<AdminHourlyTaskNameMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HourlyTaskResponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "e.description,  " +
                    "e.chargeable,  " +
                    "e.enabled,  " +
                    "e.deleted,  " +
                    "department.name,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminHourlyTaskNameMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "+
                    " LEFT JOIN e.department  department "
    )
    Page<HourlyTaskResponseDto> findAll1(
            Pageable pageable
    );


    @Query("SELECT e FROM AdminHourlyTaskNameMvc e WHERE e.id = :id")
    Optional<AdminHourlyTaskNameMvc> findById(@Param("id") Integer id);



    AdminHourlyTaskNameMvc findByName(String name);

}
