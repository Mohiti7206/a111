package com.idsargus.akpmsadminservice.Mvc.Entities;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "revenue_type")
public class AdminRevenueTypeMvc extends AdminBaseAuditableEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    @Column(name = "payments")
    private Float payments = 0F;

    @Column(name = "operations")
    private Float operations = 0F;

    @Column(name = "accounting")
    private Float accounting = 0F;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;

    @NotNull
    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted = false;


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
}
