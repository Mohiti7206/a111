package com.idsargus.akpmsadminservice.ExcelDownloadservice;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminDoctorGroupDTO;

import java.util.List;

public interface DoctorGroupService {
    List<AdminDoctorGroupDTO> fetchExcelDownload(String query, Boolean enabled);
}
