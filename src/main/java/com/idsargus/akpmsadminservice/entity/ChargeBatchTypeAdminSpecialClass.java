//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmscommonservice.entity.BaseIdEntity;
//import com.idsargus.akpmscommonservice.entity.UserEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "charge_batch_type")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class ChargeBatchTypeAdminSpecialClass extends BaseIdEntity {
//
//
//    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//
//    private   String name;
//
//    private String description;
//
//    @NotNull
//    @Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//    private Boolean deleted = false;
//
//    @NotNull
//    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//    private Boolean enabled = true;
//
//
//
//
//
//
//    @Column(name = "created_by", columnDefinition = "INTEGER DEFAULT NULL")
//    private Integer createdBy;
//
//    @Column(name = "modified_by", columnDefinition = "INTEGER DEFAULT NULL")
//    private Integer modifiedBy;
//
//    @Column(name = "created_on", insertable = true, updatable = false)
//    private LocalDateTime createdOn = LocalDateTime.now();
//
//    //@javax.persistence.Temporal(TemporalType.DATE)
//    @Column(name = "modified_on", insertable = false, updatable = true)
//    private LocalDateTime modifiedOn ;
//
//    @JsonBackReference("createdByReference")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumns({@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//    private UserEntity userObj;
//    public String getCreatedByUserName() {
//        return (userObj != null && userObj.getFirstName() != null)
//                ? userObj.getFirstName() + " " + userObj.getLastName()
//                : null;
//    }
//
//    @JsonBackReference("modifiedByReference")
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumns({@JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//    private UserEntity userqbj1;
//    public String getModifiedByUserName() {
//        return (userqbj1 != null && userqbj1.getFirstName() != null)
//                ? userqbj1.getFirstName() + " " + userqbj1.getLastName()
//                : null;
//    }
//
//    public Integer getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public Integer getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(Integer modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    public LocalDateTime getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(LocalDateTime createdOn) {
//        this.createdOn = createdOn;
//    }
//
//    public LocalDateTime getModifiedOn() {
//        return modifiedOn;
//    }
//
//    public void setModifiedOn(LocalDateTime modifiedOn) {
//        this.modifiedOn = modifiedOn;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setDeleted(@NotNull Boolean deleted) {
//        this.deleted = deleted;
//    }
//
//    public void setEnabled(@NotNull Boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public   String getName() {
//        return name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public @NotNull Boolean getDeleted() {
//        return deleted;
//    }
//
//    public @NotNull Boolean getEnabled() {
//        return enabled;
//    }
//
//}
