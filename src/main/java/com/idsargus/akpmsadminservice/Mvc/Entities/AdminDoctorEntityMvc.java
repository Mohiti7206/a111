
package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorGroup;
//import com.idsargus.akpmsadminservice.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "doctor")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class AdminDoctorEntityMvc extends AdminBaseAuditableEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    @Column(name = "doctorCode")
    private String code;

    @Column(name = "payments")
    private Float payments = 0F;

    @Column(name = "operations")
    private Float operations = 0F;

    @Column(name = "accounting")
    private Float accounting = 0F;

    @NotNull
    @Column(name = "non_deposit", columnDefinition = "BIT default 1", nullable = false)
    private Boolean nonDeposit;


    @NotNull
    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;


    @NotNull
    @Column(name = "status", columnDefinition = "boolean default true", nullable = false)
    private Boolean status = true;


    @NotNull
    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<AdminDoctorEntityMvc> doctors = null;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
    private AdminDoctorEntityMvc parent = null;




//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)   // working
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private AdminDoctorCompanyEntityMvc company;




    @ManyToOne( fetch = FetchType.LAZY)     // working
//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name="group_id",referencedColumnName = "id")
    private AdminDoctorGroupMvc group;



    public @NotNull Boolean getNonDeposit() {
        return nonDeposit;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public AdminDoctorGroupMvc getGroup() {
        return group;
    }

    public void setGroup(AdminDoctorGroupMvc group) {
        this.group = group;
    }
}
