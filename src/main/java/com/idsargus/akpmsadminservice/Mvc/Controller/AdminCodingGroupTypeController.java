package com.idsargus.akpmsadminservice.Mvc.Controller;


import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminCodingGroupTypeService;
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
@RequestMapping("/v1/adminapi/codingProdType")
public class AdminCodingGroupTypeController {

    private final AdminCodingGroupTypeService adminCodingGroupTypeService;

    public AdminCodingGroupTypeController(AdminCodingGroupTypeService adminCodingGroupTypeService) {
        this.adminCodingGroupTypeService = adminCodingGroupTypeService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {

        String sortBy = columnName;
        String direction= sortDirection;
        System.out.println("1");
        if (sortBy.equals("created_by")) {
            sortBy = "createdBy.firstName";
        } else if (sortBy.equals("modified_by")) {
            sortBy = "modifiedBy.firstName";
        }

//        return ResponseEntity.ok("hello");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminCodingGroupTypeService.getAll1(pageable));

    }









    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class) @RequestBody AdminCodingGroupTypeRequestDto dto) {

        // Call the service method to save the email template
        AdminCodingGroupTypeResponseDto response = adminCodingGroupTypeService.add (dto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class) @RequestBody AdminCodingGroupTypeRequestDto adminCodingGroupTypeRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminCodingGroupTypeRequestDto.getName());

            AdminCodingGroupTypeResponseDto responseDto = adminCodingGroupTypeService.update(adminCodingGroupTypeRequestDto, id);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
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
            AdminCodingGroupTypeResponseDto arTeamsResponseDto = adminCodingGroupTypeService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(arTeamsResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }




    //   GET http://localhost:5003/v1/adminapi/codingProdType/search/OnlyEnabled?page=0&size=100
    @GetMapping("/search/OnlyEnabled")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(adminCodingGroupTypeService.findByAllEnabled( ));
    }





    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return adminCodingGroupTypeService.getbyname(name);
    }


}