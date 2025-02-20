package com.idsargus.akpmsadminservice.ExcelDownloadservice;

//import com.idsargus.akpmsadminservice.Excel_downoad_dto.DoctorCompanyDTO;

import com.idsargus.akpmsadminservice.ExcelDownloadDto.DoctorCompanyDTO;

import java.util.List;

public interface DoctorCompanyService {
    List<DoctorCompanyDTO> getDoctorCompaniesForExcelDownload(String query, Boolean enabled);
}
