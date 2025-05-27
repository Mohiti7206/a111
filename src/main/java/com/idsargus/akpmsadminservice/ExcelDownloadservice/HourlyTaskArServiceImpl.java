package com.idsargus.akpmsadminservice.ExcelDownloadservice;


import com.idsargus.akpmsadminservice.ExcelDownloadDto.HourlyTasksArDto;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskEntity;
import com.idsargus.akpmsadminservice.Mvc.Repository.HourlyTaskDataRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HourlyTaskArServiceImpl implements HourlyTaskArService{

    @Autowired
    private HourlyTaskDataRestRepository hourlyTaskDataRestRepository;


    @Transactional
    @Override
    public List<HourlyTasksArDto> getExcelDownload(Integer name,
                                                   Integer createdBy,
                                                   Date createdFrom,
                                                   Date createdTo,
                                                   Date taskCompletedFrom,
                                                   Date taskCompletedTo,
                                                   Date dateReceivedFrom,
                                                   Date dateReceivedTo
    )  {

//        List<ArHourlyTaskEntity> excelDownloadResponse = hourlyTaskDataRestRepository.fetchExcelDownload(
//               name,createdFrom,createdTo,taskCompletedFrom,taskCompletedTo,dateReceivedFrom,dateReceivedTo,createdBy
//        );
        List<AdminHourlyTaskEntity> excelDownloadResponse = hourlyTaskDataRestRepository.fetchExcelDownload(
                name,createdBy, createdFrom,createdTo,taskCompletedFrom,taskCompletedTo,dateReceivedFrom,dateReceivedTo
        );
        // Return an empty list if no records are found
        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }


        return excelDownloadResponse.stream()
                .map(HourlyTasksArDto::fromEntity)
                .limit(50000)
                .collect(Collectors.toList());
    }
}
