package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.AdminLocationService;
import com.idsargus.akpmsadminservice.Mvc.Service.ArTeamsService;
import com.idsargus.akpmsadminservice.Mvc.Service.ChargeBatchTypeService;
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
@RequestMapping("/v1/adminapi/locations")
public class AdminLocationController {


    private final AdminLocationService adminLocationService;

    public AdminLocationController(AdminLocationService adminLocationService) {
        this.adminLocationService = adminLocationService;
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
        else if (sortBy.equals("desc")) {
            sortBy = "desc";
        }

//        return ResponseEntity.ok("hello");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminLocationService.getAll1(pageable));




    }







    @PostMapping("/add")
    public ResponseEntity<?> add(
            @Validated(ValidationGroups.Create.class)   @RequestBody AdminLocationRequestDto adminLocationRequestDto) {

        // Call the service method to save the email template
        LocationResponseDto response = adminLocationService.add(adminLocationRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }




    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)   @RequestBody AdminLocationRequestDto adminLocationRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + adminLocationRequestDto.getName());

            LocationResponseDto response = adminLocationService.updateTemplate(adminLocationRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }




    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            LocationResponseDto response = adminLocationService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }




//  GET  http://localhost:5003/v1/adminapi/locations/search/all?sortBy=desc&direction=desc


    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return adminLocationService.getbyname(name);
    }


}
