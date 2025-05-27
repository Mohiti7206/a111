package com.idsargus.akpmsadminservice.Mvc.Repository;

//public interface HourlyTaskDataRestRepository {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskEntity;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HouryTaskdataRestResponseDto;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface HourlyTaskDataRestRepository extends JpaRepository<AdminHourlyTaskEntity, Integer>, CrudRepository<AdminHourlyTaskEntity,Integer> {


   @Query(
    "SELECT new com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HouryTaskdataRestResponseDto(" +
            " e.id," +
            " e.createdOn," +
            " e.modifiedOn," +
            " e.details," +
            " e.time," +
            " e.hours," +
            " e.minutes, " +
            " e.dateReceived," +
            " e.taskCompleted," +
            " e.hourlyTask.name," +
            " e.hourlyTask.id, " +
            "CONCAT(createdBy.firstName, ' ', createdBy.lastName), " +
            "CONCAT(modifiedBy.firstName, ' ', modifiedBy.lastName)) " +

    "FROM AdminHourlyTaskEntity e " +
    "LEFT JOIN e.createdBy createdBy " +
    "LEFT JOIN e.modifiedBy modifiedBy " +
    "LEFT JOIN e.hourlyTask hourlyTask " +
    "WHERE (:name IS NULL OR e.hourlyTask.id = :name) " +
    "AND (:createdFrom IS NULL OR DATE(e.createdOn) >= :createdFrom) " +
    "AND (:createdTo IS NULL OR DATE(e.createdOn) <= :createdTo) " +
    "AND (:taskCompletedFrom IS NULL OR DATE(e.taskCompleted) >= :taskCompletedFrom) " +
    "AND (:taskCompletedTo IS NULL OR DATE(e.taskCompleted) <= :taskCompletedTo) " +
    "AND (:dateReceivedFrom IS NULL OR DATE(e.dateReceived) >= :dateReceivedFrom) " +
    "AND (:dateReceivedTo IS NULL OR DATE(e.dateReceived) <= :dateReceivedTo) " +
    "AND (:createdBy IS NULL OR e.createdBy.id = :createdBy)"
)
Page<HouryTaskdataRestResponseDto> findAll1(
        @Param("name") Integer name,
        @Param("createdFrom") Date createdFrom,
        @Param("createdTo") Date createdTo,
        @Param("taskCompletedFrom") Date taskCompletedFrom,
        @Param("taskCompletedTo") Date taskCompletedTo,
        @Param("dateReceivedFrom") Date dateReceivedFrom,
        @Param("dateReceivedTo") Date dateReceivedTo,
        @Param("createdBy") Integer createdBy,
        Pageable pageable
);






   @Query(value="SELECT i FROM AdminHourlyTaskEntity i WHERE" +
           " (:name is null or i.hourlyTask.id= :name) and" +
           " (:createdFrom is null or DATE(i.createdOn) >= :createdFrom) AND " +
           "(:createdTo is null or DATE(i.createdOn) <= :createdTo) AND " +
           "(:taskCompletedFrom is null or DATE(i.taskCompleted) >= :taskCompletedFrom) AND " +
           "(:taskCompletedTo is null or DATE(i.taskCompleted) <= :taskCompletedTo) AND " +
           "(:dateReceivedFrom is null or DATE(i.dateReceived) >= :dateReceivedFrom) AND " +
           "(:dateReceivedTo is null or DATE(i.dateReceived) <= :dateReceivedTo) AND " +
           "(:createdBy is null or i.createdBy.id = :createdBy)")
   List<AdminHourlyTaskEntity> fetchExcelDownload(
           @Param("name") Integer name,
           @Param("createdBy") Integer createdBy,
           @Param("createdFrom") Date createdFrom,
           @Param("createdTo") Date createdTo,
           @Param("taskCompletedFrom") Date taskCompletedFrom,
           @Param("taskCompletedTo") Date taskCompletedTo,
           @Param("dateReceivedFrom") Date dateReceivedFrom,
           @Param("dateReceivedTo") Date dateReceivedTo
   );


   Optional<AdminHourlyTaskEntity> findByHourlyTask_Name(String name);

}
