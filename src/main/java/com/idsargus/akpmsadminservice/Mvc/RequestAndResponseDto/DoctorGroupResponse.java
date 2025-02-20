package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
//import com.idsargus.akpmsadminservice.entity.User;

import java.time.LocalDateTime;

public class DoctorGroupResponse {

    private  Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private Boolean enabled;
    private String company;
    private String createdByUserName;
    private String modifiedByUserName;

    public DoctorGroupResponse(Integer id, LocalDateTime createdOn,
                               LocalDateTime modifiedOn, String name, Boolean enabled,
                               String company, String createdByUserName,
                               String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.enabled = enabled;
        this.company = company;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }


    public DoctorGroupResponse(AdminDoctorGroupMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.createdOn = entity.getCreatedOn();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " "+ entity.getCreatedBy().getLastName() : null;
        this.modifiedByUserName =  entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " "+ entity.getModifiedBy().getLastName() : null;
    }

    public DoctorGroupResponse() {

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }



}
