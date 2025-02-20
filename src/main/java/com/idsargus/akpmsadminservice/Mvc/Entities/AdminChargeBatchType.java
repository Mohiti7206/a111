package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Table(name = "charge_batch_type")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminChargeBatchType extends AdminBaseAuditableEntity {

    private static final long serialVersionUID = 1L;

    private   String name;

    private String description;

    @NotNull
    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted = false;

    @NotNull
    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(@NotNull Boolean deleted) {
        this.deleted = deleted;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }
}
