package com.idsargus.akpmsadminservice.Mvc.Controller;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceResponseDto;
import com.idsargus.akpmsadminservice.Mvc.Service.MoneySourceService;
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
@RequestMapping("/v1/adminapi/moneysource")
public class MoneySourceController {




    private final MoneySourceService moneySourceService;

    public MoneySourceController(MoneySourceService moneySourceService) {
        this.moneySourceService = moneySourceService;
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
            case "createdBy":
                sortBy = "createdBy.firstName";
                break;
            case "modifiedBy":
                sortBy = "modifiedBy.firstName";
                break;
            case "name":
                sortBy = "name";
            case "enabled":
                sortBy = "enabled";
                break;
            default:
                sortBy = "id"; // fallback to default
        }

        // Validate direction
        Sort.Direction sortDirection1 = Sort.Direction.fromString(direction);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection1, sortBy));

        Page<MoneySourceResponseDto> result = moneySourceService.getAll1(pageable);
        return ResponseEntity.ok(result);



}




//GET http://localhost:5003/v1/adminapi/doctorgroups/search/all?page&size=500













    @PostMapping("/add")
    public ResponseEntity<?> add(
            @Validated(ValidationGroups.Create.class) @RequestBody MoneySourceRequestDto moneySourceRequestDto) {

        // Call the service method to save the email template
        MoneySourceResponseDto response = moneySourceService.add(moneySourceRequestDto);

        // Return the saved template in the response
        return ResponseEntity.ok(response);
    }










    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
//    @CachePut(value = "arteams", key = "#id")
    public ResponseEntity<?> update(
            @Validated(ValidationGroups.Update.class)  @RequestBody MoneySourceRequestDto moneySourceRequestDto,
            @PathVariable Integer id) {


        try {
            System.out.println("id: " + id);
            System.out.println("name: " + moneySourceRequestDto.getName());

            MoneySourceResponseDto moneySourceResponseDto = moneySourceService.update(moneySourceRequestDto, id);
            return ResponseEntity.ok(moneySourceResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



// PATCH   http://localhost:5003/v1/adminapi/doctorgroups/update/412
//
//    {
//        "name": "0",
//            "enabled": "false",
//            "userTimeZone": "IST",
//            "company": {
//        "id": 2,
//                "name": "mohit compan2y"
//    },
//        "modifiedBy": {
//        "id": 2, // Replace 123 with the actual user ID
//                "firstName": "John" // Optional: Additional fields, if needed
//    }
//    }





    @PatchMapping("/updateActivationStatus/{id}")
    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    public ResponseEntity<?> updateActivationStatus(
            @RequestBody ActivationStatusRequestDto activationStatusRequestDto,
            @PathVariable Integer id) {

        try {
//            System.out.println("id: " + id);
            boolean status = activationStatusRequestDto.isEnabled();
//            System.out.println("status = "+status);
            MoneySourceResponseDto moneySourceResponseDto = moneySourceService.updateTemplateActivationStatus(id,status);
            return ResponseEntity.ok(moneySourceResponseDto);
        } catch (RuntimeException ex) {
            // Return only the message to the front-end
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/search/getbyname")
    public Boolean getEmailTemplates(@RequestParam DuplicateNameCheckExistsDto name
    ) {
        return moneySourceService.getbyname(name);
    }


}


























///*----------------------------------------------------------------------------------------------------------------------------------------

/*


POST http://localhost:5003/v1/adminapi/moneysource/add
{
  "name": "t9ww3",
  "enabled": true,
  "deleted": false,
    "createdBy": {
    "id": 847, // Replace 123 with the actual user ID
    "name": "John Doe" // Optional: Additional fields, if needed
  }
}


GET http://localhost:5003/v1/adminapi/moneysource/search/all



PATCH http://localhost:5003/v1/adminapi/moneysource/update/18
{
    "name": "00 ",
    "enabled": false,
    "description": "0000",
    "deleted": false,
    "userTimeZone": "IST",
      "modifiedBy": {
    "id": 100009, // Replace 123 with the actual user ID
    "firstName": "John" // Optional: Additional fields, if needed
  }
}




PATCH http://localhost:5003/v1/adminapi/moneysource/updateActivationStatus/18
{
    "enabled": true
}
 */
