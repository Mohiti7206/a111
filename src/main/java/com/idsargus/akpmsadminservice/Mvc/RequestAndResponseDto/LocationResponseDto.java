package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminChargeBatchType;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;

import java.time.LocalDateTime;

public class LocationResponseDto {


    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String desc;
    private Boolean enabled;
    private Boolean deleted;
    private String createdByUserName;
    private String modifiedByUserName;

    public LocationResponseDto() {
    }


    public LocationResponseDto(AdminLocations entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.desc = entity.getDesc();
        this.enabled = entity.isEnabled();
        this.deleted = entity.isDeleted();
    }


    public LocationResponseDto(Integer id,
                               LocalDateTime createdOn,
                               LocalDateTime modifiedOn,
                               String name,
                               String desc,
                               Boolean enabled,
                               Boolean deleted,
                               String createdByUserName,
                               String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.desc = desc;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdByUserName = createdByUserName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
