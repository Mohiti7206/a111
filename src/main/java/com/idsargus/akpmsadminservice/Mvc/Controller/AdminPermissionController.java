package com.idsargus.akpmsadminservice.Mvc.Controller;

//public class AdminPermissionController {
//}

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ActivationStatusRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminPermissionsService;
import com.idsargus.akpmsadminservice.Mvc.Service.ArTeamsService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/adminapi/permissions")
public class AdminPermissionController {

    private final AdminPermissionsService adminPermissionsService;

    public AdminPermissionController(AdminPermissionsService adminPermissionsService) {
        this.adminPermissionsService = adminPermissionsService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(adminPermissionsService.getAll1( ));
    }

}
