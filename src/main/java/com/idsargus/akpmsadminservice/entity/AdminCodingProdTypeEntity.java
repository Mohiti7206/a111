//package com.idsargus.akpmsadminservice.entity;
//
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "coding_productivity_type")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class AdminCodingProdTypeEntity  extends AdminBaseAuditableEntity {
//
//    private static final long serialVersionUID = 1L;
//
//    @Column(name = "name")
//    private @NotNull String name;
//
//    @NotNull
//    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//
//    public @NotNull String getName() {
//        return name;
//    }
//
//    public void setName(@NotNull String name) {
//        this.name = name;
//    }
//
//    public @NotNull Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(@NotNull Boolean enabled) {
//        this.enabled = enabled;
//    }
//}
