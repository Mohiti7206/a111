package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
//import com.idsargus.akpmsadminservice.entity.User;
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
public class AdminEmailTemplateEntity2 extends AdminBaseAuditableEntity {

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
	private List<AdminUserMvc> users;


}