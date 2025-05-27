package com.idsargus.akpmsadminservice.ExcelDownloadController;


//import com.idsargus.akpmsarservice.ExcelDownloadDto.ArHourlyTaskEntityDto;

//import com.idsargus.akpmsarservice.ExcelDownloadDto.HourlyTasksArDto;
//import com.idsargus.akpmsarservice.ExcelDownloadService.HourlyTaskArService;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.HourlyTasksArDto;
import com.idsargus.akpmsadminservice.ExcelDownloadservice.HourlyTaskArService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HourlyTaskDataRestControllerExcelDownload {

    @Autowired
    private HourlyTaskArService hourlyTaskArService;

    @GetMapping("/hourly-productivity")
    public ResponseEntity<?> getExcelDownload(
//            @RequestParam(value = "createdBy", required = false) Integer createdBy,
//            @RequestParam(value = "name", required = false)  Integer id,
//            @RequestParam(value = "createdFrom", required = false)  Date createdFrom,
//            @RequestParam(value = "createdTo", required = false)  Date createdTo

            @RequestParam(value = "name", required = false) Integer id,
            @RequestParam(value = "createdFrom", required = false) Date createdFrom,
            @RequestParam(value = "createdTo", required = false) Date createdTo,
            @RequestParam(value = "taskCompletedFrom", required = false) Date taskCompletedFrom,
            @RequestParam(value = "taskCompletedTo", required = false) Date taskCompletedTo,
            @RequestParam(value = "dateReceivedFrom", required = false) Date dateReceivedFrom,
            @RequestParam(value = "dateReceivedTo", required = false) Date dateReceivedTo,
            @RequestParam(value = "createdBy", required = false) Integer createdBy

    ) {


        System.out.println(createdFrom+"createdFrom=============================____________________________+++++++++++++++++++++++");
        System.out.println(createdTo+"createdTo=============================++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(createdBy+"createdBy=============================++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        try {

            List<HourlyTasksArDto> excelDownloadResponse = hourlyTaskArService.getExcelDownload(id,createdBy,createdFrom,createdTo,taskCompletedFrom,taskCompletedTo,dateReceivedFrom,dateReceivedTo);

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





































//package com.idsargus.akpmsarservice.ExcelDownLoadController;
//
//import com.idsargus.akpmsarservice.ExcelDownloadDto.HourlyTasksArDto;
//import com.idsargus.akpmsarservice.ExcelDownloadService.HourlyTaskArService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api")
//public class HourlyTaskArController {
//
//
//    @Autowired
//    private HourlyTaskArService hourlyTaskArService;
//
//    @GetMapping("/hourly-productivity")
//    public ResponseEntity<?> getExcelDownload(
//
////            @Param("name") Integer name,
////            @Param("createdFrom") Date createdFrom,
////            @Param("createdTo") Date createdTo,
////            @Param("taskCompletedFrom") Date taskCompletedFrom,
////            @Param("taskCompletedTo") Date taskCompletedTo,
////            @Param("dateReceivedFrom") Date dateReceivedFrom,
////            @Param("dateReceivedTo") Date dateReceivedTo,
////            @Param("createdBy") Integer createdBy
////
////            @RequestParam(value = "name", required = false) Integer name,
////            @RequestParam(value = "createdFrom", required = false) Date createdFrom,
////            @RequestParam(value = "createdTo", required = false) Date createdTo,
////            @RequestParam(value = "taskCompletedFrom", required = false) Date taskCompletedFrom,
////            @RequestParam(value = "taskCompletedTo", required = false) Date taskCompletedTo,
////            @RequestParam(value = "dateReceivedFrom", required = false) Date dateReceivedFrom,
////            @RequestParam(value = "dateReceivedTo", required = false) Date dateReceivedTo,
////            @RequestParam(value = "createdBy", required = false) Integer createdBy
//
//
//
//
//            @RequestParam(value = "name", required = false) Integer name,
//            @RequestParam(value = "createdFrom", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime createdFrom,
//            @RequestParam(value = "createdTo", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime createdTo,
//            @RequestParam(value = "taskCompletedFrom", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime taskCompletedFrom,
//            @RequestParam(value = "taskCompletedTo", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime taskCompletedTo,
//            @RequestParam(value = "dateReceivedFrom", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime dateReceivedFrom,
//            @RequestParam(value = "dateReceivedTo", required = false)
//            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime dateReceivedTo,
//            @RequestParam(value = "createdBy", required = false) Integer createdBy
//
//    ) {
//
//
//        Date createdFromDate = createdFrom != null ? Date.from(createdFrom.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date createdToDate = createdTo != null ? Date.from(createdTo.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date taskCompletedFromDate = taskCompletedFrom != null ? Date.from(taskCompletedFrom.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date taskCompletedToDate = taskCompletedTo != null ? Date.from(taskCompletedTo.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date dateReceivedFromDate = dateReceivedFrom != null ? Date.from(dateReceivedFrom.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date dateReceivedToDate = dateReceivedTo != null ? Date.from(dateReceivedTo.atZone(ZoneId.systemDefault()).toInstant()) : null;
//
//
//
//        try {
////            List<HourlyTasksArDto> excelDownloadResponse = hourlyTaskArService.getExcelDownload(
////                    name,createdFrom,createdTo, taskCompletedFrom,taskCompletedTo, dateReceivedFrom, dateReceivedTo,
////                    createdBy
////            );
//  List<HourlyTasksArDto> excelDownloadResponse = hourlyTaskArService.getExcelDownload(
//                    name,createdFromDate,createdToDate, taskCompletedFromDate,taskCompletedToDate, dateReceivedFromDate, dateReceivedToDate,
//                    createdBy
//            );
//
//            if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
//                return ResponseEntity.noContent().build();  // Return 204 No Content if the list is empty
//            }
//            return ResponseEntity.ok(excelDownloadResponse);  // Return 200 OK with the list if records are found
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error while fetching the records: " + e.getMessage());  // Return 500 with error message
//        }
//    }
//}
//
//
//
//
//
//
//





























/*
import com.idsargus.akpmsarservice.ExcelDownloadDto.ArQueryToTLDTO1;
import com.idsargus.akpmsarservice.ExcelDownloadService.QueryToTLService1;
//import com.idsargus.akpmsarservice.excel_download_dto.ArQueryToTLDTO;
//import com.idsargus.akpmsarservice.excel_download_service.QueryToTLService;
import com.idsargus.akpmsarservice.exception.CustomException;
import com.idsargus.akpmsarservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class QueryToTLControllerExceldownload1 {

    @Autowired
    private QueryToTLService1 queryToTLService;

    @GetMapping("/query-to-tl")
    public ResponseEntity<?> getExcelDownload(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "statusCode", required = false) String statusCode,
            @RequestParam(value = "source", required = false) String source,
            @RequestParam(value = "doctor", required = false) Integer doctor,
            @RequestParam(value = "insurance", required = false) Integer insurance,
            @RequestParam(value = "team", required = false) Integer team,
            @RequestParam(value = "groupId", required = false) Integer groupId,
            @RequestParam(value = "subStatus", required = false) String subStatus,
            @RequestParam(value = "createdFrom", required = false) Date createdFrom,
            @RequestParam(value = "createdTo", required = false) Date createdTo,
            @RequestParam(value = "followFrom", required = false) Date followFrom,
            @RequestParam(value = "followTo", required = false) Date followTo,
            @RequestParam(value = "createdBy", required = false) Integer createdBy,
            @RequestParam(value = "isHd", required = false) String isHd,
            @RequestParam(value = "companyId", required = false) Integer companyId,
            @RequestParam(value = "databaseId", required = false) Integer databaseId
    ) {
        try {
            List<ArQueryToTLDTO1> excelDownloadResponse = queryToTLService.getExcelDownload(
                    query, status, statusCode, source, doctor, insurance, team, groupId, subStatus, createdFrom, createdTo,
                    followFrom, followTo, createdBy, isHd, companyId, databaseId
            );

            if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
                return ResponseEntity.noContent().build();  // Return 204 No Content if the list is empty
            }
            return ResponseEntity.ok(excelDownloadResponse);  // Return 200 OK with the list if records are found
        } catch (Exception e) {
            log.error("Error fetching records", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching the records: " + e.getMessage());  // Return 500 with error message
        }
    }
}






 */