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
    @Autowired
    private AdminUserRepository userRepository;

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



    public HouryTaskdataRestResponseDto add(HourlyTaskDataRequestDto dto) {

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




    @Transactional
    public HouryTaskdataRestResponseDto update(HourlyTaskDataRequestDto hourlyTaskDataRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminHourlyTaskEntity template = hourlyTaskDataRestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ar team with provided ID not found."));

//        AdminArTeams existingEntity2 = hourlyTaskDataRestRepository.findByName(arTeamsRequestDto.getName());
//        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
//            throw new DuplicateNameException("An AdminArTeams with the name '" + arTeamsRequestDto.getName() + "' already exists.");
//        }


        template.setHourlyTask(hourlyTaskDataRequestDto.getHourlyTask());
        template.setDetails(hourlyTaskDataRequestDto.getDetails());
        template.setTime(hourlyTaskDataRequestDto.getTime());
        template.setHours(hourlyTaskDataRequestDto.getHours());
        template.setMinutes(hourlyTaskDataRequestDto.getMinutes());
        template.setDateReceived(hourlyTaskDataRequestDto.getDateReceived());
        template.setTaskCompleted(hourlyTaskDataRequestDto.getTaskCompleted());
        template.setTaskCompleted(hourlyTaskDataRequestDto.getTaskCompleted());


        // Fetch the 'modifiedBy' user from the database and set it
        if (hourlyTaskDataRequestDto.getModifiedBy() != null && hourlyTaskDataRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(hourlyTaskDataRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            template.setModifiedBy(modifiedByUser);
        }
        System.out.println("3--------------------------------------------------------");

        // Save the updated entity
        AdminHourlyTaskEntity updatedEntity = hourlyTaskDataRestRepository.save(template);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


    private HouryTaskdataRestResponseDto convertToDTO(AdminHourlyTaskEntity entity) {
        HouryTaskdataRestResponseDto dto = new HouryTaskdataRestResponseDto();

        // Check if the entity is not null
        if (entity != null) {
            dto.setId(entity.getId());

            dto.setCreatedOn(entity.getCreatedOn());
            dto.setModifiedOn(entity.getModifiedOn());

            // Handle createdBy field
            if (entity.getCreatedBy() != null) {
                dto.setCreatedByUserName(entity.getCreatedBy().getFirstName());
            } else {
                dto.setCreatedByUserName(""); // or set to an empty string or null
            }

            // Handle modifiedBy field
            if (entity.getModifiedBy() != null) {
                dto.setModifiedByUserName(entity.getModifiedBy().getFirstName());
            } else {
                dto.setModifiedByUserName(""); // or set to an empty string or null
            }
        }

        return dto;
    }
}