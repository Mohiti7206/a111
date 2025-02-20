package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;

import java.time.LocalDateTime;

public class AdminCompanyResponseDto {



    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
     private Boolean enabled;
    private String createdByUserName;
    private String modifiedByUserName;

    public AdminCompanyResponseDto(Integer id, LocalDateTime createdOn,
                                   LocalDateTime modifiedOn, String name, Boolean enabled,
                                   String createdByUserName, String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.enabled = enabled;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }





    public AdminCompanyResponseDto() {

    }


    public AdminCompanyResponseDto(AdminDoctorCompanyEntityMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
         this.enabled = entity.getEnabled();
         this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName()+""+entity.getCreatedBy().getLastName() : null;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
