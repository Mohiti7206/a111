package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
 public class AdminUserMvc extends AdminBaseAuditableEntity {

	private static final long serialVersionUID = 2L;

//	// Qamar
	@ManyToOne
	@JoinColumn(name = "ar_teams_id")
	private AdminArTeams  arTeam;
	// qamar

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", updatable = false, insertable = true)
	private String email;

	@Column(name = "password", updatable = false, insertable = true)
	private String password;

	@Column(name = "contact")
	private String contact;

	@Column(name = "address", columnDefinition = "TEXT")
	private String address;

	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean enabled = true;

	@NotNull
	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
	private Boolean deleted = false;

	@Column(name = "target")
	private Integer target;

	@Column(name = "user_location")
	private String location;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = true, updatable = true)
	private AdminRoleEntityMvc role;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_permission", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private Set<AdminPermissionEntityMvc> permissions;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_department", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "department_id", referencedColumnName = "id") })
//	@JsonManagedReference(value = "user-department")          									// added on 13-feb-2025
	private Set<AdminDepartmentEntityMvc> departments;



	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_email_template", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "email_template_id", referencedColumnName = "id") })
	private Set<AdminEmailTemplateEntity2> emailTemplates;


	public Set<AdminEmailTemplateEntity2> getEmailTemplates() {
		return emailTemplates;
	}

	public void setEmailTemplates(Set<AdminEmailTemplateEntity2> emailTemplates) {
		this.emailTemplates = emailTemplates;
	}

	public Set<AdminDepartmentEntityMvc> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<AdminDepartmentEntityMvc> departments) {
		this.departments = departments;
	}

	public Set<AdminPermissionEntityMvc> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<AdminPermissionEntityMvc> permissions) {
		this.permissions = permissions;
	}
}
