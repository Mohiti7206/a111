package com.idsargus.akpmsadminservice.ExcelDownloadController;


 import com.idsargus.akpmsadminservice.ExcelDownloadDto.UserDTO;
 import com.idsargus.akpmsadminservice.ExcelDownloadservice.UserService;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class UserExcelDownloadController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getExcelDownload(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "role", required = false) Integer role,
            @RequestParam(value = "departmentIds", required = false) List<Integer> departmentIds,
            @RequestParam(value = "deleted", required = false) Boolean deleted
    ) {
        try {
            List<UserDTO> excelDownloadResponse = userService.getExcelDownload(query, enabled, role, departmentIds, deleted);

            if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
                return ResponseEntity.noContent().build();  // Return 204 No Content if the list is empty
            }
            return ResponseEntity.ok(excelDownloadResponse);  // Return 200 OK with the list if records are found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching the records: " + e.getMessage());  // Return 500 with error message
        }
    }
}
