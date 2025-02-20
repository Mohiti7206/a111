package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "doctor_company")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminDoctorCompanyEntityMvc extends AdminBaseAuditableEntity {

	@NotNull
	@Column(unique=true)
	private String name;

	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean enabled = true;



	public @NotNull Boolean getEnabled() {
		return enabled;
	}
}
