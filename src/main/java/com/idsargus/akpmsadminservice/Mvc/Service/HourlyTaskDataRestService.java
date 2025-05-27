package com.idsargus.akpmsadminservice.Mvc.Service;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminArTeamsRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.HourlyTaskDataRestRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
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


//
//

    public HouryTaskdataRestResponseDto add(HourlyTaskDataRequestDto dto) {
        // Create a new entity from the DTO
//        if (hourlyTaskDataRestRepository.findByHourlyTask_Name(dto.getHourlyTask().getName()) != null) {
//            throw new DuplicateNameException("An HourlyTask with the name '" + dto.getHourlyTask().getName() + "' already exists.");
//        }
        if(dto.getHourlyTask()==null || dto.getCreatedBy() == null){
            throw new MandatoryFieldException("mandatory fields must not be empty or null");
        }



        AdminHourlyTaskEntity template = new AdminHourlyTaskEntity();

        // Set the properties of the template from the DTO
        template.setHourlyTask(dto.getHourlyTask());
        template.setDetails(dto.getDetails());
        template.setTime(dto.getTime());
        template.setHours(dto.getHours());
        template.setMinutes(dto.getMinutes());
        template.setDateReceived(dto.getDateReceived());
        template.setTaskCompleted(dto.getTaskCompleted());
        template.setTaskCompleted(dto.getTaskCompleted());

        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }

        AdminHourlyTaskEntity savedTemplate = hourlyTaskDataRestRepository.save(template);

        return new HouryTaskdataRestResponseDto(savedTemplate);
    }







}