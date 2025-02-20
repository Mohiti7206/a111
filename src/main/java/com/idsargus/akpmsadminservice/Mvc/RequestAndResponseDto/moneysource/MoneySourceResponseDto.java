package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminMoneySource;

import java.time.LocalDateTime;

public class MoneySourceResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private Boolean enabled;
    private Boolean deleted;
    private String createdByUserName;
    private String modifiedByUserName;


    public MoneySourceResponseDto(Integer id,
                                  LocalDateTime createdOn,
                                  LocalDateTime modifiedOn,
                                  String name,
                                  Boolean enabled,
                                  Boolean deleted,
                                  String createdByUserName,
                                  String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }


    public MoneySourceResponseDto(AdminMoneySource entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
    }

    public MoneySourceResponseDto() {

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
