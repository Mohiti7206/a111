package com.idsargus.akpmsadminservice.Mvc.Controller;


import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminDoctorCompanyService;
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
@RequestMapping("/v1/adminapi/doctorcompanies")
public class AdminDoctorCompanyController {

    private final AdminDoctorCompanyService adminDoctorCompanyService;

    public AdminDoctorCompanyController(AdminDoctorCompanyService adminDoctorCompanyService) {
        this.adminDoctorCompanyService = adminDoctorCompanyService;
    }

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
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminDoctorCompanyService.getAll1(query,pageable,enabled));



    }









    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)    @RequestBody AdminDoctorCompanyRequestDto adminDoctorCompanyRequestDto ,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminDoctorCompanyRequestDto.getName());

            AdminCompanyResponseDto response = adminDoctorCompanyService.updateTemplate(adminDoctorCompanyRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


//PATCH http://localhost:5003/v1/adminapi/doctorcompanies/update/2
//    {
//        "name": "0",
//            "enabled": false,
//            "userTimeZone": "IST",
//            "modifiedBy": {
//        "id": 1, // Replace 123 with the actual user ID
//                "firstName": "John" // Optional: Additional fields, if needed
//    }
//    }





    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(

            @Validated(ValidationGroups.Create.class)   @RequestBody AdminDoctorCompanyRequestDto adminDoctorCompanyRequestDto) {

        // Call the service method to save the email template
        AdminCompanyResponseDto  response = adminDoctorCompanyService.addCompany(adminDoctorCompanyRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }


//  POST  http://localhost:5003/v1/adminapi/doctorcompanies/add
//    {
//        "name": "sjd",
//            "enabled": true,
//            "userTimeZone": "IST",
//            "createdBy": {
//        "id": 1,
//                "name": "John Doe"
//    }
//    }









// PATCH http://localhost:5003/v1/adminapi/doctorcompanies/updateActivationStatus/126
//{
//    "enabled": false
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
            AdminCompanyResponseDto response = adminDoctorCompanyService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }















// GET http://localhost:5003/v1/adminapi/doctorcompanies/search/allCompanies?status=1

    @GetMapping("/search/allCompanies")
    public ResponseEntity<?> getdoctorcompanies(
            @RequestParam(required = false) Boolean status
     ) {
        return ResponseEntity.ok(adminDoctorCompanyService.getallCompanies( status));
    }







    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return adminDoctorCompanyService.getbyname(name);
    }






}
