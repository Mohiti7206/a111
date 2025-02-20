package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import java.util.Set;

//public class UserPermissionUpdateDto {
//}
import java.util.List;
import java.util.Set;

public class UserPermissionUpdateDto {

    Set<String> permissionIds;

    public Set<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Set<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
