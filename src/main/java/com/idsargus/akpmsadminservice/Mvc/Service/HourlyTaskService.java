package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminChargeBatchType;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskNameMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminMoneySource;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminHourlyTaskRepo;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.MoneySourceRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DuplicateNameCheckExistsDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HourlyTaskRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HourlyTaskResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceResponseDto;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//public class HourlyTaskService {
//}

@Service
public class HourlyTaskService {

    private final AdminHourlyTaskRepo adminHourlyTaskRepo;

    public HourlyTaskService(AdminHourlyTaskRepo adminHourlyTaskRepo) {
        this.adminHourlyTaskRepo = adminHourlyTaskRepo;
    }

    @Transactional(readOnly = true)
    public Page<HourlyTaskResponseDto> getAll1(Pageable pageable) {
        return adminHourlyTaskRepo.findAll1(pageable);
    }


    @Autowired
    private AdminUserRepository userRepository;
//
//    public HourlyTaskResponseDto add(HourlyTaskRequestDto dto) {
//        // Create a new entity from the DTO
//        if (adminHourlyTaskRepo.findByName(dto.getName()) != null) {
//            throw new DuplicateNameException("An HourlyTask with the name '" + dto.getName() + "' already exists.");
//        }
//
//
//        System.out.println(dto.getCreatedBy().getId()+"  user id is ");
//        System.out.println(dto.getDepartment().getId()+" id is ");
//        AdminHourlyTaskNameMvc template = new AdminHourlyTaskNameMvc();
//
//        // Set the properties of the template from the DTO
//        template.setName(dto.getName());
//        template.setEnabled(dto.getEnabled());
//        template.setDeleted(dto.getDeleted());
//        template.setChargeable(dto.getChargeable());
//        template.setDepartment(dto.getDepartment());
//        template.setDescription(dto.getDescription());
//
//        // Save user ID in the created_by field
//        if (dto.getCreatedBy() != null) {
//            template.setCreatedBy(dto.getCreatedBy());
//        }
//        // Save the template to the database
//        AdminHourlyTaskNameMvc savedTemplate = adminHourlyTaskRepo.save(template);
//
//        // Convert the saved entity to response DTO
//        return new HourlyTaskResponseDto(savedTemplate);
//    }


    public HourlyTaskResponseDto add(HourlyTaskRequestDto dto) {
        // Check if an hourly task with the same name already exists
        if (adminHourlyTaskRepo.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An HourlyTask with the name '" + dto.getName() + "' already exists.");
        }

        if(dto.getDepartment()==null || dto.getChargeable()==null || dto.getName()==null|| dto.getName().trim()==""){
            throw new MandatoryFieldException("mandatory fields must not be empty or null");
        }






//        // Validate mandatory fields
//        if (dto.getDepartment() == null || dto.getDepartment().getId() == null) {
//            throw new IllegalArgumentException("Department information is required.");
//        }
//
//        if (dto.getChargeable() == null) {
//            throw new IllegalArgumentException("Chargeable status is required.");
//        }
//        if (dto.getName() == null || dto.getName() == "") {
//            throw new IllegalArgumentException("name is required");
//        }

        // Log user ID and department ID
        System.out.println(dto.getCreatedBy().getId() + "  user id is ");
        System.out.println(dto.getDepartment().getId() + " id is ");

        AdminHourlyTaskNameMvc template = new AdminHourlyTaskNameMvc();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setEnabled(dto.getEnabled());
        template.setDeleted(dto.getDeleted());
        template.setChargeable(dto.getChargeable());
        template.setDepartment(dto.getDepartment());
        template.setDescription(dto.getDescription());

        // Save user ID in the created_by field if provided
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }

        // Save the template to the database
        AdminHourlyTaskNameMvc savedTemplate = adminHourlyTaskRepo.save(template);

        // Convert the saved entity to response DTO
        return new HourlyTaskResponseDto(savedTemplate);
    }
































    @Transactional
    public HourlyTaskResponseDto update(HourlyTaskRequestDto hourlyTaskRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminHourlyTaskNameMvc existingEntity = adminHourlyTaskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("hourly task with provided ID not found."));

        AdminHourlyTaskNameMvc existingEntity2 = adminHourlyTaskRepo.findByName(hourlyTaskRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An hourly task with the name '" + hourlyTaskRequestDto.getName() + "' already exists.");
        }

        if(hourlyTaskRequestDto.getDepartment()==null || hourlyTaskRequestDto.getChargeable()==null || hourlyTaskRequestDto.getName()==null|| hourlyTaskRequestDto.getName().trim()==""){
            throw new MandatoryFieldException("mandatory fields must not be empty or null");
        }



        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity

//        if (hourlyTaskRequestDto.getDescription() != null) {
            existingEntity.setDescription(hourlyTaskRequestDto.getDescription());
//
//        }
//        if (hourlyTaskRequestDto.getChargeable() != null) {
            existingEntity.setChargeable(hourlyTaskRequestDto.getChargeable());

//        }
//         if (hourlyTaskRequestDto.getDeleted() != null) {
             existingEntity.setDeleted(hourlyTaskRequestDto.getDeleted());

//         }


//        if (hourlyTaskRequestDto.getName() != null) {
            existingEntity.setName(hourlyTaskRequestDto.getName());
//
//        }
        // Set the properties of the template from the DTO
//        existingEntity.setName(hourlyTaskRequestDto.getName());


//        existingEntity.setName(hourlyTaskRequestDto.getName());
//        existingEntity.setEnabled(hourlyTaskRequestDto.getEnabled());
//        existingEntity.setDeleted(hourlyTaskRequestDto.getDeleted());
//        existingEntity.setChargeable(hourlyTaskRequestDto.getChargeable());
//        existingEntity.setDepartment(hourlyTaskRequestDto.getDepartment());
//        existingEntity.setDescription(hourlyTaskRequestDto.getDescription());


        // Only update subscriptionEmail if it's provided in the request
//        if (hourlyTaskRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(hourlyTaskRequestDto.getEnabled());
//        }
//        if (hourlyTaskRequestDto.getDepartment() != null) {
            existingEntity.setDepartment(hourlyTaskRequestDto.getDepartment());
//        }

//

        // Fetch the 'modifiedBy' user from the database and set it
        if (hourlyTaskRequestDto.getModifiedBy() != null && hourlyTaskRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(hourlyTaskRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }

        // Save the updated entity
        AdminHourlyTaskNameMvc updatedEntity = adminHourlyTaskRepo.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }



    private HourlyTaskResponseDto convertToDTO(AdminHourlyTaskNameMvc entity) {
        HourlyTaskResponseDto dto = new HourlyTaskResponseDto();
        dto.setId(entity.getId());

        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());

        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());

        dto.setChargeable(entity.getChargeable());
        dto.setDeleted(entity.getDeleted());
        dto.setDescription(entity.getDescription());
        dto.setDepartment_name(entity.getDepartment() != null ? entity.getDepartment().getName(): null);

        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        return dto;
    }



    @Transactional
    public HourlyTaskResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminHourlyTaskNameMvc existingEntity = adminHourlyTaskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminHourlyTaskNameMvc updatedEntity = adminHourlyTaskRepo.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }






//
//    @Transactional
//    public HourlyTaskResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
//        // Fetch the existing entity from the database
//        AdminHourlyTaskNameMvc existingEntity = adminHourlyTaskRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));
//
//        // Update only the 'enabled' field and keep all other fields unchanged
//        existingEntity.setEnabled(enabled);
//
//        // Save the updated entity back to the database
//        AdminHourlyTaskNameMvc updatedEntity = adminHourlyTaskRepo.save(existingEntity);
//
//        // Convert the updated entity to a DTO and return
//        return convertToDTO(updatedEntity);
//    }





    @Transactional(readOnly = true)
    public Boolean getbyname(DuplicateNameCheckExistsDto duplicateNameCheckExistsDto) {

        return adminHourlyTaskRepo.findByName(duplicateNameCheckExistsDto.getName()) != null;
    }






}



