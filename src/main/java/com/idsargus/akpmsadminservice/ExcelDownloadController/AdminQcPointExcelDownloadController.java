package com.idsargus.akpmsadminservice.ExcelDownloadController;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminQcPointDTO;
import com.idsargus.akpmsadminservice.ExcelDownloadservice.AdminQcPointService;
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
public class AdminQcPointExcelDownloadController {

    @Autowired
    private AdminQcPointService adminQcPointService;

    @GetMapping("/qc-points")
    public ResponseEntity<?> getExcelDownload(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Boolean status

            ) {
        if(query == null || query.isEmpty()){
            query=null;
        }
        try {


            System.out.println(query+"query==================================================");
            System.out.println(departmentId+"departmentId==============================================");
            System.out.println(status+"status====================================================================");

//            System.out.println(status+ " ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//            List<AdminQcPointDTO> excelDownloadResponse = adminQcPointService.getExcelDownload(query,deleted, status );
            List<AdminQcPointDTO> excelDownloadResponse = adminQcPointService.getExcelDownload(query,departmentId,status);

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
