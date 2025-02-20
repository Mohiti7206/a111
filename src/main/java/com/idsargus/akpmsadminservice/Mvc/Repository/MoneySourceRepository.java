package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminChargeBatchType;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminMoneySource;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ChargeBatchTyperesponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//
//public interface MoneySourceRepository {
////}
@Repository
public interface MoneySourceRepository extends JpaRepository<AdminMoneySource, Integer>, CrudRepository<AdminMoneySource,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceResponseDto(" +
                    "e.id , " +
                    "e.createdOn , " +
                    "e.modifiedOn , " +
                    "e.name ," +
                    "e.enabled ,  " +
                    "e.deleted ,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName) , " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminMoneySource e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
    Page<MoneySourceResponseDto> findAll1(
            Pageable pageable
    );


    AdminMoneySource findByName(String name);

}