//package com.idsargus.akpmsadminservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.idsargus.akpmscommonservice.entity.UserEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "location")
//public class AdminLocation {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//	private String name;
//
//	@NotNull
//	@Column(name = "enabled", columnDefinition = "TINYINT(1) DEFAULT '1'")
//	private boolean enabled = true;
//
//	@Column(name = "description", columnDefinition = "TEXT")
//	private String desc;
//
//	@NotNull
//	@Column(name = "is_deleted", columnDefinition = "TINYINT(1) DEFAULT '0'")
//	private boolean deleted = false;
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
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
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
//
//	public String toString() {
//		return "" + this.getId();
//	}
//
//	/**
//	 * @return the desc
//	 */
//	public String getDesc() {
//		return desc;
//	}
//
//	/**
//	 * @param desc
//	 *            the desc to set
//	 */
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//
//	/**
//	 * @return the deleted
//	 */
//	public boolean isDeleted() {
//		return deleted;
//	}
//
//	/**
//	 * @param deleted
//	 *            the deleted to set
//	 */
//	public void setDeleted(boolean deleted) {
//		this.deleted = deleted;
//	}
//
//}