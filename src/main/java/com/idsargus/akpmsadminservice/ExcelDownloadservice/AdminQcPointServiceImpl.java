package com.idsargus.akpmsadminservice.ExcelDownloadservice;

 import com.idsargus.akpmsadminservice.ExcelDownloadDto.AdminQcPointDTO;
 import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
 import com.idsargus.akpmsadminservice.Mvc.Repository.AdminQcPointRepository;
// import com.idsargus.akpmsadminservice.entity.AdminQcPointEntity;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminQcPointServiceImpl implements AdminQcPointService {

    @Autowired
    private AdminQcPointRepository qcPointDataRestRepository;

    @Override
    @Transactional
    @Cacheable("adminQcPoints")
    public List<AdminQcPointDTO> getExcelDownload(String query, Integer departmentId, Boolean status ) {
//        List<AdminQcPointEntity> excelDownloadResponse = qcPointDataRestRepository.fetchExcelDownload(query, deleted,status);
        List<AdminQcPointEntityMvc> excelDownloadResponse = qcPointDataRestRepository.fetchExcelDownload(query,departmentId,status);

// Return an empty list if no records are found
        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }

        return excelDownloadResponse.stream()
                .map(AdminQcPointDTO::toDTO)
                .collect(Collectors.toList());
    }
}
