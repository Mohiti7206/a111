//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.idsargus.akpmscommonservice.entity.UserEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "doctor_company")
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
//public class AdminDoctorCompanyEntity extends AdminBaseAuditableEntity {
//
//	@NotNull
//	@Column(unique=true)
//	private String name;
//
//	@NotNull
//	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//	private Boolean enabled = true;
//
//
//
//	public @NotNull Boolean getEnabled() {
//		return enabled;
//	}
//}
