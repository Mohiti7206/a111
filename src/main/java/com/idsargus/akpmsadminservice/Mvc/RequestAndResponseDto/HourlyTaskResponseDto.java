package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskNameMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;
//import com.idsargus.akpmsadminservice.entity.Department;

import java.time.LocalDateTime;

public class HourlyTaskResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String description;
    private Boolean chargeable;
    private Boolean enabled;
    private Boolean deleted;
    private String Department_name;
    private String createdByUserName;
    private String modifiedByUserName;


    public HourlyTaskResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn, String name, String description, Boolean chargeable, Boolean enabled, Boolean deleted, String department_name, String createdByUserName, String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.description = description;
        this.chargeable = chargeable;
        this.enabled = enabled;
        this.deleted = deleted;
        this.Department_name = department_name;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }
    // Constructor to create DTO from entity
    public HourlyTaskResponseDto(AdminHourlyTaskNameMvc savedTemplate) {
        this.id = savedTemplate.getId();
        this.createdOn = savedTemplate.getCreatedOn();
        this.modifiedOn = savedTemplate.getModifiedOn();
        this.name = savedTemplate.getName();
        this.chargeable = savedTemplate.getChargeable();
        this.description = savedTemplate.getDescription();
        this.enabled = savedTemplate.getEnabled();
        this.deleted = savedTemplate.getDeleted();
        this.Department_name = savedTemplate.getDepartment() != null ? savedTemplate.getDepartment().getName() : null;

        // Assuming UserEntity has methods getFirstName() and getLastName()
        if (savedTemplate.getCreatedBy() != null) {
            this.createdByUserName = savedTemplate.getCreatedBy().getFirstName() + " " +
                    savedTemplate.getCreatedBy().getLastName();
        } else {
            this.createdByUserName = ""; // or null, based on your preference
        }

        if (savedTemplate.getModifiedBy() != null) {
            this.modifiedByUserName = savedTemplate.getModifiedBy().getFirstName() + " " +
                    savedTemplate.getModifiedBy().getLastName();
        } else {
            this.modifiedByUserName = ""; // or null, based on your preference
        }
    }

    public HourlyTaskResponseDto() {

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

    public Boolean getChargeable() {
        return chargeable;
    }

    public void setChargeable(Boolean chargeable) {
        this.chargeable = chargeable;
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

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
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
