package com.idsargus.akpmsadminservice.Mvc.Entities;

//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ar_teams")
@Getter
@Setter
public class AdminArTeams extends AdminBaseAuditableEntity {



    public AdminArTeams() {
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "ar_team_name")
    private String name;

    @NotNull
    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }
}