package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientRequestDto {




    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;


    public @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}) String name) {
        this.name = name;
    }

    public @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class) AdminUserMvc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class) AdminUserMvc createdBy) {
        this.createdBy = createdBy;
    }

    public @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class) AdminUserMvc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(@NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class) AdminUserMvc modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}