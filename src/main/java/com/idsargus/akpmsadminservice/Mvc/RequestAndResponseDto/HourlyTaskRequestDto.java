package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.entity.AdminDepartmentEntity;
//import com.idsargus.akpmsadminservice.entity.Department;
import com.idsargus.akpmsadminservice.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HourlyTaskRequestDto {

//    @NotNull(message = "Name cannot be null")
//    private String name;
//    private Boolean enabled;
//    private AdminUserMvc modifiedBy;
//    private AdminUserMvc createdBy;


    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;


    @NotNull(message = "department field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private AdminDepartmentEntity department; // Reference to Department DTO

    @NotNull(message = "chargeable field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean chargeable;

    private String description;


    private Boolean deleted;


    @NotNull(message = "User time zone cannot be null")
    private String userTimeZone;





    public AdminUserMvc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(AdminUserMvc modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public AdminUserMvc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AdminUserMvc createdBy) {
        this.createdBy = createdBy;
    }

    public @NotNull(message = "Name cannot be null") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") String name) {
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

    public Boolean getChargeable() {
        return chargeable;
    }

    public void setChargeable(Boolean chargeable) {
        this.chargeable = chargeable;
    }

    public @NotNull(message = "User time zone cannot be null") String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(@NotNull(message = "User time zone cannot be null") String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public AdminDepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(AdminDepartmentEntity department) {
        this.department = department;
    }
}
