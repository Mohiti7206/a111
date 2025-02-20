package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminSourceEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminStatusCodeEntity;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArSourceResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArstatusCodeResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArSourceRepository extends JpaRepository<AdminSourceEntity, Integer>, CrudRepository<AdminSourceEntity,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArSourceResponseDto(" +
                    "e.id, " +
                    "e.name " +
                    ") " +
                    " FROM AdminSourceEntity e "
    )
    List<ArSourceResponseDto> findAll1();
}
