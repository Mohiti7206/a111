package com.idsargus.akpmsadminservice.Mvc.Repository;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmscommonservice.entity.ArTeams;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdminArTeamsRepository extends JpaRepository<AdminArTeams, Integer>, CrudRepository<AdminArTeams,Integer> {


    @Cacheable
    @Query(
            "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto(" +
                    "e.id, " +
                    "e.createdOn , " +
                    "e.modifiedOn, " +
                    "e.name," +
                    "e.enabled,  " +
                    "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
                    "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)  " +
                    ") " +
                    " FROM AdminArTeams e " +
                    " LEFT JOIN e.createdBy createdBy " +
                    " LEFT JOIN e.modifiedBy  modifiedBy "
    )
//    List<ArTeamsResponseDto> findAll1(
    Page<ArTeamsResponseDto> findAll1(
            Pageable pageable
    );


    @Query("SELECT e FROM AdminArTeams e WHERE e.id = :id")
    Optional<AdminArTeams> findById(@Param("id") Integer id);


//    @Query("SELECT e FROM AdminArTeams e WHERE e.name = :name")
//    Integer findByName(@Param("name") String name);


    AdminArTeams findByName(String name);













     @Query("SELECT i FROM AdminArTeams i where  i.enabled= 1 ORDER BY i.name")
    @Cacheable(key = "#enabled")
    List<ArTeamsResponseDto> findByAllEnabled();











}
