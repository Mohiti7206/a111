package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DoctorRequestDTO {



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


    @NotNull(message = "nonDeposit field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean nonDeposit;



    private Long companyId;
    private String code;
    private Float accounting;
    private Float operations;
    private Float payments;
    private String userTimeZone;
    private Boolean status;
    private Boolean deleted;
    private AdminDoctorGroupMvc group;

    private AdminDoctorEntityMvc parent;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getAccounting() {
        return accounting;
    }

    public void setAccounting(Float accounting) {
        this.accounting = accounting;
    }

    public Float getOperations() {
        return operations;
    }

    public void setOperations(Float operations) {
        this.operations = operations;
    }

    public Float getPayments() {
        return payments;
    }

    public void setPayments(Float payments) {
        this.payments = payments;
    }

    public Boolean getNonDeposit() {
        return nonDeposit;
    }

    public void setNonDeposit(Boolean nonDeposit) {
        this.nonDeposit = nonDeposit;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
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

    public AdminDoctorCompanyEntityMvc getCompany() {
        return company;
    }

    public void setCompany(AdminDoctorCompanyEntityMvc company) {
        this.company = company;
    }

    public AdminDoctorGroupMvc getGroup() {
        return group;
    }

    public void setGroup(AdminDoctorGroupMvc group) {
        this.group = group;
    }

    public AdminDoctorEntityMvc getParent() {
        return parent;
    }

    public void setParent(AdminDoctorEntityMvc parent) {
        this.parent = parent;
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