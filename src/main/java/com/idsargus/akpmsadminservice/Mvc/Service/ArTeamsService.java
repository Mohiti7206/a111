package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminArTeamsRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ArTeamsService {


    private final AdminArTeamsRepository adminArTeamsRepository;

    public ArTeamsService(AdminArTeamsRepository adminArTeamsRepository) {
        this.adminArTeamsRepository = adminArTeamsRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<ArTeamsResponseDto> getAll1(
            Pageable pageable
    ) {
        Page<ArTeamsResponseDto> result = adminArTeamsRepository.findAll1(
//        List<AdminEmailTemplateDTO> result = adminEmailTemplateRepository.findAllBooks(
                pageable
        );

//        logger.info("Fetched books: {}", result.getContent());
//        return bookRepository.findAllBooks(pageable);
        return result;
    }










    public ArTeamsResponseDto addArTeam(ArTeamsRequestDto dto) {
        // Create a new entity from the DTO
        if (adminArTeamsRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An AdminArTeams with the name '" + dto.getName() + "' already exists.");
        }
        if(dto.getName()==null|| dto.getName().trim()=="" || dto.getCreatedBy() == null){
            throw new MandatoryFieldException("mandatory fields must not be empty or null");
        }



        AdminArTeams template = new AdminArTeams();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());

        template.setEnabled(dto.getEnabled());

        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }




        // Save the template to the database
        AdminArTeams savedTemplate = adminArTeamsRepository.save(template);

        // Convert the saved entity to response DTO
        return new ArTeamsResponseDto(savedTemplate);
    }












    @Transactional
    public ArTeamsResponseDto updateTemplate(ArTeamsRequestDto arTeamsRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminArTeams existingEntity = adminArTeamsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ar team with provided ID not found."));

        AdminArTeams existingEntity2 = adminArTeamsRepository.findByName(arTeamsRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An AdminArTeams with the name '" + arTeamsRequestDto.getName() + "' already exists.");
        }


        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity
        existingEntity.setName(arTeamsRequestDto.getName());



        // Only update enabled if it's provided in the request
//        if (arTeamsRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(arTeamsRequestDto.getEnabled());
//        }


        System.out.println("2--------------------------------------------------------");


        // Fetch the 'modifiedBy' user from the database and set it
        if (arTeamsRequestDto.getModifiedBy() != null && arTeamsRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(arTeamsRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("3--------------------------------------------------------");

        // Save the updated entity
        AdminArTeams updatedEntity = adminArTeamsRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }






    private ArTeamsResponseDto convertToDTO(AdminArTeams entity) {
        ArTeamsResponseDto dto = new ArTeamsResponseDto();

        // Check if the entity is not null
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setEnabled(entity.getEnabled());
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
    public ArTeamsResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminArTeams existingEntity = adminArTeamsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arteam with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminArTeams updatedEntity = adminArTeamsRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }





    @Transactional(readOnly = true)
    public List<ArTeamsResponseDto> findByAllEnabled(
     ) {
        List<ArTeamsResponseDto> result = adminArTeamsRepository.findByAllEnabled();

        return result;
    }








}

