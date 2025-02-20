package com.idsargus.akpmsadminservice.Mvc.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idsargus.akpmscommonservice.entity.BaseIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass

@Getter
@Setter
public abstract class AdminBaseAuditableEntity extends BaseIdEntity {

    private static final long serialVersionUID = 1L;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "created_by", referencedColumnName = "id", insertable = true, updatable = false) })
    @JsonBackReference(value = "user-created")  // Prevents cycle during serialization             // added on 13-feb-2025
//    private User createdBy;
    private AdminUserMvc createdBy;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modified_by", referencedColumnName = "id", insertable = true, updatable = true)
    @JsonBackReference(value = "user-modified")  // Prevents cycle during serialization             // added on 13-feb-2025
//    private User modifiedBy;
    private AdminUserMvc modifiedBy;


    @Column(name = "created_on", insertable = true, updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

  //  @javax.persistence.Temporal(TemporalType.DATE)
    @Column(name = "modified_on", insertable = false, updatable = true)
    private LocalDateTime modifiedOn;


    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdOn = now;


//        System.out.println("--------------------------"+(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

    @PreUpdate
    public void preUpdate() {
        LocalDateTime now = LocalDateTime.now();
        modifiedOn = now;


    }

}
