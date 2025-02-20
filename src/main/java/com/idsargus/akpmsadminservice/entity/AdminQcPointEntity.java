////package com.idsargus.akpmsadminservice.entity;
////
////
////import lombok.Getter;
////import lombok.Setter;
////
////import javax.persistence.*;
////import javax.validation.constraints.NotNull;
////import java.util.List;
////
////@Entity
////@Getter
////@Setter
////@Table(name = "qc_point")
////public class AdminQcPointEntity extends AdminBaseAuditableEntity {
////
////	private static final long serialVersionUID = 1L;
////
////	private String name;
////
////	@OneToMany(targetEntity = AdminQcPointEntity.class, mappedBy = "parent", fetch = FetchType.LAZY)
////	private List<AdminQcPointEntity> qcPoints = null;
////
////	@ManyToOne
////	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
////	private AdminQcPointEntity parent = null;
////
////	@OneToOne
////	@JoinColumns(@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, unique = false))
////	private AdminDepartmentEntity department;
////
////	@OneToOne
////	@JoinColumns(@JoinColumn(name = "sub_department_id", referencedColumnName = "id", nullable = true, unique = false))
////	private AdminDepartmentEntity  subDepartment;
////
////	@Column(name = "description", columnDefinition = "TEXT")
////	private String description;
////
////	@NotNull
////	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
////	private Boolean enabled = true;
////
////	@NotNull
////	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
////	private Boolean deleted = false;
////
////	@Transient
////	private Integer parentId;
////
////	@Transient
////	private Integer departmentId;
////
////	@Transient
////	private Integer subDepartmentId;
////	//Prateek
////
////
////	public Integer getParentId() {
////		return (this.parent == null) ? null : this.parent.getId();
////	}
////
////	public Integer getDepartmentId() {
////
////		return (this.department == null) ? null : this.department.getId();
////	}
////
////	public Integer getSubDepartmentId() {
////		System.out.println(this.subDepartment);
////		return (this.subDepartment == null) ? null : this.subDepartment.getId();
////	}
////
////
////}
////Prateek
////package com.idsargus.akpmsadminservice.entity;
////
////import lombok.Getter;
////import lombok.Setter;
////
////import javax.persistence.*;
////import javax.validation.constraints.NotNull;
////import java.util.Collections;
////import java.util.List;
////
////@Entity
////@Getter
////@Setter
////@Table(name = "qc_point")
////public class AdminQcPointEntity extends AdminBaseAuditableEntity {
////
////	private static final long serialVersionUID = 1L;
////
////	private String name;
////
////	@OneToMany(targetEntity = AdminQcPointEntity.class, mappedBy = "parent", fetch = FetchType.LAZY)
////	private List<AdminQcPointEntity> qcPoints = null;
////
////	@ManyToOne
////	@JoinColumns({@JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true)})
////	private AdminQcPointEntity parent = null;
////
////	@OneToOne
////	@JoinColumns(@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, unique = false))
////	private AdminDepartmentEntity department;
////
////	@OneToOne
////	@JoinColumns(@JoinColumn(name = "sub_department_id", referencedColumnName = "id", nullable = true, unique = false))
////	private AdminDepartmentEntity subDepartment;
////
////	@Column(name = "description", columnDefinition = "TEXT")
////	private String description;
////
////	@NotNull
////	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
////	private Boolean enabled = true;
////
////	@NotNull
////	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
////	private Boolean deleted = false;
////
////	@Transient
////	private Integer parentId;
////
////	@Transient
////	private Integer departmentId;
////
////	@Transient
////	private Integer subDepartmentId; // Change to List<String> for multiple subDepartments
////
////	public Integer getParentId() {
////		return (this.parent == null) ? null : this.parent.getId();
////	}
////
////	public Integer getDepartmentId() {
////		return (this.department == null) ? null : this.department.getId();
////	}
////
////	// Updated getter for subDepartmentIds
////		public Integer getSubDepartmentId() {
////		System.out.println(this.subDepartment);
////		return ((this.subDepartment == null) ? null : this.subDepartment.getId());
////	}
////	Works fine
////	public void setSubDepartmentId(List<String> subDepartmentId) {
////		this.subDepartmentId = subDepartmentId;
////	}
////}
////New
//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.idsargus.akpmscommonservice.entity.BaseIdEntity;
////import com.idsargus.akpmscommonservice.entity.CodingProdType;
//
//import com.idsargus.akpmscommonservice.entity.UserEntity;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "qc_point")
//public class AdminQcPointEntity extends BaseIdEntity {
//
//	private static final long serialVersionUID = 1L;
//
//	private String name;
//
//	@OneToMany(targetEntity = AdminQcPointEntity.class, mappedBy = "parent", fetch = FetchType.LAZY)
//	private List<AdminQcPointEntity> qcPoints = null;
//
////	@ManyToOne
////	@JoinColumns({@JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true)})
////	private AdminQcPointEntity parent = null;
//
////	@OneToOne
////	@JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true, unique = true)
////	private AdminQcPointEntity parent;
////
////	@ManyToOne
////	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = true, nullable = true) })
////	private AdminQcPointEntity parent = null;
//
//
//
//
//
//
//
////
////	@OneToMany(targetEntity = AdminDepartmentEntity.class, mappedBy = "parent", fetch = FetchType.LAZY)
////	private List<AdminDepartmentEntity> departments = null;
////
////	//@JsonIgnore
////	@ManyToOne
////	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = false) })
////	private AdminDepartmentEntity parent = null;
////
////	@Transient
////	private Integer parentId;
////
////	@Override
////	public String toString() {
////		return super.getId().toString();
////	}
////
////	public Integer getParentId() {
////		return (this.parent == null) ? null : this.parent.getId();
////	}
////
//
//
//
//
//
//
//	//	@OneToOne(fetch = FetchType.LAZY)
////	@OneToOne(fetch = FetchType.EAGER)
////	@JoinColumns(@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, unique = false))
////	private AdminDepartmentEntity department;
//
//
//
//	//	@ManyToOne(fetch = FetchType.LAZY)
////	@ManyToOne(fetch = FetchType.EAGER)
////	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true) })
////	private AdminDepartmentEntity parent = null;
//
//
//
//
//
//
////	@OneToOne
////	@JoinColumns(@JoinColumn(name = "codingProdType_id", referencedColumnName = "id", nullable = true, unique = false))
////	private AdminCodingProdTypeEntity codingProdType;
//
//
//
//
//
//
//	// added by m.k
////	@OneToOne@JoinColumn(name = "coding_group_type", referencedColumnName = "id", nullable = true, unique = false)
////	private CodingProdType codingProdType = null;
//
//
//
//
//
////	@OneToOne(cascade = CascadeType.ALL) // or CascadeType.ALL
////	@JoinColumn(name = "coding_prod_type", referencedColumnName = "id", nullable = true, unique = false)
////	private CodingProdType codingProdType = null;
//
//
//
//
////	@Column(name = "coding_prod_type")
////	private Integer codingProdType;
//
//
//
//
//
//
//
//
//
//
//
//	@Column(name="coding_prod_type_id",insertable =  true, updatable = true)
//	private Integer codingProdType;
//
//
////	public String getCodingGroupTypeName() {
////		return (codingProdTypes != null && codingProdTypes.getName() != null)
////				? codingProdTypes.getName()
////				: null;
////	}
//
//
////	@OneToOne(fetch = FetchType.LAZY)
////	@JoinColumn(name = "coding_prod_type_id", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
////	private AdminCodingProdTypeEntity codingProdTypes;
//
//
//	@Column(name = "department_id",insertable =  true, updatable = true)
//	private Integer departmentId;
//
//	@Column(name = "parent_id", insertable =  true, updatable = true)
//	private Integer parentId;
//
//
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true, insertable = false,updatable = false) })
//	private AdminQcPointEntity parent = null;
//
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, unique = false,insertable = false,updatable = false)})
//	private AdminDepartmentEntity department;
//
//
//
//
//
//
//
//
//
//	@Column(name = "description", columnDefinition = "TEXT")
//	private String description;
//
//	@NotNull
//	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
//	private Boolean enabled = true;
//
//	//	@Column(name = "sub_department_id", columnDefinition = "string default null")
//	@Column(name = "sub_department_id")
//	private String subDepartmentId;
//
//
//
//
//	@NotNull
//	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
//	private Boolean deleted = false;
//
//
//
//
//
//
//
//	@JsonBackReference("createdByReference")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//	private UserEntity userObj;
//	public String getCreatedByUserName() {
//		return (userObj != null && userObj.getFirstName() != null)
//				? userObj.getFirstName() + " " + userObj.getLastName()
//				: null;
//	}
//
//	@JsonBackReference("modifiedByReference")
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name = "modified_by", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)})
//	private UserEntity userqbj1;
//	public String getModifiedByUserName() {
//		return (userqbj1 != null && userqbj1.getFirstName() != null)
//				? userqbj1.getFirstName() + " " + userqbj1.getLastName()
//				: null;
//	}
//
//
//
//
//
//
//
//
//
//
////	@Transient
////	private Integer parentId;
//
//	@Transient
//	private String parentName;
//
////	@Transient
////	private Integer departmentId;
//
//	@Transient
//	private String departmentName;
//
//
//	@Transient
////	private List<AdminQcPointEntity> listOfQcPoints;
//	private List<String> listOfQcPointsName;
//
//	@Transient
//	private List<Integer> listOfQcPointsIds;
//
//	// Remove the subDepartmentId and associated getter method
//
//
//	public Integer getParentId() {
//		return (this.parent == null) ? null : this.parent.getId();
//	}
//
//	public String getParentName(){
//		return (this.parent == null ) ? null : this.parent.getName();
//	}
//
//
//	public Integer getDepartmentId() {
//		return (this.department == null) ? null : this.department.getId();
//	}
//
//
//	public String getDepartmentName(){
//		return (this.department == null ) ? null : this.department.getName();
//	}
//
//
//
//
//
//
//	public List<String> getListOfQcPointsName() {
//		return (this.qcPoints == null) ? null
//				: this.qcPoints.stream().map(AdminQcPointEntity::getName).collect(Collectors.toList());
//	}
//
//
//
//	public List<Integer> getListOfQcPointsIds() {
//		return (this.qcPoints == null) ? null
//				: this.qcPoints.stream().map(AdminQcPointEntity::getId).collect(Collectors.toList());
//	}
//
//
//
//
//
//
//
////
////
////	public CodingProdType getCodingProdType() {
////		return codingProdType;
////	}
////
////	public void setCodingProdType(CodingProdType codingProdType) {
////		if(codingProdType != null){
////			this.codingProdType=codingProdType;
////		}
////		this.codingProdType = null;
////	}
////
//////	public void setCodingProdType(AdminQcPointEntity qcPoint) {
//////		if (qcPoint.getCodingProdType() != null && qcPoint.getCodingProdType().getId() == null) {
//////			qcPoint.setCodingProdType(null);
//////		}
//////
//////	}
//
//
//	@Column(name = "created_by", insertable = true, updatable = false)
//	private Integer createdBy;
//
//	@Column(name = "modified_by", insertable = false, updatable = true)
//	private Integer modifiedBy;
//
//
//
//	@Column(name = "created_on", insertable = true, updatable = false)
//	private LocalDateTime createdOn;
//
//	@Column(name = "modified_on", insertable = false, updatable = true)
//	private LocalDateTime modifiedOn;
//
//	@PrePersist
//	public void prePersist() {
//		LocalDateTime now = LocalDateTime.now();
//		createdOn = now;
//		System.out.println("--------------------------"+(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//	}
//
//
//	@PreUpdate
//	public void preUpdate() {
//		LocalDateTime now = LocalDateTime.now();
//		modifiedOn = now;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public List<AdminQcPointEntity> getQcPoints() {
//		return qcPoints;
//	}
//
//	public void setQcPoints(List<AdminQcPointEntity> qcPoints) {
//		this.qcPoints = qcPoints;
//	}
//
////	public AdminQcPointEntity getParent() {
////		return parent;
////	}
////
////	public void setParent(AdminQcPointEntity parent) {
////		this.parent = parent;
////	}
//
//	public AdminDepartmentEntity getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(AdminDepartmentEntity department) {
//		this.department = department;
//	}
////
////	public Integer getCodingProdType() {
////		return codingProdType;
////	}
////
////	public void setCodingProdType(Integer codingProdType) {
////		this.codingProdType = codingProdType;
////	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public @NotNull Boolean getEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(@NotNull Boolean enabled) {
//		this.enabled = enabled;
//	}
//
//	public String getSubDepartmentId() {
//		return subDepartmentId;
//	}
//
//	public void setSubDepartmentId(String subDepartmentId) {
//		this.subDepartmentId = subDepartmentId;
//	}
//
//	public @NotNull Boolean getDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(@NotNull Boolean deleted) {
//		this.deleted = deleted;
//	}
//
//	public void setParentId(Integer parentId) {
//		this.parentId = parentId;
//	}
//
//	public void setDepartmentId(Integer departmentId) {
//		this.departmentId = departmentId;
//	}
//
//	public Integer getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(Integer createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public Integer getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(Integer modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public LocalDateTime getCreatedOn() {
//		return createdOn;
//	}
//
//	public void setCreatedOn(LocalDateTime createdOn) {
//		this.createdOn = createdOn;
//	}
//
//	public LocalDateTime getModifiedOn() {
//		return modifiedOn;
//	}
//
//	public void setModifiedOn(LocalDateTime modifiedOn) {
//		this.modifiedOn = modifiedOn;
//	}
//}
//
