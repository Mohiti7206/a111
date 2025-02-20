package com.idsargus.akpmsadminservice.Mvc.Repository;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;
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
public interface AdminLocationRepository extends JpaRepository<AdminLocations, Integer>, CrudRepository<AdminLocations,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.LocationResponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "e.desc,  " +
                    "e.enabled,  " +
                    "e.deleted,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminLocations e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    Page<LocationResponseDto> findAll1(
            Pageable pageable
    );


    @Query("SELECT e FROM AdminLocations e WHERE e.id = :id")
    Optional<AdminLocations> findById(@Param("id") Integer id);



    AdminLocations findByName(String name);

}

//public class AdminLocationRepository {
//}
