package com.idsargus.akpmsadminservice.Mvc.Controller;

//public class AdminInsuranceController {
//}

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.ArTeamsService;
import com.idsargus.akpmsadminservice.Mvc.Service.InsuranceService;
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
@RequestMapping("/v1/adminapi/insurances")
public class AdminInsuranceController {

    private final InsuranceService insuranceService;

    public AdminInsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100000") int size,
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
        return ResponseEntity.ok(insuranceService.getAll1(pageable));

    }









//

    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class)    @RequestBody InsuranceRequestDto insuranceRequestDto) {

        // Call the service method to save the email template
        InsuranceResponseDto response = insuranceService.add(insuranceRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "insurance", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)   @RequestBody InsuranceRequestDto insuranceRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + insuranceRequestDto.getName());

            InsuranceResponseDto response = insuranceService.update(insuranceRequestDto, id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            InsuranceResponseDto response = insuranceService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }




    // GET http://localhost:5003/v1/adminapi/doctors/search/allEnabled
    @GetMapping("/search/allEnabled")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(insuranceService.findByAllEnabled( ));
    }












}
