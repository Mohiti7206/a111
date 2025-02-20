package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminStatusCodeEntity;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArstatusCodeResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public class ArStatusCodesRepository {
//}
@Repository
public interface ArStatusCodesRepository extends JpaRepository<AdminStatusCodeEntity, Integer>, CrudRepository<AdminStatusCodeEntity,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArstatusCodeResponse(" +
                    "e.id, " +
                    "e.name " +
                    ") " +
                    " FROM AdminStatusCodeEntity e "
    )
    List<ArstatusCodeResponse> findAll1(
    );
}
