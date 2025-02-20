package com.idsargus.akpmsadminservice.ExcelDownloadController;

 import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminDoctorGroupDTO;
 import com.idsargus.akpmsadminservice.ExcelDownloadservice.DoctorGroupService;
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
@RequestMapping("/api")
@Slf4j
public class DoctorGroupController {

    @Autowired
    private DoctorGroupService doctorGroupService;

    @GetMapping("/group")
    public ResponseEntity<?> getExcelDownload(
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "enabled", required = false) Boolean enabled) {
        try {
            List<AdminDoctorGroupDTO> excelDownloadResponse = doctorGroupService.fetchExcelDownload(query, enabled);

            System.out.println("in the DoctorGroupController 1");






            if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
                return ResponseEntity.noContent().build();  // Return 204 No Content if the list is empty
            }

            System.out.println("in the DoctorGroupController 1");

            return ResponseEntity.ok(excelDownloadResponse);  // Return 200 OK with the list if records are found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching the records: " + e.getMessage());  // Return 500 with error message
        }
    }
}
