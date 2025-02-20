package com.idsargus.akpmsadminservice.Mvc.Entities;

//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//public class AdminLocations {
//}
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idsargus.akpmscommonservice.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
        import javax.validation.constraints.NotNull;
//private String name;
//
//@NotNull
//@Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT '1'")
//private boolean enabled = true;
//
//@Column(name = "description", columnDefinition = "TEXT")
//private String desc;
//
//@NotNull
//@Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT '0'")
//private boolean deleted = false;
@Getter
@Setter
@Entity
@Table(name = "location")
public class AdminLocations extends AdminBaseAuditableEntity {


    private String name;

    @NotNull
    @Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT '1'")
    private boolean enabled = true;

//    @Column(name = "`desc`") // Escape the reserved keyword
//    private String desc;

    @Column(name = "description", columnDefinition = "TEXT")
    private String desc;








    @NotNull
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT '0'")
    private boolean deleted = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull boolean enabled) {
        this.enabled = enabled;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @NotNull
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(@NotNull boolean deleted) {
        this.deleted = deleted;
    }
}