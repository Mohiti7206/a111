package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminRevenueTypeMvcResponseDto;
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

//public interface AdminRevenueTypeMvcRepository {
//}

@Repository
public interface AdminRevenueTypeMvcRepository extends JpaRepository<AdminRevenueTypeMvc, Integer>, CrudRepository<AdminRevenueTypeMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminRevenueTypeMvcResponseDto(" +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.code ," +
                    "e.payments ," +
                    "e.operations ," +
                    "e.accounting ," +
                    "e.description ,  " +
                    "e.enabled ,  " +
                    "e.deleted ,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminRevenueTypeMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    Page<AdminRevenueTypeMvcResponseDto> findAll1(Pageable pageable);



    @Query("SELECT e FROM AdminRevenueTypeMvc e WHERE e.id = :id")
    Optional<AdminRevenueTypeMvc> findById(@Param("id") Integer id);



    AdminRevenueTypeMvc findByName(String name);

}
