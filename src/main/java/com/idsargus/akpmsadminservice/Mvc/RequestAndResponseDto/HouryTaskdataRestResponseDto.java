package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

//public class HouryTaskdataRestResponseDto {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminCodingProdTypeEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskNameMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;

import java.time.LocalDateTime;

public class HouryTaskdataRestResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String details;
    private String time;
    private String hours;
    private String minutes;
    private LocalDateTime dateReceived;
    private LocalDateTime taskCompleted;
//    private String userTimeZone = "";
    private String taskName ;
    private Integer nameId;
    private String createdByUserName;
    private String modifiedByUserName;


    public HouryTaskdataRestResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn
                                       , String details, String time, String hours, String minutes
                                        ,LocalDateTime dateReceived, LocalDateTime taskCompleted,
//                                        String userTimeZone,
                                        String taskName
                                        ,Integer nameId,
                                        String createdByUserName, String modifiedByUserName
    ) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.details = details;
        this.time = time;
        this.hours = hours;
        this.minutes = minutes;
        this.dateReceived = dateReceived;
        this.taskCompleted = taskCompleted;
//        this.userTimeZone = userTimeZone;
        this.taskName = taskName;
        this.nameId = nameId;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }


    public HouryTaskdataRestResponseDto(AdminHourlyTaskEntity entity) {
        this.id = entity.getId();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName()+""+entity.getCreatedBy().getLastName() : null;
        this.createdOn = entity.getCreatedOn();
        this.modifiedOn = entity.getModifiedOn();
    }

    public HouryTaskdataRestResponseDto() {

    }


    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public LocalDateTime getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(LocalDateTime taskCompleted) {
        this.taskCompleted = taskCompleted;
    }



    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public String getModifiedByUserName() {
        return modifiedByUserName;
    }

    public void setModifiedByUserName(String modifiedByUserName) {
        this.modifiedByUserName = modifiedByUserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

}
