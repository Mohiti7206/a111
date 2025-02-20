package com.idsargus.akpmsadminservice.Mvc.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "doctor_group")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminDoctorGroupMvc extends AdminBaseAuditableEntity {

    @NotNull
    @Column(unique=true)
    private String name;

    //@NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doctor_company_id", referencedColumnName = "id")
    private AdminDoctorCompanyEntityMvc company;



    @NotNull
    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;




//
//        @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
//    private UserEntity userObj;
//
//
//    public String getCreatedByUserName() {
//        return (userObj != null && userObj.getFirstName() != null)
//                ? userObj.getFirstName() + " " + userObj.getLastName()
//                : null;
//    }
//
//
//
//        @JsonBackReference
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
//    private UserEntity userObj2;
//
//
//    public String getModifiedByUserName() {
//        return (userObj2 != null && userObj2.getFirstName() != null)
//                ? userObj2.getFirstName() + " " + userObj2.getLastName()
//                : null;
//    }


    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public AdminDoctorCompanyEntityMvc getCompany() {
        return company;
    }

    public void setCompany(AdminDoctorCompanyEntityMvc company) {
        this.company = company;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }
}