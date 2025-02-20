package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AdminRevenueTypeMvcResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String code;
    private Float payments;
    private Float operations;
    private Float accounting;
    private String description;
    private Boolean enabled;
    private Boolean deleted;
    private String createdByUserName; // Concatenated first and last name of creator
    private String modifiedByUserName; // Concatenated first and last name of modifier

    public AdminRevenueTypeMvcResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn, String name, String code, Float payments, Float operations, Float accounting, String description, Boolean enabled, Boolean deleted, String createdByUserName, String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.code = code;
        this.payments = payments;
        this.operations = operations;
        this.accounting = accounting;
        this.description = description;
        this.enabled = enabled;
        this.deleted = deleted;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
    }


    // Constructor to create DTO from entity
    public AdminRevenueTypeMvcResponseDto(AdminRevenueTypeMvc savedTemplate) {
        this.id = savedTemplate.getId();
        this.createdOn = savedTemplate.getCreatedOn();
        this.modifiedOn = savedTemplate.getModifiedOn();
        this.name = savedTemplate.getName();
        this.code = savedTemplate.getCode();
        this.payments = savedTemplate.getPayments();
        this.operations = savedTemplate.getOperations();
        this.accounting = savedTemplate.getAccounting();
        this.description = savedTemplate.getDescription();
        this.enabled = savedTemplate.getEnabled();
        this.deleted = savedTemplate.getDeleted();

        // Assuming UserEntity has methods getFirstName() and getLastName()
        if (savedTemplate.getCreatedBy() != null) {
            this.createdByUserName = savedTemplate.getCreatedBy().getFirstName() + " " +
                    savedTemplate.getCreatedBy().getLastName();
        } else {
            this.createdByUserName = ""; // or null, based on your preference
        }

        if (savedTemplate.getModifiedBy() != null) {
            this.modifiedByUserName = savedTemplate.getModifiedBy().getFirstName() + " " +
                    savedTemplate.getModifiedBy().getLastName();
        } else {
            this.modifiedByUserName = ""; // or null, based on your preference
        }
    }


//    public AdminEmailTemplateResponseDTO(AdminEmailTemplateEntity2 entity) {
//        this.id = entity.getId();
//        this.name = entity.getName();
//        this.content = entity.getContent();
//        this.subscriptionEmail = entity.getSubscriptionEmail();
//        this.enabled = entity.getEnabled();
//        this.status = entity.getStatus();
//        this.is_deleted = entity.getIs_deleted();
//    }

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
