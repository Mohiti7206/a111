package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminRevenueTypeRequestDto {

//    @NotNull(message = "Name cannot be null")
//    private String name;
//    @NotNull(message = "Enabled status cannot be null")
//    private Boolean enabled;
//    private AdminUserMvc modifiedBy;
//    private AdminUserMvc createdBy;
//


    @Schema(example = "John2")
    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;




    private String code;

    private Float payments;

    private Float operations;

    private Float accounting;

    private String description;



    @NotNull(message = "Deleted status cannot be null")
    private Boolean deleted;

    // You can add additional fields if needed, such as createdBy or modifiedBy



    public @NotNull(message = "Name cannot be null") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getPayments() {
        return payments;
    }

    public void setPayments(Float payments) {
        this.payments = payments;
    }

    public Float getOperations() {
        return operations;
    }

    public void setOperations(Float operations) {
        this.operations = operations;
    }

    public Float getAccounting() {
        return accounting;
    }

    public void setAccounting(Float accounting) {
        this.accounting = accounting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "Enabled status cannot be null") Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull(message = "Enabled status cannot be null") Boolean enabled) {
        this.enabled = enabled;
    }

    public @NotNull(message = "Deleted status cannot be null") Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(@NotNull(message = "Deleted status cannot be null") Boolean deleted) {
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
