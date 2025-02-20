//package com.idsargus.akpmsadminservice.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmscommonservice.entity.UserEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//@Entity
//@Getter
//@Setter
//@Table(name = "doctor_group")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class AdminDoctorGroup extends AdminBaseAuditableEntity {
//
//    @NotNull
//    @Column(unique=true)
//    private String name;
//
//    //@NotNull
//    @ManyToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "doctor_company_id", referencedColumnName = "id")
//    private AdminDoctorCompanyEntity company;
//
//
//
//    @NotNull
//    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//
//
//
////
////        @JsonBackReference
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
////
////
////
////        @JsonBackReference
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
//
//
//
//
//
//
//
//}