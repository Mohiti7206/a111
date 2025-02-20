package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminReveneueTypeService;
import com.idsargus.akpmsadminservice.Mvc.Service.ArTeamsService;
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

//public class AdminReveneueTypeController {
//}
@RestController
@RequestMapping("/v1/adminapi/revenuetype")
public class AdminReveneueTypeController {

    private final AdminReveneueTypeService adminReveneueTypeService;

    public AdminReveneueTypeController(AdminReveneueTypeService adminReveneueTypeService) {
        this.adminReveneueTypeService = adminReveneueTypeService;
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
        return ResponseEntity.ok(adminReveneueTypeService.getAll1(pageable));


    }





//
//    @PostMapping("/add")
//    public ResponseEntity<?> add(
//            @RequestBody AdminRevenueTypeRequestDto adminRevenueTypeRequestDto) {
//
//        // Call the service method to save the email template
//        AdminRevenueTypeMvcDto response = adminReveneueTypeService.add(adminRevenueTypeRequestDto);
//
//        // Return the saved template in the response
//        return ResponseEntity.ok(response);
//    }
//
//




    @PostMapping("/add")
    public ResponseEntity<AdminRevenueTypeMvcResponseDto> add(  @Validated(ValidationGroups.Create.class) @RequestBody AdminRevenueTypeRequestDto dto) {
        // Call the service to add the new revenue type
        AdminRevenueTypeMvcResponseDto savedDto = adminReveneueTypeService.add(dto);

        // Return the saved DTO with a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
//        return ResponseEntity.ok(savedDto);

    }




    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)
            @RequestBody AdminRevenueTypeRequestDto adminRevenueTypeRequestDto,
            @PathVariable Integer id) {


        System.out.println(adminRevenueTypeRequestDto.getModifiedBy().getId() + "modifiedby id");
        System.out.println(adminRevenueTypeRequestDto.getModifiedBy().getFirstName() + "modifiedby name");
        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminRevenueTypeRequestDto.getName());

            AdminRevenueTypeMvcResponseDto adminRevenueTypeMvcResponseDto = adminReveneueTypeService.updateTemplate(adminRevenueTypeRequestDto, id);
            return ResponseEntity.ok(adminRevenueTypeMvcResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


















    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            AdminRevenueTypeMvcResponseDto response = adminReveneueTypeService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }























}

