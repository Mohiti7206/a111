package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
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


@RestController
@RequestMapping("/v1/adminapi/arteams")
public class AdminArTeamsController {

    private final ArTeamsService arTeamsService;

    public AdminArTeamsController(ArTeamsService arTeamsService) {
        this.arTeamsService = arTeamsService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10000") int size,
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
        return ResponseEntity.ok(arTeamsService.getAll1(pageable));


//        Page doctorGroups = arTeamsService.getAll1(pageable);
//
//        // Construct the pagination response
//        PageResponse pageResponse = new PageResponse();
//        pageResponse.setSize(doctorGroups.getSize());
//        pageResponse.setTotalElements(doctorGroups.getTotalElements());
//        pageResponse.setTotalPages(doctorGroups.getTotalPages());
//        pageResponse.setNumber(doctorGroups.getNumber());
//
//        // Return the response with the custom pagination structure and the content
//        Map<String, Object> response = new HashMap<>();
//        response.put("content", doctorGroups.getContent());
//        response.put("page", pageResponse);
//
//
//        return ResponseEntity.ok(response);

    }

// GET http://localhost:5003/v1/adminapi/arteams/search/all?page=0&size=100&sortBy=name&direction=asc






// POST http://localhost:5003/v1/adminapi/arteams/add
//{
//    "name": "refe ",     // mandatory
//        "enabled":  true,
//        "userTimeZone": "IST",
//        "createdBy": {       // mandatory
//    "id": 1,
//            "name": "John Doe"
//}
//}




    @PostMapping("/add")
    public ResponseEntity<?> addEmailTemplate(
            @Validated(ValidationGroups.Create.class) @RequestBody ArTeamsRequestDto arTeamsRequestDto) {

        // Call the service method to save the email template
        ArTeamsResponseDto response = arTeamsService.addArTeam(arTeamsRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }









// PATCH   http://localhost:5003/v1/adminapi/arteams/update/15
//{
//    "name": "fef",     // mandatory
//        "enabled":  true,
//        "userTimeZone": "IST",
//        "modifiedBy": {       // mandatory
//    "id": 1,
//            "name": "John Doe"
//}
//}
//


    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class) @RequestBody ArTeamsRequestDto arTeamsRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + arTeamsRequestDto.getName());

            ArTeamsResponseDto arTeamsResponseDto = arTeamsService.updateTemplate(arTeamsRequestDto, id);
            return ResponseEntity.ok(arTeamsResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }






//PATCH http://localhost:5003/v1/adminapi/arteams/updateActivationStatus/15
//{
//    "enabled": false
//}


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
            ArTeamsResponseDto arTeamsResponseDto = arTeamsService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(arTeamsResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
















    //   GET http://localhost:5003/v1/adminapi/arteams/search/OnlyEnabled?page=0&size=100
    @GetMapping("/search/OnlyEnabled")
    public ResponseEntity<?> findByAllEnabled(
    ) {
        return ResponseEntity.ok(arTeamsService.findByAllEnabled( ));
    }











}
