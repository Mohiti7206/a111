package com.idsargus.akpmsadminservice.Mvc.Repository;

 import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRoleEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminRoleResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoleDataRestRepository extends JpaRepository<AdminRoleEntityMvc, Integer>, CrudRepository<AdminRoleEntityMvc,Integer> {


//	public static final String MODULE_NAME = "roles";

//	@Override
//	@PreAuthorize("hasAuthority('role_admin') Or hasAuthority('role_user')")
//	@RestResource(path = "all", rel = "all")
	@Query("SELECT i FROM AdminRoleEntityMvc i")
	@Cacheable
	public List<AdminRoleResponse> findAll1();



}
