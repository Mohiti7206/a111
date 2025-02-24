//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
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
//@Table(name = "doctor")
////@JsonIgnore
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class AdminDoctor extends AdminBaseAuditableEntity {
//
//    private static final long serialVersionUID = 1L;
//
//    private String name;
//
//    @Column(name = "doctorCode")
//    private String code;
//
//    @Column(name = "payments")
//    private Float payments = 0F;
//
//    @Column(name = "operations")
//    private Float operations = 0F;
//
//    @Column(name = "accounting")
//    private Float accounting = 0F;
//
//    @NotNull
//    @Column(name = "non_deposit", columnDefinition = "BIT default 1", nullable = false)
//    private Boolean nonDeposit;
//
//    @NotNull
//    @Column(name = "status", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//    @NotNull
//    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//    private Boolean deleted = false;
//
//
//    @OneToMany( mappedBy = "parent", fetch = FetchType.LAZY)
//    private List<AdminDoctor> doctors = null;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
//    private AdminDoctor parent = null;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="company_id",referencedColumnName = "id")
//    private AdminDoctorCompanyEntity company;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="group_id",referencedColumnName = "id")
//    private AdminDoctorGroup group;
//
//    public String getGroupName() {
//        return (this.group == null) ? null : this.group.getName();
//
//    }
//
//    @Transient
//    private String groupName;
//    @Transient
//    private Integer parentId;
//    @Transient
//    private String companyName;
//
//    public String getCompanyName() {
//        return (this.company == null) ? null : this.company.getName();
//    }
//
//    @Transient
//    private Integer companyId;
//
//    public Integer getCompanyId() {
//        return (this.company == null) ? null : this.company.getId();
//    }
//
//    @Transient
//    private Integer groupId;
//
//    public Integer getGroupId() {
//        return (this.group == null) ? null : this.group.getId();
//    }
//
//    public Integer getParentId() {
//        return (this.parent == null) ? null : this.parent.getId();
//    }
//}
