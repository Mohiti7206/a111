package com.idsargus.akpmsadminservice.Mvc.Controller;


 import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
 import com.idsargus.akpmsadminservice.Mvc.Service.AdminPatientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
 import org.springframework.validation.annotation.Validated;
 import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/adminapi/patient")
public class PatientController {

    private final AdminPatientService adminPatientService;

    public PatientController(AdminPatientService adminPatientService) {
        this.adminPatientService = adminPatientService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {


        String sortBy = columnName;
        String direction = sortDirection;
        System.out.println("1");
        if (sortBy.equals("created_by")) {
            sortBy = "createdBy.firstName";
        } else if (sortBy.equals("modified_by")) {
            sortBy = "modifiedBy.firstName";
        }else if (sortBy.equals("name")) {
            sortBy = "name";
        }

//        return ResponseEntity.ok("hello");
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(direction)))
        );
        return ResponseEntity.ok(adminPatientService.getAll1(pageable));

    }










    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class) @RequestBody PatientRequestDto patientRequestDto) {

        // Call the service method to save the email template
        PatientResponseDto response = adminPatientService.add(patientRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }


















}