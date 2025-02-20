package com.idsargus.akpmsadminservice.ExcelDownloadController;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.DoctorDTO;
import com.idsargus.akpmsadminservice.ExcelDownloadservice.DoctorService;
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
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<?> getExcelDownload(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) Boolean deleted
    ) {
        try {
            List<DoctorDTO> excelDownloadResponse = doctorService.getExcelDownload(query, enabled, deleted);


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
