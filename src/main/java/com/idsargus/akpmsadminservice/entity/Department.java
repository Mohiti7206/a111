//package com.idsargus.akpmsadminservice.entity;
//
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
//@Table(name = "department")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class Department extends AdminBaseAuditableEntity {
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//
//    private String name;
//
////
////    @Column(name = "target")
////    private Integer target;
//
//    @Column(name = "description", columnDefinition = "TEXT")
//    private String description;
//
//    @NotNull
//    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//    @NotNull
//    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//    private Boolean deleted = false;
//
//    @OneToMany(targetEntity = Department.class, mappedBy = "parent", fetch = FetchType.LAZY)
//    private List<Department> departments = null;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
//    private Department parent = null;
//
//    @Transient
//    private Integer parentId;
//
//    @Override
//    public String toString() {
//        return super.getId().toString();
//    }
//
//    public Integer getParentId() {
//        return (this.parent == null) ? null : this.parent.getId();
//    }
//
//}