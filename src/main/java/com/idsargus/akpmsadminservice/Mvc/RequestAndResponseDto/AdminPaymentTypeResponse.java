package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

//public class AdminPaymentTypeResponse {
//}



//import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentType;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentTypeMvc;

import java.time.LocalDateTime;

public class AdminPaymentTypeResponse {
    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String description;
    private Boolean enabled;
    private Boolean deleted;
    private String createdByUserName;
    private String modifiedByUserName;

    public AdminPaymentTypeResponse() {
    }

    public AdminPaymentTypeResponse(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn, String name, String description, Boolean enabled, Boolean deleted, String createdByUserName, String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }

    public AdminPaymentTypeResponse(AdminPaymentTypeMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.createdOn = entity.getCreatedOn();
        this.description = entity.getDescription();
        this.deleted = entity.getDeleted();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " "+ entity.getCreatedBy().getLastName() : null;
        this.modifiedByUserName =  entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " "+ entity.getModifiedBy().getLastName() : null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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