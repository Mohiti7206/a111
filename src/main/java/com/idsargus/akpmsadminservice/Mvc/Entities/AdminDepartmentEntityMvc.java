package com.idsargus.akpmsadminservice.Mvc.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
//import com.idsargus.akpmsadminservice.entity.AdminDepartmentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "department")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AdminDepartmentEntityMvc extends AdminBaseAuditableEntity {

	private static final long serialVersionUID = 3L;

	private String name;

    @Column(name = "mohali_target")
    private String target;

	@Column(name = "noida_target")
	private String noidaTarget;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;


	@Column(name = "mohali_location")
	private String location;

	@Column(name = "noida_location")
	private String noidaLocation;



	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean enabled = true;

	@NotNull
	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
	private Boolean deleted = false;

	@OneToMany(targetEntity = AdminDepartmentEntityMvc.class, mappedBy = "parent", fetch = FetchType.LAZY)
//	private List<AdminDepartmentEntity> departments = null;
	@JsonManagedReference(value = "user-department")   // This will be serialized                         // added on 13-feb-2025
	private List<AdminDepartmentEntityMvc> departments = null;

	//@JsonIgnore
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = false) })
//	private AdminDepartmentEntity parent = null;
	@JsonBackReference(value = "user-department")        // Prevents infinite loop							 // added on 13-feb-2025
	private AdminDepartmentEntityMvc parent = null;






	@Transient
	private Integer parentId;

	@Override
	public String toString() {
		return super.getId().toString();
	}

	public Integer getParentId() {
		return (this.parent == null) ? null : this.parent.getId();
	}
}