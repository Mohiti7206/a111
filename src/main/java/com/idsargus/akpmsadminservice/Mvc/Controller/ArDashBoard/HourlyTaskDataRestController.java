package com.idsargus.akpmsadminservice.Mvc.Controller.ArDashBoard;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.ArTeamsService;
import com.idsargus.akpmsadminservice.Mvc.Service.HourlyTaskDataRestService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/hourlytasks")
public class  HourlyTaskDataRestController {

    private final HourlyTaskDataRestService hourlyTaskDataRestService;

    public HourlyTaskDataRestController(HourlyTaskDataRestService hourlyTaskDataRestService) {
        this.hourlyTaskDataRestService = hourlyTaskDataRestService;
    }
    @GetMapping("/search/all")
    public ResponseEntity<?> getEmailTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String columnName,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTo,
            @RequestParam(required = false) Integer name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date taskCompletedFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date taskCompletedTo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateReceivedFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateReceivedTo,
            @RequestParam(required = false) Integer createdBy
    ) {
        // Resolve sorting column alias
        if (columnName.equals("created_by")) {
            columnName = "createdBy.firstName";
        } else if (columnName.equals("modified_by")) {
            columnName = "modifiedBy.firstName";
        } else if (columnName.equals("taskName")) {
            columnName = "hourlyTask.name";
        }

        Sort sort = Sort.by(Sort.Order.by(columnName).with(Sort.Direction.fromString(sortDirection)));
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<HouryTaskdataRestResponseDto> result = hourlyTaskDataRestService.getAll1(
                name,
                createdFrom,
                createdTo,
                taskCompletedFrom,
                taskCompletedTo,
                dateReceivedFrom,
                dateReceivedTo,
                createdBy,
                pageable
        );

        return ResponseEntity.ok(result);
    }






//
//    @PostMapping("/add")
//    public ResponseEntity<?> addEmailTemplate(
//            @Validated(ValidationGroups.Create.class) @RequestBody ArTeamsRequestDto arTeamsRequestDto) {
//
//        // Call the service method to save the email template
//        HouryTaskdataRestResponseDto response = hourlyTaskDataRestService.add(arTeamsRequestDto);
//
//        // Return the saved template in the response
//        return ResponseEntity.ok(response);
//    }
































}
