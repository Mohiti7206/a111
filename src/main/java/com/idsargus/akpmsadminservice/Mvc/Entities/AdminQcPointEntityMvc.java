
package com.idsargus.akpmsadminservice.Mvc.Entities;

//import com.idsargus.akpmsadminservice.entity.AdminBaseAuditableEntity;
//import com.idsargus.akpmsadminservice.entity.AdminCodingProdTypeEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "qc_point")
public class AdminQcPointEntityMvc extends AdminBaseAuditableEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	@OneToMany(targetEntity = AdminQcPointEntityMvc.class, mappedBy = "parent", fetch = FetchType.LAZY)
//	private List<AdminQcPointEntityMvc> qcPoints = null;
	private List<AdminQcPointEntityMvc> child = null;

	@Column(name="coding_prod_type_id",insertable =  true, updatable = true)
	private Integer codingProdType;


//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "coding_prod_type_id", referencedColumnName = "id", nullable = true, unique = false,insertable =  false, updatable = false)
//	private AdminCodingProdTypeEntity codingProdTypes;


	@Column(name = "department_id",insertable =  true, updatable = true)
	private Integer departmentId;

	@Column(name = "parent_id", insertable =  true, updatable = true)
	private Integer parentId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "parent_id", referencedColumnName = "id", unique = false, nullable = true, insertable = false,updatable = false) })
	private AdminQcPointEntityMvc parent = null;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, unique = false,insertable = false,updatable = false)})
	private AdminDepartmentEntityMvc department;

	@Setter
    @Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@NotNull
	@Column(name = "enabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean enabled = true;

	@Column(name = "sub_department_id")
	private String subDepartmentId;

	@NotNull
	@Column(name = "deleted", columnDefinition = "boolean default false", nullable = false)
	private Boolean deleted = false;

}

