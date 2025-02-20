package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminChargeBatchType;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.ChargeBatchTypeRepository;
 import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargeBatchTypeService {

    private final ChargeBatchTypeRepository chargeBatchTypeRepository;

    public ChargeBatchTypeService(ChargeBatchTypeRepository chargeBatchTypeRepository) {
        this.chargeBatchTypeRepository = chargeBatchTypeRepository;
    }

    @Transactional(readOnly = true)
    public Page<ChargeBatchTyperesponseDto> getAll1(Pageable pageable) {
        return chargeBatchTypeRepository.findAll1(pageable);
    }


    @Autowired
    private AdminUserRepository userRepository;

    public ChargeBatchTyperesponseDto add(ChargeBatchRequestDto dto) {
        // Create a new entity from the DTO

        if (chargeBatchTypeRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An ChargeBatchType with the name '" + dto.getName() + "' already exists.");
        }

        if(dto.getName()==null|| dto.getName().trim()=="" || dto.getCreatedBy() == null){
            throw new MandatoryFieldException("mandatory fields must not be empty or null");
        }

//        System.out.println(10/0);
        AdminChargeBatchType template = new AdminChargeBatchType();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setDescription(dto.getDescription());
        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }
        // Save the template to the database
        AdminChargeBatchType savedTemplate = chargeBatchTypeRepository.save(template);

        // Convert the saved entity to response DTO
        return new ChargeBatchTyperesponseDto(savedTemplate);
    }




    @Transactional
    public ChargeBatchTyperesponseDto update(ChargeBatchRequestDto chargeBatchRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminChargeBatchType existingEntity = chargeBatchTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChargeBatchType with provided ID not found."));

        AdminChargeBatchType existingEntity2 = chargeBatchTypeRepository.findByName(chargeBatchRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An ChargeBatchType with the name '" + chargeBatchRequestDto.getName() + "' already exists.");
        }

        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity
        existingEntity.setName(chargeBatchRequestDto.getName());

        // Only update subscriptionEmail if it's provided in the request
//        if (chargeBatchRequestDto.getDescription() != null) {
            existingEntity.setDescription(chargeBatchRequestDto.getDescription());
//        }

        // Only update subscriptionEmail if it's provided in the request
//        if (chargeBatchRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(chargeBatchRequestDto.getEnabled());
//        }



        // Fetch the 'modifiedBy' user from the database and set it
        if (chargeBatchRequestDto.getModifiedBy() != null && chargeBatchRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(chargeBatchRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }

        // Save the updated entity
        AdminChargeBatchType updatedEntity = chargeBatchTypeRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }




    private ChargeBatchTyperesponseDto convertToDTO(AdminChargeBatchType entity) {
        ChargeBatchTyperesponseDto dto = new ChargeBatchTyperesponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        return dto;
    }










    @Transactional
    public ChargeBatchTyperesponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminChargeBatchType existingEntity = chargeBatchTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminChargeBatchType updatedEntity = chargeBatchTypeRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

















}
