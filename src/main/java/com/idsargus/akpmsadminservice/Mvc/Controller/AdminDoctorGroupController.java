package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminDoctorGroupService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/doctorgroups")
public class AdminDoctorGroupController {

    private final AdminDoctorGroupService adminDoctorGroupService;

    public AdminDoctorGroupController(AdminDoctorGroupService adminDoctorGroupService) {
        this.adminDoctorGroupService = adminDoctorGroupService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "", required = false) String query,  // Default sort by 'id'
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
        } else if (sortBy.equals("company")) {
            sortBy = "company.name";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminDoctorGroupService.getAll1(query,  enabled, pageable));
    }














    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class)  @RequestBody AdminDoctorGroupRequestDto adminDoctorGroupRequestDto) {

        // Call the service method to save the email template
        DoctorGroupResponse response = adminDoctorGroupService.addgroup(adminDoctorGroupRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }




//     POST http://localhost:5003/v1/adminapi/doctorgroups/add
//{
//    "name": "new_company",
//        "enabled": true,
//        "userTimeZone": "IST",
//        "createdBy": {
//    "id": 1,
//            "name": "John Doe"
//},
//    "company": {
//    "id": 58,
//            "name": "abcd"
//}
//}




    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)  @RequestBody AdminDoctorGroupRequestDto adminDoctorGroupRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminDoctorGroupRequestDto.getName());

            DoctorGroupResponse response = adminDoctorGroupService.updateTemplate(adminDoctorGroupRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }






    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "emailTemplateCache", key = "#id")
    public ResponseEntity<?> updateTemplateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {

         try {
            boolean status = activationStatusRequestDto.isEnabled();

            DoctorGroupResponse  response = adminDoctorGroupService.updateTemplateActivationStatus(id,status);

            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {

             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
//    PATCH  http://localhost:5003/v1/adminapi/doctorgroups/updateActivationStatus/411
//    {
//        "enabled": true
//    }





    @GetMapping("/search/companyGroup")
    public ResponseEntity<?> getcompanyGroup(
            @RequestParam(required = false ) Integer companyId,
            @RequestParam(required = false) Boolean status
    ) {
        return ResponseEntity.ok(adminDoctorGroupService.getcompanyGroup(companyId,  status));
    }










}