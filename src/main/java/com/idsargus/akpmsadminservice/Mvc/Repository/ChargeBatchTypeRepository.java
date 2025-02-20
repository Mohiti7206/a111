package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminChargeBatchType;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ChargeBatchTyperesponseDto;
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
//
//public interface ChargeBatchTypeRepository {
//}

@Repository
public interface ChargeBatchTypeRepository extends JpaRepository<AdminChargeBatchType, Integer>, CrudRepository<AdminChargeBatchType,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ChargeBatchTyperesponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "e.description,  " +
                    "e.enabled,  " +
                    "e.deleted,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminChargeBatchType e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    Page<ChargeBatchTyperesponseDto> findAll1(
            Pageable pageable
    );


    @Query("SELECT e FROM AdminChargeBatchType e WHERE e.id = :id")
    Optional<AdminChargeBatchType> findById(@Param("id") Integer id);



    AdminChargeBatchType findByName(String name);

}
