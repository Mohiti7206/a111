package com.idsargus.akpmsadminservice.ExcelDownloadservice;


//import com.idsargus.akpmsarservice.ExcelDownloadDto.HourlyTasksArDto;

//import com.idsargus.akpmsadminservice.Excel_downoad_dto.HourlyTasksArDto;

import com.idsargus.akpmsadminservice.ExcelDownloadDto.HourlyTasksArDto;

import java.util.Date;
import java.util.List;

public interface HourlyTaskArService {


    List<HourlyTasksArDto> getExcelDownload(Integer name,
                                            Integer createdBy,
                                            Date createdFrom,
                                            Date createdTo,
                                            Date taskCompletedFrom,
                                            Date taskCompletedTo,
                                            Date dateReceivedFrom,
                                            Date dateReceivedTo
    );
}
