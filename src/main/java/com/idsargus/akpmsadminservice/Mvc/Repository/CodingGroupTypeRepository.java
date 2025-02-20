package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminCodingProdTypeEntity;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCodingGroupTypeResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
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


@Repository
    public interface CodingGroupTypeRepository extends JpaRepository<AdminCodingProdTypeEntity, Integer>, CrudRepository<AdminCodingProdTypeEntity,Integer> {


        @Cacheable
        @Query(
                "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCodingGroupTypeResponseDto(" +
                        "e.id , " +
                        "e.createdOn , " +
                        "e.modifiedOn , " +
                        "e.name ," +
                        "e.target ," +
                        "e.enabled ,  " +
                         "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                        "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                        ") " +
                        " FROM AdminCodingProdTypeEntity e " +
                        " LEFT JOIN e.createdBy createdBy " +
                        " LEFT JOIN e.modifiedBy  modifiedBy "
          )
        Page<AdminCodingGroupTypeResponseDto> findAll1(
                                                  Pageable pageable
        );

    @Query("SELECT e FROM AdminCodingProdTypeEntity e WHERE e.id = :id")
    Optional<AdminCodingProdTypeEntity> findById(@Param("id") Integer id);


//    @Query("SELECT e FROM AdminArTeams e WHERE e.name = :name")
//    Integer findByName(@Param("name") String name);


    AdminCodingProdTypeEntity findByName(String name);

    @Query("SELECT i FROM AdminCodingProdTypeEntity i where  i.enabled= 1 ORDER BY i.name")
    @Cacheable(key = "#enabled")
    List<AdminCodingGroupTypeResponseDto> findByAllEnabled();
    }
