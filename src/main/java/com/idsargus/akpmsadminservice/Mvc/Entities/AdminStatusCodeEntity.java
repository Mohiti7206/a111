package com.idsargus.akpmsadminservice.Mvc.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ar_status_code")
public class AdminStatusCodeEntity {

	@Id
	protected String id;

	private String name;

	@Override
	public String toString() {
		return getId().toString();
	}

}
