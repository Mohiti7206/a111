package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.HourlyTaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.cache.annotation.CachePut;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/adminapi/hourlytaskname")
public class AdminHourlyTaskController {

    private final HourlyTaskService hourlyTaskService;

    public AdminHourlyTaskController(HourlyTaskService hourlyTaskService) {
        this.hourlyTaskService = hourlyTaskService;
    }


// GET  http://localhost:5003/v1/adminapi/hourlytaskname/search/all
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
        return ResponseEntity.ok(hourlyTaskService.getAll1(pageable));



    }







// POST http://localhost:5003/v1/adminapi/hourlytaskname/add
//{
//    "chargeable": "true",   // mandatory
//        "deleted": false,
//        "enabled": true,
//        "description": "ter",
//        "name": "ww",            // mandatory
//        "userTimeZone": "IST",
//        "department": {     // mandatory
//    "id" : 1
//},
//    "createdBy": {
//    "id" : 847
//}
//}
    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class) @RequestBody HourlyTaskRequestDto hourlyTaskRequestDto) {

        // Call the service method to save the email template
        HourlyTaskResponseDto response = hourlyTaskService.add(hourlyTaskRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
//    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)  @RequestBody HourlyTaskRequestDto hourlyTaskRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + hourlyTaskRequestDto.getName());

            HourlyTaskResponseDto hourlyTaskResponseDto = hourlyTaskService.update(hourlyTaskRequestDto, id);
            return ResponseEntity.ok(hourlyTaskResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
//
//
    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
//    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {


        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            HourlyTaskResponseDto response = hourlyTaskService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return hourlyTaskService.getbyname(name);
    }
}
