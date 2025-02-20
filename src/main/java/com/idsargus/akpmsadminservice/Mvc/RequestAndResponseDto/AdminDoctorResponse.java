package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

//public class AdminDoctorResponse {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentTypeMvc;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AdminDoctorResponse {
    private Integer id;
    private Integer companyId;
    private Integer groupId;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private Boolean enabled;
    private Boolean deleted;
    private Boolean nonDeposit;
    private String createdByUserName;
    private String modifiedByUserName;

    private String code;
    private String groupName;
    private String companyName;
    private String parent;

    private Float payments  ;

    private Float operations ;

    private Float accounting  ;


    public AdminDoctorResponse(Integer id,
                               LocalDateTime createdOn,
                               LocalDateTime modifiedOn,
                               Float accounting,
                               Boolean deleted,
                               Boolean nonDeposit,
                               String code,

                               String name,
                               Float operations,
                               Float payments,

                               Boolean enabled,
                               String groupName,
                               String companyName,
                               String parent,
                               Integer companyId,
                               Integer groupId,

                               String createdByUserName,
                               String modifiedByUserName
    )
    {
        this.id = id;
        this.companyId = companyId;
        this.groupId = groupId;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.companyName = companyName;
        this.parent = parent;
        this.groupName = groupName;
        this.name = name;
        this.enabled = enabled;
        this.deleted = deleted;
        this.nonDeposit = nonDeposit;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
        this.code = code;
        this.payments = payments;
        this.operations = operations;
        this.accounting = accounting;
    }

    public AdminDoctorResponse() {
    }

    public Boolean getNonDeposit() {
        return nonDeposit;
    }

    public void setNonDeposit(Boolean nonDeposit) {
        this.nonDeposit = nonDeposit;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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

    public AdminDoctorResponse(AdminDoctorEntityMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.createdOn = entity.getCreatedOn();
        this.deleted = entity.getDeleted();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " "+ entity.getCreatedBy().getLastName() : null;
        this.modifiedByUserName =  entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " "+ entity.getModifiedBy().getLastName() : null;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
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