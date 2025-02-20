package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import java.util.Set;

public class DepartmentUpdateRequestDto {

    Set<Integer> DepartmentIds;

    public Set<Integer> getDepartmentIds() {
        return DepartmentIds;
    }

    public void setDepartmentIds(Set<Integer> departmentIds) {
        DepartmentIds = departmentIds;
    }
}
