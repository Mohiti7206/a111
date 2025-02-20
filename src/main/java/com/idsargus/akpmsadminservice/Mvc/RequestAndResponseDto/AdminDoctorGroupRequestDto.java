package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
import com.idsargus.akpmsadminservice.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class AdminDoctorGroupRequestDto {

//    @NotNull(message = "Name cannot be null")
//    private String name;
//
//    @NotNull(message = "Enabled status cannot be null")
//    private Boolean enabled;
//
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
    @NotNull(message = "company field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private AdminDoctorCompanyEntityMvc company;












    public @NotNull(message = "Name cannot be null") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") String name) {
        this.name = name;
    }

    public @NotNull(message = "Enabled status cannot be null") Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull(message = "Enabled status cannot be null") Boolean enabled) {
        this.enabled = enabled;
    }

    public AdminDoctorCompanyEntityMvc getCompany() {
        return company;
    }

    public void setCompany(AdminDoctorCompanyEntityMvc company) {
        this.company = company;
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
