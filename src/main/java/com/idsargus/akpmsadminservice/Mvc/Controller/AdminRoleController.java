package com.idsargus.akpmsadminservice.Mvc.Controller;

//public class AdminRoleController {
//}



import com.idsargus.akpmsadminservice.Mvc.Service.AdminRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/adminapi/roles")
public class AdminRoleController {

    private final AdminRoleService adminRoleService;

    public AdminRoleController(AdminRoleService adminRoleService) {
        this.adminRoleService = adminRoleService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
    ) {
        return ResponseEntity.ok(adminRoleService.findByAllEnabled( ));
    }
}

