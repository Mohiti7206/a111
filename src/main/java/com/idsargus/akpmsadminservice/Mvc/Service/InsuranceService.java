package com.idsargus.akpmsadminservice.Mvc.Service;

//public class InsuranceService {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskNameMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminInsuranceEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.InsuranceRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.HourlyTaskResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.InsuranceRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.InsuranceResponseDto;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    @Transactional(readOnly = true)
//    public Page<InsuranceResponseDto> getAll1(Pageable pageable) {
    public List<InsuranceResponseDto> getAll1(Pageable pageable) {
        return insuranceRepository.findAll1(pageable);
    }


    @Autowired
    private AdminUserRepository userRepository;
//
    public InsuranceResponseDto add(InsuranceRequestDto dto) {
        // Create a new entity from the DTO
        if (insuranceRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Insurance with the name '" + dto.getName() + "' already exists.");
        }


        System.out.println(dto.getCreatedBy().getId()+"  user id is ");
        AdminInsuranceEntityMvc template = new AdminInsuranceEntityMvc();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setEnabled(dto.getEnabled());
        template.setDeleted(dto.getDeleted());
        template.setDescription(dto.getDescription());


        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }
        // Save the template to the database
        AdminInsuranceEntityMvc savedTemplate = insuranceRepository.save(template);

        // Convert the saved entity to response DTO
        return new InsuranceResponseDto(savedTemplate);
    }


//    public InsuranceResponseDto add(HourlyTaskRequestDto dto) {
//        // Check if an hourly task with the same name already exists
//        if (insuranceRepository.findByName(dto.getName()) != null) {
//            throw new DuplicateNameException("An HourlyTask with the name '" + dto.getName() + "' already exists.");
//        }
//
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
//
//        // Log user ID and department ID
//        System.out.println(dto.getCreatedBy().getId() + "  user id is ");
//        System.out.println(dto.getDepartment().getId() + " id is ");
//
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
//        // Save user ID in the created_by field if provided
//        if (dto.getCreatedBy() != null) {
//            template.setCreatedBy(dto.getCreatedBy());
//        }
//
//        // Save the template to the database
//        AdminHourlyTaskNameMvc savedTemplate = adminHourlyTaskRepo.save(template);
//
//        // Convert the saved entity to response DTO
//        return new HourlyTaskResponseDto(savedTemplate);
//    }
//















    @Transactional(readOnly = true)
    public List<InsuranceResponseDto> findByAllEnabled(
    ) {
        List<InsuranceResponseDto> result = insuranceRepository.findByAllEnabled();
        return result;
    }
















    @Transactional
    public InsuranceResponseDto update(InsuranceRequestDto insuranceRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminInsuranceEntityMvc existingEntity = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance task with provided ID not found."));


        AdminInsuranceEntityMvc existingEntity2 = insuranceRepository.findByName(insuranceRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An Insurance with the name '" + insuranceRequestDto.getName() + "' already exists.");
        }

        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity

//        if (insuranceRequestDto.getDescription() != null) {
            existingEntity.setDescription(insuranceRequestDto.getDescription());

//        }

//        if (insuranceRequestDto.getDeleted() != null) {
            existingEntity.setDeleted(insuranceRequestDto.getDeleted());
//        }

//        if (insuranceRequestDto.getEnabled() != null) {
            existingEntity.setDeleted(insuranceRequestDto.getEnabled());
//        }


//        if (insuranceRequestDto.getName() != null) {
            existingEntity.setName(insuranceRequestDto.getName());

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
//        if (insuranceRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(insuranceRequestDto.getEnabled());
//        }


//

        // Fetch the 'modifiedBy' user from the database and set it
        if (insuranceRequestDto.getModifiedBy() != null && insuranceRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(insuranceRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }

        // Save the updated entity
        AdminInsuranceEntityMvc updatedEntity = insuranceRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

//
//
    private InsuranceResponseDto convertToDTO(AdminInsuranceEntityMvc entity) {
        InsuranceResponseDto dto = new InsuranceResponseDto();
        dto.setId(entity.getId());

        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());

        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());

        dto.setDeleted(entity.getDeleted());
        dto.setDescription(entity.getDescription());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        return dto;
    }

//
//
    @Transactional
    public InsuranceResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminInsuranceEntityMvc existingEntity = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("insurance with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminInsuranceEntityMvc updatedEntity = insuranceRepository.save(existingEntity);

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












}



