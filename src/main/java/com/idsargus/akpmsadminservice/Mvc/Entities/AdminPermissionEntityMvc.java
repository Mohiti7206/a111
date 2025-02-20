package com.idsargus.akpmsadminservice.Mvc.Entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "permission")
public class AdminPermissionEntityMvc {

	@Id
	protected String id;

	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;
	
	

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "permission_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<AdminUserMvc> users;



	@Override
	public String toString() {
		return getId().toString();
	}

}
