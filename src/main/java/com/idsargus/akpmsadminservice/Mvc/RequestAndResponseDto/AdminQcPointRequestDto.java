package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminQcPointRequestDto {

//    private String name;
//    private Boolean enabled;
//    private AdminUserMvc createdBy;
//     private AdminUserMvc modifiedBy;


    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;

//    @NotNull(message = "parent  field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Integer parentId;



    private String userTimeZone;
    private Boolean deleted;
    private String codingProdType;
    private String description;

    private String subDepartmentId;
    private Integer departmentId;

    public AdminQcPointRequestDto(String name, String description,
                                  Boolean enabled, String userTimeZone,
                                  Boolean deleted, String codingProdType,
                                  String subDepartmentId, Integer parentId,
                                  Integer departmentId,
                                  AdminUserMvc createdBy,
                                  AdminUserMvc modifiedBy
//                                  Integer createdBy
    ) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.userTimeZone = userTimeZone;
        this.deleted = deleted;
        this.codingProdType = codingProdType;
        this.subDepartmentId = subDepartmentId;
        this.parentId = parentId;
        this.departmentId = departmentId;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
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

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCodingProdType() {
        return codingProdType;
    }

    public void setCodingProdType(String codingProdType) {
        this.codingProdType = codingProdType;
    }

    public String getSubDepartmentId() {
        return subDepartmentId;
    }

    public void setSubDepartmentId(String subDepartmentId) {
        this.subDepartmentId = subDepartmentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public AdminUserMvc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AdminUserMvc createdBy) {
        this.createdBy = createdBy;
    }

    public AdminUserMvc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(AdminUserMvc modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    //
//    public Integer getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }
}
