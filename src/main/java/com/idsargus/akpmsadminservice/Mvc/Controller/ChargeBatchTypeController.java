
package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.Service.ChargeBatchTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/adminapi/batchtype")
public class ChargeBatchTypeController {

    private final ChargeBatchTypeService chargeBatchTypeService;

    public ChargeBatchTypeController(ChargeBatchTypeService chargeBatchTypeService) {
        this.chargeBatchTypeService = chargeBatchTypeService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getchargebatchtype(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50000") int size,
            @RequestParam(defaultValue = "id") String columnName,  // Default sort by 'id'
            @RequestParam(defaultValue = "desc") String sortDirection // Default sort direction 'asc'
    ) {


        String sortBy = columnName;
        String direction= sortDirection;







        System.out.println("Fetching charge batch types...");

        // Map sortBy values to entity fields
        switch (sortBy) {
            case "created_by":
                sortBy = "createdBy.firstName";
                break;
            case "modified_by":
                sortBy = "modifiedBy.firstName";
                break;
            default:
                sortBy = "id"; // fallback to default
        }

        // Validate direction
        Sort.Direction sortDirection1 = Sort.Direction.fromString(direction);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection1, sortBy));

        Page<ChargeBatchTyperesponseDto> result = chargeBatchTypeService.getAll1(pageable);
        return ResponseEntity.ok(result);





    }

//}








//

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @Validated(ValidationGroups.Create.class) @RequestBody ChargeBatchRequestDto requestDto) {
        ChargeBatchTyperesponseDto response = chargeBatchTypeService.add(requestDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class) @RequestBody ChargeBatchRequestDto requestDto, @PathVariable Integer id) {
        ChargeBatchTyperesponseDto response = chargeBatchTypeService.update(requestDto,id);
        return ResponseEntity.ok(response);
    }

//
//
//    @PatchMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
////    @CachePut(value = "arteams", key = "#id")
//    public ResponseEntity<?> update(
//          @Valid @RequestBody ChargeBatchRequestDto chargeBatchRequestDto,
//            @PathVariable Integer id) {
//
//
//        try {
//            System.out.println("id: " + id);
//            System.out.println("name: " + chargeBatchRequestDto.getName());
//
//            ChargeBatchTyperesponseDto chargeBatchTyperesponseDto = chargeBatchTypeService.update(chargeBatchRequestDto, id);
//            return ResponseEntity.ok(chargeBatchTyperesponseDto);
//        } catch (RuntimeException ex) {
//            // Return only the message to the front-end
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//        }
//    }

//
    @PatchMapping("/updateActivationStatus/{id}")
//    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {

        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            ChargeBatchTyperesponseDto chargeBatchTyperesponseDto = chargeBatchTypeService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(chargeBatchTyperesponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return chargeBatchTypeService.getbyname(name);
    }
}
