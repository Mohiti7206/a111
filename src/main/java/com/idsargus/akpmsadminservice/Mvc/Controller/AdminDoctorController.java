package com.idsargus.akpmsadminservice.Mvc.Controller;


import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminDoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/doctors")
@Tag(name = "AKPMS Doctor API's")
public class AdminDoctorController {

    private final AdminDoctorService adminDoctorService;

    public AdminDoctorController(AdminDoctorService adminDoctorService) {
        this.adminDoctorService = adminDoctorService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "",required = false) String query,  // Default sort by 'id'
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) Boolean deleted,
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
        }else if (sortBy.equals("companyName")) {
            sortBy = "company.name";
        }else if (sortBy.equals("groupName")) {
            sortBy = "group.name";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminDoctorService.getAll1(query,pageable,enabled,deleted));



    }









    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)   @RequestBody DoctorRequestDTO doctorRequestDTO ,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + doctorRequestDTO.getName());

            AdminDoctorResponse response = adminDoctorService.updateTemplate(doctorRequestDTO, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


// PATCH http://localhost:5003/v1/adminapi/doctors/update/2736
//    {
//        "name": " Adrienne  Tran MD",
//            "companyId": 2,
//            "code": "12 ",
//            "accounting": 0,
//            "operations": 0,
//            "payments": 0.01,
//            "nonDeposit": false,
//            "userTimeZone": "IST",
//            "enabled": false,
//            "deleted": false,
//            "modifiedBy": {
//        "id": 1,
//                "name": "John Doe"
//    },
//        //     "group": null,
//        "group": {
//        "id": 1,
//                "name": "abc"
//    },
//        "company": {
//        "id": 58,
//                "name": "abcd"
//    }
//    }








    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class) @RequestBody DoctorRequestDTO doctorRequestDTO) {

        // Call the service method to save the email template
        AdminDoctorResponse  response = adminDoctorService.adddoctor(doctorRequestDTO);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }


// POST http://localhost:5003/v1/adminapi/doctors/add
//    {
//        "name": "Af12",
//            "companyId": 1,
//            "code": "1",
//            "accounting": "1",
//            "operations": "1",
//            "payments": "1",
//            "nonDeposit": true,
//            "userTimeZone": "IST",
//            "enabled": true,
//            "status": true,
//            "deleted": false,
//            "createdBy": {
//        "id": 1,
//                "name": "John Doe"
//    },
//        "group": {
//        "id": 1,
//                "name": "abc"
//    },
//        "company": {
//        "id": 58,
//                "name": "abcd"
//    },
//        "parent":null
//    }






    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "emailTemplateCache", key = "#id")
    public ResponseEntity<?> updateTemplateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminDoctorResponse  response = adminDoctorService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

// PATCH http://localhost:5003/v1/adminapi/doctors/updateActivationStatus/2736
//    {
//        "enabled": true
//    }






// GET http://localhost:5003/v1/adminapi/doctors/search/allEnabled
    @GetMapping("/search/allEnabled")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(adminDoctorService.findByAllEnabled( ));
    }



  // GET   http://localhost:5003/v1/adminapi/doctors/search/findCompanyByDoctorId/2623
    @GetMapping("/search/findCompanyByDoctorId/{doctorId}")
    public ResponseEntity<?> findCompanyByDoctorId(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(adminDoctorService.findCompanyByDoctorId(doctorId));
    }


    // GET   http://localhost:5003/v1/adminapi/doctors/search/findGroupByDoctorId/2623
    @GetMapping("/search/findGroupByDoctorId/{doctorId}")
    public ResponseEntity<?> findGroupByDoctorId(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(adminDoctorService.findGroupByDoctorId(doctorId));
    }




    // GET   http://localhost:5003/v1/adminapi/doctors/search/findGroupByDoctorId/2623
    @GetMapping("/search/findByCompanygroup")
    public ResponseEntity<?> findByCompanyAndGroup(
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer companyId) {
        return ResponseEntity.ok(adminDoctorService.findByCompanyAndGroup(groupId,companyId));
    }



//    @RestResource(path = "findByCompanygroup", rel = "findByCompanygroup")
//    @Query(value = "SELECT * FROM doctor i where (:groupId is null or i.group_id = :groupId) AND (:companyId is null or i.company_id = :companyId) AND ( i.status = 1)", nativeQuery = true)
//    public List<AdminDoctorEntity> findByCompanyAndGroup(@Param("groupId") Integer groupId,
//                                                         @Param("companyId") Integer companyId);









}
