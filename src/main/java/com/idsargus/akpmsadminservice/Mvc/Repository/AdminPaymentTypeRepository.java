package com.idsargus.akpmsadminservice.Mvc.Repository;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminPaymentTypeResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminRevenueTypeMvcResponseDto;
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
public interface AdminPaymentTypeRepository extends JpaRepository<AdminPaymentTypeMvc, Integer>, CrudRepository<AdminPaymentTypeMvc,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminPaymentTypeResponse(" +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.description ,  " +
                    "e.enabled ,  " +
                    "e.deleted ,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminPaymentTypeMvc e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    Page<AdminPaymentTypeResponse> findAll1(Pageable pageable);



    @Query("SELECT e FROM AdminPaymentTypeMvc e WHERE e.id = :id")
    Optional<AdminPaymentTypeMvc> findById(@Param("id") Integer id);



    AdminPaymentTypeMvc findByName(String name);





    @Query(value="SELECT count(i) FROM AdminPaymentTypeMvc i where i.deleted =0")
    public long countByIsDeleted();

}