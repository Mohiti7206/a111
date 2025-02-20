package com.idsargus.akpmsadminservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminBaseAuditableEntity;
import com.idsargus.akpmscommonservice.entity.BaseAuditableEntity;
import com.idsargus.akpmscommonservice.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "email_template")
@Getter
@Setter
public class AdminEmailTemplateEntity extends AdminBaseAuditableEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@NotNull
	@Column(name = "subscription_email", columnDefinition = "BIT default 0", nullable = false)
	private Boolean subscriptionEmail;

	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean enabled = true;

	@NotNull
	@Column(name = "status", columnDefinition = "boolean default true", nullable = false)
	private Boolean status = true;


	@NotNull
	@Column(name = "is_deleted", columnDefinition = "boolean default false", nullable = false)
	private Boolean is_deleted = false;

	@ManyToMany(mappedBy = "emailTemplates")
	private List<User> users;




	@JsonBackReference("createdByReference")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
	private UserEntity userObj;
	public String getCreatedByUserName() {
		return (userObj != null && userObj.getFirstName() != null)
				? userObj.getFirstName() + " " + userObj.getLastName()
				: null;
	}

	@JsonBackReference("modifiedByReference")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
	private UserEntity userqbj1;
	public String getModifiedByUserName() {
		return (userqbj1 != null && userqbj1.getFirstName() != null)
				? userqbj1.getFirstName() + " " + userqbj1.getLastName()
				: null;
	}













////	@Column(name = "created_by")
////	private  Integer createdBy;
//
//
//	@JsonBackReference("createdByReference")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//	private UserEntity userObj;
//
//
//
//	public String getCreatedByUserName() {
//		return (userObj != null && userObj.getFirstName() != null)
//				? userObj.getFirstName() + " " + userObj.getLastName()
//				: null;
//	}



}