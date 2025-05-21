package com.idsargus.akpmsadminservice.Mvc.Controller;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminDepartmentService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/departments")
public class AdminDepartmentController {

    private final AdminDepartmentService adminDepartmentService;

    public AdminDepartmentController(AdminDepartmentService adminDepartmentService) {
        this.adminDepartmentService = adminDepartmentService;
    }



//   GET  http://localhost:5003/v1/adminapi/departments/search/all?sortBy=parent&direction=desc

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "",required = false) String query,  // Default sort by 'id'
            @RequestParam(required = false) Boolean enabled,
             @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {



        String sortBy = columnName;
        String direction= sortDirection;







        System.out.println("1");
        if (sortBy.equals("createdBy")) {
            sortBy = "createdBy.firstName";
        } else if (sortBy.equals("modifiedBy")) {
            sortBy = "modifiedBy.firstName";
        } else if (sortBy.equals("name")) {
            sortBy = "name";
        }else if (sortBy.equals("parent")) {
            sortBy = "parent.name";
        }else if (sortBy.equals("status")) {
            sortBy = "enabled";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminDepartmentService.getAll1(query,pageable,enabled));




    }









    //
    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)
            @RequestBody AdminDepartmentRequestDto adminDepartmentRequestDto ,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminDepartmentRequestDto.getName());

            AdminDepartmentResponseDto response = adminDepartmentService.updateTemplate(adminDepartmentRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

//
//
//
//
//
//
//

//    //PATCH http://localhost:5003/v1/adminapi/departments/update/1
//    {
//        "name": "1",
//            "enabled": "false",
//            "userTimeZone": "IST",
//            "description": "111",
//            "deleted": true,
//            "parent": {           // when it should not set to  null
//               "id": 2,
//                "name": "mohit compan2y"
//              },
//        // "parent": null,   // when it should set null
//        "modifiedBy": {
//        "id": 847, // Replace 123 with the actual user ID
//                "firstName": "John" // Optional: Additional fields, if needed
//    }
//    }
//





    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class)   @RequestBody AdminDepartmentRequestDto adminDepartmentRequestDto) {

        // Call the service method to save the email template
        AdminDepartmentResponseDto response = adminDepartmentService.addDepartment(adminDepartmentRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }








// POST  http://localhost:5003/v1/adminapi/departments/add
//{
//    "name": "monkey",
//        "description": "urywiu",
//        "enabled": true,
//        "deleted": false,
//        "userTimeZone": "IST",
//        "parent": {
//          "id": 12,
//            "name": "abcd"
//},
//    "createdBy": {
//    "id": 1,
//            "name": "John Doe"
//}
//}


















    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "emailTemplateCache", key = "#id")
    public ResponseEntity<?> updateTemplateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminDepartmentResponseDto  response = adminDepartmentService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

//   PATCH  http://localhost:5003/v1/adminapi/departments/updateActivationStatus/27
//{
//    "enabled": true
//}




























//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @GetMapping("/search/parentonly")
    public ResponseEntity<?> getparentonly(
    ) {
        return ResponseEntity.ok(adminDepartmentService.getparentonly());
    }







//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @GetMapping("/search/subdeptonly")
    public ResponseEntity<?> getsubdeptonly(
    ) {
        return ResponseEntity.ok(adminDepartmentService.getsubdeptonly());
    }









//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @GetMapping("/search/subdeptbydept")
    public ResponseEntity<?> getsubdeptbydept(
            @RequestParam(required = false) Integer deptId
    ) {
        return ResponseEntity.ok(adminDepartmentService.getsubdeptbydept(deptId));
    }



//    @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
    @GetMapping("/search/name")
    public ResponseEntity<?> getname(
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(adminDepartmentService.findByNameForAdmin(name));
    }







    @GetMapping("/search/alldepartments")
    public ResponseEntity<?> getalldepartments(
    ) {
        return ResponseEntity.ok(adminDepartmentService.getalldepartments());
    }
    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return adminDepartmentService.getbyname(name);
    }

}
