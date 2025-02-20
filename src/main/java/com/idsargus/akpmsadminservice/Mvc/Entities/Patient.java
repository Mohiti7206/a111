package com.idsargus.akpmsadminservice.Mvc.Entities;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Patient")
@Getter
@Setter
public class Patient extends AdminBaseAuditableEntity {



    public Patient() {
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "patient_name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
