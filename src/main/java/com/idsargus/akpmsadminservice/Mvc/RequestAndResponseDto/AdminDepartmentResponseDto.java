package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
//import com.idsargus.akpmsadminservice.entity.AdminDepartmentEntity;

import java.time.LocalDateTime;

public class AdminDepartmentResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String target;
    private String noidaTarget;
    private String description;
    private String location;
    private String noidaLocation;
    private Boolean enabled;
    private Boolean deleted;
    private String createdByUserName; // Concatenated first and last name of creator
    private String modifiedByUserName; // Concatenated first and last name of modifier
    private Integer parentId; // Changed from AdminDepartmentEntityMvc to Integer
    private String parentDepartmentName;



    public AdminDepartmentResponseDto(Integer id,
                                      LocalDateTime createdOn, LocalDateTime modifiedOn,
                                      String name, String target, String noidaTarget,
                                      String description,
                                      String location,
                                      String noidaLocation,
                                      Boolean enabled, Boolean deleted,
                                      String createdByUserName, String modifiedByUserName,
                                      AdminDepartmentEntityMvc parent,
                                      String parentDepartmentName
    ) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.target = target;
        this.noidaTarget = noidaTarget;
        this.description = description;
        this.location = location;
        this.noidaLocation = noidaLocation;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
        this.parentId = parent != null ? parent.getId() : null;
        this.parentDepartmentName = parentDepartmentName;
    }


    public AdminDepartmentResponseDto(AdminDepartmentEntityMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName()+""+entity.getCreatedBy().getLastName() : null;
        this.createdOn = entity.getCreatedOn();
        this.modifiedOn = entity.getModifiedOn();
    }

    public AdminDepartmentResponseDto() {

    }

    public String getModifiedByUserName() {
        return modifiedByUserName;
    }

    public void setModifiedByUserName(String modifiedByUserName) {
        this.modifiedByUserName = modifiedByUserName;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }
//
    public String getParentDepartmentName() {
        return parentDepartmentName;
    }

    public void setParentDepartmentName(String parentDepartmentName) {
        this.parentDepartmentName = parentDepartmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//
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
//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
////
//
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNoidaLocation() {
        return noidaLocation;
    }

    public void setNoidaLocation(String noidaLocation) {
        this.noidaLocation = noidaLocation;
    }
////
//    public Boolean getEnabled() {
//        return enabled;
//    }
////
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
//
    public String getNoidaTarget() {
        return noidaTarget;
    }

    public void setNoidaTarget(String noidaTarget) {
        this.noidaTarget = noidaTarget;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    //
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
//
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
//
//    public String getCreatedByUserName() {
//        return createdByUserName;
//    }
//
//    public void setCreatedByUserName(String createdByUserName) {
//        this.createdByUserName = createdByUserName;
//    }
//
//    public String getModifiedByUserName() {
//        return modifiedByUserName;
//    }
//
//    public void setModifiedByUserName(String modifiedByUserName) {
//        this.modifiedByUserName = modifiedByUserName;
//    }
}
