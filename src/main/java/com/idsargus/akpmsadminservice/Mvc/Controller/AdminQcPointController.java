package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminQcPointService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/adminapi/qcpoints")
public class AdminQcPointController {

    private final AdminQcPointService adminQcPointService;

    public AdminQcPointController(AdminQcPointService adminQcPointService) {
        this.adminQcPointService = adminQcPointService;
    }

    @GetMapping("/search/withDepartmentAndSubdepartment")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "500000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "", required = false) String query,  // Default sort by 'id'
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {
        System.out.println(columnName+ " columnname");


        String sortBy = columnName;
        String direction= sortDirection;
        size = 500;


        System.out.println("1");
        return ResponseEntity.ok(adminQcPointService.getAllQcPoints(sortBy,direction,page ,size,query, status, departmentId));
    }










    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {

        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminQcPointResponseDto response = adminQcPointService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }









    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class)  @RequestBody AdminQcPointRequestDto adminQcPointRequestDto) {

        // Call the service method to save the email template
        AdminQcPointResponseDto response = adminQcPointService.addQcPoint(adminQcPointRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }




    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)   @RequestBody AdminQcPointRequestDto adminQcPointRequestDto,
            @PathVariable Integer id) {

        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminQcPointRequestDto.getName());

            AdminQcPointResponseDto response = adminQcPointService.updateTemplate(adminQcPointRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }







//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
//    @RestResource(path = "parentonly", rel = "parentonly")
//    @Query("SELECT i FROM AdminQcPointEntity i where i.deleted = 0 and parent = null ORDER BY i.name")
//    @Cacheable(key = "#parent-only")
//    public List<AdminQcPointEntity> findParentForAdmin();




    @GetMapping("/search/parentonly")
    public ResponseEntity<?> getparentonly(
    ) {
        System.out.println("1");
        return ResponseEntity.ok(adminQcPointService.getparentonly());
    }

    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return adminQcPointService.getbyname(name);
    }

}