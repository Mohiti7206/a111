////package com.idsargus.akpmsadminservice.entity;
////
////import java.util.List;
////
////import javax.persistence.CascadeType;
////import javax.persistence.Column;
////import javax.persistence.Entity;
////import javax.persistence.FetchType;
////import javax.persistence.JoinColumn;
////import javax.persistence.JoinColumns;
////import javax.persistence.ManyToOne;
////import javax.persistence.OneToMany;
////import javax.persistence.Table;
////import javax.persistence.Transient;
////import javax.validation.constraints.NotNull;
////
////import com.fasterxml.jackson.annotation.JsonIgnore;
////import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
////import lombok.Getter;
////import lombok.Setter;
////
////@Entity
////@Getter
////@Setter
////@Table(name = "doctor")
//////@JsonIgnore
////@JsonIgnoreProperties({"hibernateLazyInitializer"})
////public class AdminDoctorEntity extends AdminBaseAuditableEntity {
////
////    private static final long serialVersionUID = 1L;
////
////    private String name;
////
////    @Column(name = "doctorCode")
////    private String code;
////
////    @Column(name = "payments")
////    private Float payments = 0F;
////
////    @Column(name = "operations")
////    private Float operations = 0F;
////
////    @Column(name = "accounting")
////    private Float accounting = 0F;
////
////    @NotNull
////    @Column(name = "non_deposit", columnDefinition = "BIT default 1", nullable = false)
////    private Boolean nonDeposit;
////
////    @NotNull
////    @Column(name = "status", columnDefinition = "boolean default true", nullable = false)
////    private Boolean enabled = true;
////
////    @NotNull
////    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
////    private Boolean deleted;
////
////
////    @OneToMany( mappedBy = "parent", fetch = FetchType.LAZY)
////    private List<AdminDoctorEntity> doctors = null;
////
////    @JsonIgnore
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
////    private AdminDoctorEntity parent = null;
////
////    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinColumn(name="company_id",referencedColumnName = "id")
////    private AdminDoctorCompanyEntity company;
////
////    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinColumn(name="group_id",referencedColumnName = "id")
////    private AdminDoctorGroup group;
////
////
////    @Transient
////    private String groupName;
////
////    public String getGroupName() {
////        return (this.group == null) ? null : this.group.getName();
////
////    }
////
////    @Transient
////    private Integer parentId;
////    @Transient
////    private String companyName;
////
////    public String getCompanyName() {
////        return (this.company == null) ? null : this.company.getName();
////    }
////
////    @Transient
////    private Integer companyId;
////
////    public Integer getCompanyId() {
////        return (this.company == null) ? null : this.company.getId();
////    }
////
////    @Transient
////    private Integer groupId;
////
////    public Integer getGroupId() {
////        return (this.group == null) ? null : this.group.getId();
////    }
////
////    public Integer getParentId() {
////        return (this.parent == null) ? null : this.parent.getId();
////    }
////}
//
//
//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
//import com.idsargus.akpmscommonservice.entity.UserEntity;
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
//@JsonIgnoreProperties({ "hibernateLazyInitializer" })
//public class AdminDoctorEntity extends AdminBaseAuditableEntity {
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
//    public @NotNull Boolean getNonDeposit() {
//        return nonDeposit;
//    }
//
//    @NotNull
//    @Column(name = "status", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//    public @NotNull Boolean getEnabled() {
//        return enabled;
//    }
//
//    @NotNull
//    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//    private Boolean deleted;
//
//    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
//    private List<AdminDoctorEntity> doctors = null;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
//    private AdminDoctorEntity parent = null;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id", referencedColumnName = "id")
//    private AdminDoctorCompanyEntity company;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="group_id",referencedColumnName = "id")
//    private AdminDoctorGroup group;
//
//
//
//
//    //
////    @ManyToMany(fetch = FetchType.LAZY)
////    @JoinTable(name = "doctor_groups", joinColumns = {
////            @JoinColumn(name = "doctor_id", referencedColumnName = "id") }, inverseJoinColumns = {
////            @JoinColumn(name = "group_id", referencedColumnName = "id") })
////    private Set<AdminDoctorGroup> group;
//////
//
//
//  @Transient
//  private Integer groupId;
//
//  public Integer getGroupId() {
//      return (this.group == null) ? null : this.group.getId();
//  }
//
//    @Transient
//    private String groupName;
//
//    public String getGroupName() {
//        return (this.group == null) ? null : this.group.getName();
//
//    }
//
//    //  ================================================================
////    @Transient
////    private List<Integer> groupId;
////
////    public List<Integer> getGroupId() {
//////      return (this.group == null) ? null : this.group.getId();
////        return (this.group == null) ? null : this.group.stream().map(AdminDoctorGroup::getId).collect(Collectors.toList());
////    }
////
////    @Transient
////    private List<String> groupName;
////
////    public List<String> getGroupName() {
//////        return (this.group == null) ? null : this.group.getName();
////        return (this.group == null) ? null : this.group.stream().map(AdminDoctorGroup::getName).collect(Collectors.toList());
////
////    }
//
//
//
//
////    @JsonBackReference("createdByReference")
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
////    private UserEntity userObj;
////
////
////    public String getCreatedByUserName() {
////        return (userObj != null && userObj.getFirstName() != null)
////                ? userObj.getFirstName() + " " + userObj.getLastName()
////                : null;
////    }
//
//
//
//////    @JsonBackReference("modifiedByReference")
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
////    private UserEntity userObj2;
////
////
////    public String getModifiedByUserName() {
////        return (userObj2 != null && userObj2.getFirstName() != null)
////                ? userObj2.getFirstName() + " " + userObj2.getLastName()
////                : null;
////    }
//
//
//
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "coding_prod_type_id", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
////    private AdminCodingProdTypeEntity codingProdTypes;
////
////
////
////
////    public String getCodingGroupTypeName() {
////        return (codingProdTypes != null && codingProdTypes.getName() != null)
////                ? codingProdTypes.getName()
////                : null;
////    }
//
//
//
//
//
//
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
//
//
//    public Integer getParentId() {
//        return (this.parent == null) ? null : this.parent.getId();
//    }
//}
