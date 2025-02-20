package com.idsargus.akpmsadminservice.ExcelDownloadservice;

 import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminDoctorGroupDTO;
 import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
 import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorGroupRepository;
// import com.idsargus.akpmsadminservice.entity.AdminDoctorGroup;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorGroupServiceImpl implements DoctorGroupService {

    @Autowired
    private AdminDoctorGroupRepository doctorGroupDataRestRepository;

    @Override
    public List<AdminDoctorGroupDTO> fetchExcelDownload(String query, Boolean enabled) {
        List<AdminDoctorGroupMvc> excelDownloadResponse = doctorGroupDataRestRepository.fetchExcelDownload(query, enabled);

        System.out.println("in the serviceImpl 1");

        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }

        System.out.println("in the serviceImpl 2");


        return excelDownloadResponse.stream()
                       .map(AdminDoctorGroupDTO::toDTO)
                       .collect(Collectors.toList());
    }
}
