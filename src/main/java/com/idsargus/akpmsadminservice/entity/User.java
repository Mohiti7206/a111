package com.idsargus.akpmsadminservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminBaseAuditableEntity;
import com.idsargus.akpmscommonservice.entity.ArTeams;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "user")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class User extends AdminBaseAuditableEntity {

	private static final long serialVersionUID = 2L;

	// Qamar
	@ManyToOne
	@JoinColumn(name = "ar_teams_id")
	private ArTeams arTeam;
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

//    @Column(name= "login_status")
//    private String loginStatus;

	// added by ARN
//	@Column(name = "role_id", nullable=false)
	@Transient
	private Integer userRoleId;

	// @OneToOne
	// @JoinColumn(name = "role_id")
	// added by ARN
	// @JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = true, updatable = true)
	private AdminRoleEntity role;

	@Transient
	private List<String> permissionIds;

	@Transient
	private List<Integer> departmentIds;

	@Transient
	private List<Integer> emailTemplateIds;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_permission", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private Set<AdminPermissionEntity> permissions;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_department", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "department_id", referencedColumnName = "id") })
	private Set<AdminDepartmentEntity> departments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_email_template", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "email_template_id", referencedColumnName = "id") })
	private Set<AdminEmailTemplateEntity> emailTemplates;


	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public Integer getUserRoleId() {
		return (this.role == null) ? null : this.role.getId();
	}

	public List<String> getPermissionIds() {
		return (this.permissions == null) ? null
				: this.permissions.stream().map(AdminPermissionEntity::getId).collect(Collectors.toList());
	}

	public List<Integer> getDepartmentIds() {
		return (this.departments == null) ? null
				: this.departments.stream().map(AdminDepartmentEntity::getId).collect(Collectors.toList());
	}

	public List<Integer> getEmailTemplateIds() {
		return (this.emailTemplates == null) ? null
				: this.emailTemplates.stream().map(AdminEmailTemplateEntity::getId).collect(Collectors.toList());
	}
}
