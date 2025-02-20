package com.idsargus.akpmsadminservice.ExcelDownloadservice;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminQcPointDTO;

import java.util.List;

public interface AdminQcPointService {
//    List<AdminQcPointDTO> getExcelDownload(String query,Integer deleted,Integer status);
    List<AdminQcPointDTO> getExcelDownload(String query, Integer departmentId, Boolean status);
}
