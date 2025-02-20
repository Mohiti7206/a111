package com.idsargus.akpmsadminservice.ExcelDownloadservice;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.DoctorDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getExcelDownload(
            String query,
            Boolean enabled,
            Boolean deleted
    );
}
