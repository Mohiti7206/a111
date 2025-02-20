package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.Patient;

import java.time.LocalDateTime;

//public class PatientResponseDto {
//}
public class PatientResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
     private String createdByUserName;
    private String modifiedByUserName;


    public PatientResponseDto(Integer id,
                              LocalDateTime createdOn,
                              LocalDateTime modifiedOn,
                              String name
            ,                 String createdByUserName,
                              String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }



    public PatientResponseDto(Patient entity) {
        this.id = entity.getId();
        this.name = entity.getName();
         this.createdByUserName = entity.getCreatedBy().getFirstName()+" "+entity.getCreatedBy().getLastName();
        this.createdOn = entity.getCreatedOn();
        this.modifiedOn = entity.getModifiedOn();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}