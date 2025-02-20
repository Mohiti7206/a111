package com.idsargus.akpmsadminservice.ExcelDownloadservice;


//import com.idsargus.akpmsadminservice.Excel_downoad_dto.DoctorDTO;
import com.idsargus.akpmsadminservice.ExcelDownloadDto.DoctorDTO;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Repository.DoctorRepository;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorEntity;
import com.idsargus.akpmsadminservice.exception.ResourceNotFoundException;
//import com.idsargus.akpmsadminservice.repository.DoctorDataRestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImple implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    @Override
    public List<DoctorDTO> getExcelDownload(
            String query,
            Boolean enabled,
            Boolean deleted
    ) {
        List<AdminDoctorEntityMvc> excelDownloadResponse = doctorRepository.fetchExcelDownload(query, enabled, deleted);

        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }

        return excelDownloadResponse.stream()
                .map(DoctorDTO::fromEntity)
                .limit(5000) // Adjust as needed
                .collect(Collectors.toList());
    }
}
