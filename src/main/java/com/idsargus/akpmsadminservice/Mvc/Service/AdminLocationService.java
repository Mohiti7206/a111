package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminLocationRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminLocationService {

    private final AdminLocationRepository adminLocationRepository;

    public AdminLocationService(AdminLocationRepository adminLocationRepository) {
        this.adminLocationRepository = adminLocationRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<LocationResponseDto> getAll1(
            Pageable pageable
    ) {
        Page<LocationResponseDto> result = adminLocationRepository.findAll1(
//        List<AdminEmailTemplateDTO> result = adminEmailTemplateRepository.findAllBooks(
                pageable
        );

//        logger.info("Fetched books: {}", result.getContent());
//        return bookRepository.findAllBooks(pageable);
        return result;
    }










    public LocationResponseDto add(AdminLocationRequestDto dto) {
        // Create a new entity from the DTO

        if (adminLocationRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Location with the name '" + dto.getName() + "' already exists.");
        }
        AdminLocations template = new AdminLocations();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setEnabled(dto.getEnabled());
        template.setDesc(dto.getDescription());


        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }


        System.out.println("1----------------------------------------------------------------------1111111111111111");
        // Save the template to the database
        AdminLocations savedTemplate = adminLocationRepository.save(template);

        // Convert the saved entity to response DTO
        return new LocationResponseDto(savedTemplate);
    }





    @Transactional
    public LocationResponseDto updateTemplate(AdminLocationRequestDto adminLocationRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminLocations existingEntity = adminLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location with provided ID not found."));


        AdminLocations existingEntity2 = adminLocationRepository.findByName(adminLocationRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An Location with the name '" + adminLocationRequestDto.getName() + "' already exists.");
        }







        // Map fields from request DTO to the entity
             existingEntity.setName(adminLocationRequestDto.getName());


             existingEntity.setDesc(adminLocationRequestDto.getDescription());

             existingEntity.setEnabled(adminLocationRequestDto.getEnabled());



        System.out.println("2--------------------------------------------------------");


        // Fetch the 'modifiedBy' user from the database and set it
        if (adminLocationRequestDto.getModifiedBy() != null && adminLocationRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminLocationRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("3--------------------------------------------------------");

        // Save the updated entity
        AdminLocations updatedEntity = adminLocationRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }







    private LocationResponseDto convertToDTO(AdminLocations entity) {
        LocationResponseDto dto = new LocationResponseDto();

        // Check if the entity is not null
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setEnabled(entity.isEnabled());
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



    @Transactional
    public LocationResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminLocations existingEntity = adminLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location with provided ID not found."));




        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminLocations updatedEntity = adminLocationRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


}
