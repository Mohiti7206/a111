package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ValidationGroups;
import com.idsargus.akpmsadminservice.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MoneySourceRequestDto {


    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;


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
