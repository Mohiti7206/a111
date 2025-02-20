//package com.idsargus.akpmsadminservice.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.idsargus.akpmscommonservice.entity.BaseIdEntity;
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
//@Table(name = "money_source")
//public class AdminMoneySourceEntity extends BaseIdEntity {
//
//	private static final long serialVersionUID = 1L;
//
//	private String name;
//
//	@NotNull
//	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//	private Boolean enabled = true;
//
//	@NotNull
//	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//	private Boolean deleted = false;
//
////	@JsonBackReference("createdByReference")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//	private UserEntity userObj;
//	public String getCreatedByUserName() {
//		return (userObj != null && userObj.getFirstName() != null)
//				? userObj.getFirstName() + " " + userObj.getLastName()
//				: null;
//	}
//
////	@JsonBackReference("modifiedByReference")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//	private UserEntity userqbj1;
//	public String getModifiedByUserName() {
//		return (userqbj1 != null && userqbj1.getFirstName() != null)
//				? userqbj1.getFirstName() + " " + userqbj1.getLastName()
//				: null;
//	}
//}
