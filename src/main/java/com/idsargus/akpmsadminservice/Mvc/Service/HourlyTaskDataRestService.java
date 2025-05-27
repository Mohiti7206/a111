package com.idsargus.akpmsadminservice.Mvc.Service;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminArTeamsRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.HourlyTaskDataRestRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DuplicateNameCheckExistsDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HouryTaskdataRestResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class HourlyTaskDataRestService {


    private final HourlyTaskDataRestRepository hourlyTaskDataRestRepository;

    public HourlyTaskDataRestService(HourlyTaskDataRestRepository hourlyTaskDataRestRepository) {
        this.hourlyTaskDataRestRepository = hourlyTaskDataRestRepository;
    }



//    @Transactional(readOnly = true)
//    public Page<HouryTaskdataRestResponseDto> getAll1(
//            Pageable pageable
//    ) {
//        Page<HouryTaskdataRestResponseDto> result = hourlyTaskDataRestRepository.findAll1(
//                pageable
//        );
//        return result;
//    }

    @Transactional(readOnly = true)
    public Page<HouryTaskdataRestResponseDto> getAll1(
            Integer name,
            Date createdFrom,
            Date createdTo,
            Date taskCompletedFrom,
            Date taskCompletedTo,
            Date dateReceivedFrom,
            Date dateReceivedTo,
            Integer createdBy,
            Pageable pageable
    ) {
        return hourlyTaskDataRestRepository.findAll1(
                name,
                createdFrom,
                createdTo,
                taskCompletedFrom,
                taskCompletedTo,
                dateReceivedFrom,
                dateReceivedTo,
                createdBy,
                pageable
        );
    }

}