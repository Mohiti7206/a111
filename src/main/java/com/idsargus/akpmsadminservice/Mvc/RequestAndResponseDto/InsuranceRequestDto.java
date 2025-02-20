package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//public class InsuranceRequestDto {
//}
//package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.entity.User;

import javax.validation.constraints.NotNull;

public class InsuranceRequestDto {

//    private String name;
//    @NotNull
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





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    private String description;

    @NotNull
    private Boolean deleted;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }


    public @NotNull Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(@NotNull Boolean deleted) {
        this.deleted = deleted;
    }

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
}
