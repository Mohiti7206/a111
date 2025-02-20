package com.idsargus.akpmsadminservice.ExcelDownloadservice;

//import com.idsargus.akpmsadminservice.Excel_downoad_dto.DoctorCompanyDTO;
import com.idsargus.akpmsadminservice.ExcelDownloadDto.DoctorCompanyDTO;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorCompanyRepository;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
//import com.idsargus.akpmsadminservice.repository.DoctorCompanyDataRestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorCompanyServiceImpl implements DoctorCompanyService {

//    private final DoctorCompanyDataRestRepository doctorCompanyDataRestRepository;

    @Autowired
    private final AdminDoctorCompanyRepository adminDoctorCompanyRepository;

    @Override
    public List<DoctorCompanyDTO> getDoctorCompaniesForExcelDownload(String query, Boolean enabled) {
//        List<AdminDoctorCompanyEntity> excelDownloadResponse = doctorCompanyDataRestRepository.fetchExcelDownload(query, enabled);
        List<AdminDoctorCompanyEntityMvc> excelDownloadResponse = adminDoctorCompanyRepository.fetchExcelDownload(query, enabled);


        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }

        return excelDownloadResponse.stream()
                .map(DoctorCompanyDTO::toDTO)
                .collect(Collectors.toList());
    }
}
