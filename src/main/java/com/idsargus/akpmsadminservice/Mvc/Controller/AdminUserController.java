package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/users")
@Tag(name = "AKPMS USER API's")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "desc") String sortDirection, // Default sort direction 'asc'
            @RequestParam(defaultValue = "",required = false) String query , // Default sort by 'id'
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) Boolean deleted
            ) {
        System.out.println("1");
        String sortBy = columnName;
        String direction= sortDirection;



         if (sortBy.equals("email")) {
            sortBy = "email";
        }else if (sortBy.equals("team")) {
            sortBy = "team";
        }

         Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );

        System.out.println("hello 1 ");

        return ResponseEntity.ok(adminUserService.getAll1(enabled,deleted,query,pageable));


    }
















    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "user", key = "#id")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {
        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminUserResponseDto response = adminUserService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }








    @PostMapping("/add")
    public ResponseEntity<?> addUser(
            @Validated(ValidationGroups.Create.class) @RequestBody AdminUserRequestDto adminUserRequestDto) {


        System.out.println("hello1 ");
        // Call the service method to save the email template
        AdminUserResponseDto response = adminUserService.addUser(adminUserRequestDto);
        System.out.println("hello2 ");

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }






    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)   @RequestBody AdminUserRequestDto adminUserRequestDto ,
            @PathVariable Integer id) {

        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + adminUserRequestDto.getName());

            AdminUserResponseDto response = adminUserService.updateuser(adminUserRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



    @PatchMapping("/updateDepartmentsForUser/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateDepartmentsForUser(
            List<Integer>  departmentIds,
            @PathVariable Integer userId) {

        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + adminUserRequestDto.getName());

            AdminUserResponseDto response = adminUserService.updateuserDepartments(departmentIds, userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }













//   PATCH  http://localhost:5003/v1/adminapi/users/update/emailTemplates/100174
//{
//    "emailTemplateIds" : [1,7]
//}
    @PatchMapping("/update/emailTemplates/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateEmailTemplate(
            @RequestBody EmailTemplateUpdateRequestDto emailTemplateUpdateRequestDto ,
            @PathVariable Integer id) {


        System.out.println("777777777777777777777777777777777777777777");

        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + adminUserRequestDto.getName());

            AdminUserResponseDto response = adminUserService.updateUserEmailTemplate(emailTemplateUpdateRequestDto.getEmailTemplateIds(), id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }







// patch http://localhost:5003/v1/adminapi/users/update/departments/100228
//{
//    "departmentIds": [
//    3,6
//    ]
//}


    @PatchMapping("/update/departments/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateDeartments(
            @RequestBody DepartmentUpdateRequestDto departmentUpdateRequestDto ,
            @PathVariable Integer id) {


        System.out.println("777777777777777777777777777777777777777777");

        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + adminUserRequestDto.getName());

            AdminUserResponseDto response = adminUserService.updateUserDepartment(departmentUpdateRequestDto.getDepartmentIds(), id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }










// PATCH http://localhost:5003/v1/adminapi/users/update/permissions/100173
//{
//    "permissionIds": [
//    // "P-3",
//    // "P-13",
//    //  "P-11",
//    "P-14"
//    ]
//}

    @PatchMapping("/update/permissions/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updatePermissions(
            @RequestBody UserPermissionUpdateDto userPermissionUpdateDto ,
            @PathVariable Integer id) {


        System.out.println("777777777777777777777777777777777777777777");

        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + adminUserRequestDto.getName());

            AdminUserResponseDto response = adminUserService.updateUserPermission(userPermissionUpdateDto.getPermissionIds(), id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }














//  POST      'http://localhost:5003/v1/adminapi/users/add' \
//           {
//            "firstName": "a",
//            "lastName": "kumcar22",
//            "email": "hf 323",
//            "password": "12123",
//            "contact": "9999999999",
//            "address": "ynr",
//            "location": "mohali",
//            "userTimeZone": "IST",
//            "enabled": true,
//            "deleted": false,
//            "createdBy": {
//        "id": 1,
//                "name": "John Doe"
//    },
//            "role": {
//        "id": 1,
//                "name": "John Doe"
//    },
//            "arTeam": {
//        "id": 1,
//                "name": "John Doe"
//    }
//
//}'







//    curl -X 'POST' \
//            'http://localhost:5003/v1/adminapi/users/add' \
//            -H 'accept: */*' \
//            -H 'Content-Type: application/json' \
//            -d '
//    {
//        "firstName": "fsa",
//            "lastName": "kfsumcar22",
//            "email": "hf sf323",
//            "password": "12123",
//            "contact": "9999999999",
//            "address": "ynr",
//            "location": "mohali",
//            "userTimeZone": "IST",
//            "enabled": true,
//            "deleted": false,
//            "createdBy": {
//        "id": 1,
//                "name": "John Doe"
//    },
//        "role": {
//        "id": 1,
//                "name": "John Doe"
//    },
//        "arTeam": {
//        "id": 1,
//                "name": "John Doe"
//    }
//
//    }
//'





    // GET http://localhost:5003/v1/adminapi/doctors/search/allEnabled
    @GetMapping("/search/enableall")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(adminUserService.findByAllEnabled());
    }




    //	@PreAuthorize("hasAuthority('role_admin')")
//    @RestResource(path = "enableall", rel = "enableall")
//    @Query("SELECT i FROM User i where  i.enabled= 1 AND i.deleted = 0 ORDER BY i.firstName, i.lastName")
//    @Cacheable
//    public List<User> findEnableAll();






}