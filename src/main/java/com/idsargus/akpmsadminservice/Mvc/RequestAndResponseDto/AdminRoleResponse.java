package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRoleEntityMvc;

public class AdminRoleResponse {

    private Integer id;
    private String name;


    public AdminRoleResponse(AdminRoleEntityMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
     }

    public AdminRoleResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
