package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.*;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminArTeamsRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminRevenueTypeMvcRepository;
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
public class AdminReveneueTypeService {

        private final AdminRevenueTypeMvcRepository adminRevenueTypeMvcRepository;

        public AdminReveneueTypeService(AdminRevenueTypeMvcRepository adminRevenueTypeMvcRepository) {
            this.adminRevenueTypeMvcRepository = adminRevenueTypeMvcRepository;
        }

        @Autowired
        private AdminUserRepository userRepository;

        @Transactional(readOnly = true)
        public Page<AdminRevenueTypeMvcResponseDto> getAll1(
                Pageable pageable
        ) {
            Page<AdminRevenueTypeMvcResponseDto> result = adminRevenueTypeMvcRepository.findAll1(
//        List<AdminEmailTemplateDTO> result = adminEmailTemplateRepository.findAllBooks(
                    pageable
            );

//        logger.info("Fetched books: {}", result.getContent());
//        return bookRepository.findAllBooks(pageable);
            return result;
        }








    @Transactional
    public AdminRevenueTypeMvcResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminRevenueTypeMvc existingEntity = adminRevenueTypeMvcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminRevenueTypeMvc updatedEntity = adminRevenueTypeMvcRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }








//
//
//    public AdminRevenueTypeMvcResponseDto add(AdminRevenueTypeRequestDto dto) {
//        // Create a new entity from the DTO
//
//        if (adminRevenueTypeMvcRepository.findByName(dto.getName()) != null) {
//            throw new DuplicateNameException("An Location with the name '" + dto.getName() + "' already exists.");
//        }
//        AdminRevenueTypeMvc template = new AdminRevenueTypeMvc();
//
//
//        // Set properties of the template from the DTO
//        template.setName(dto.getName());
//        template.setCode(dto.getCode());
//        template.setPayments(dto.getPayments());
//        template.setOperations(dto.getOperations());
//        template.setAccounting(dto.getAccounting());
//        template.setDescription(dto.getDescription());
//        template.setEnabled(dto.getEnabled());
//        template.setDeleted(false); // Assuming default value for deleted is false
//
//
//        // Save user ID in the created_by field
//        if (dto.getCreatedBy() != null) {
//            template.setCreatedBy(dto.getCreatedBy());
//        }
//
//
//        System.out.println("1----------------------------------------------------------------------1111111111111111");
//        // Save the template to the database
//        AdminRevenueTypeMvc savedTemplate = adminRevenueTypeMvcRepository.save(template);
//
//        // Convert the saved entity to response DTO
//        return new AdminRevenueTypeMvcResponseDto(savedTemplate);
//    }








    public AdminRevenueTypeMvcResponseDto add(AdminRevenueTypeRequestDto dto) {
        // Check for duplicates
        if (adminRevenueTypeMvcRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("A Revenue Type with the name '" + dto.getName() + "' already exists.");
        }

        // Create a new entity from the DTO
        AdminRevenueTypeMvc template = new AdminRevenueTypeMvc();

        // Set properties from the DTO
        template.setName(dto.getName());
        template.setCode(dto.getCode());
        template.setPayments(dto.getPayments());
        template.setOperations(dto.getOperations());
        template.setAccounting(dto.getAccounting());
        template.setDescription(dto.getDescription());
        template.setEnabled(dto.getEnabled());
        template.setDeleted(false); // Default value for deleted

        // Save user ID in the created_by field if provided
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }

        // Save the template to the database
        AdminRevenueTypeMvc savedTemplate = adminRevenueTypeMvcRepository.save(template);

        // Convert and return the saved entity as DTO
        return new AdminRevenueTypeMvcResponseDto(savedTemplate);
    }











    @Transactional
    public AdminRevenueTypeMvcResponseDto updateTemplate(AdminRevenueTypeRequestDto adminRevenueTypeRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminRevenueTypeMvc existingEntity = adminRevenueTypeMvcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revenue Type with provided ID not found."));



        AdminRevenueTypeMvc existingEntity2 = adminRevenueTypeMvcRepository.findByName(adminRevenueTypeRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("A Revenue Type with the name '" + adminRevenueTypeRequestDto.getName() + "' already exists.");
        }



        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity
        existingEntity.setName(adminRevenueTypeRequestDto.getName());



        // Only update enabled if it's provided in the request
//        if (adminRevenueTypeRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(adminRevenueTypeRequestDto.getEnabled());
//        }
        // Only update enabled if it's provided in the request
//        if (adminRevenueTypeRequestDto.getAccounting() != null) {
            existingEntity.setAccounting(adminRevenueTypeRequestDto.getAccounting());
//        } // Only update enabled if it's provided in the request
//        if (adminRevenueTypeRequestDto.getOperations() != null) {
            existingEntity.setOperations(adminRevenueTypeRequestDto.getOperations());
//        }
//        if (adminRevenueTypeRequestDto.getPayments() != null) {
            existingEntity.setPayments(adminRevenueTypeRequestDto.getPayments());
//        }
//        if (adminRevenueTypeRequestDto.getCode() != null) {
            existingEntity.setCode(adminRevenueTypeRequestDto.getCode());
//        }
//        if (adminRevenueTypeRequestDto.getDescription() != null) {
            existingEntity.setDescription(adminRevenueTypeRequestDto.getDescription());
//        }


        System.out.println("2--------------------------------------------------------");


        // Fetch the 'modifiedBy' user from the database and set it
        if (adminRevenueTypeRequestDto.getModifiedBy() != null && adminRevenueTypeRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminRevenueTypeRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("3--------------------------------------------------------");

        // Save the updated entity
        AdminRevenueTypeMvc updatedEntity = adminRevenueTypeMvcRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }









    private AdminRevenueTypeMvcResponseDto convertToDTO(AdminRevenueTypeMvc entity) {
        AdminRevenueTypeMvcResponseDto dto = new AdminRevenueTypeMvcResponseDto();

        // Check if the entity is not null
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCode(entity.getCode()); // Assuming you have a code field
            dto.setPayments(entity.getPayments());
            dto.setOperations(entity.getOperations());
            dto.setAccounting(entity.getAccounting());
            dto.setDescription(entity.getDescription());
            dto.setEnabled(entity.getEnabled());
            dto.setDeleted(entity.getDeleted());
            dto.setCreatedOn(entity.getCreatedOn());
            dto.setModifiedOn(entity.getModifiedOn());

            // Handle createdBy field
            if (entity.getCreatedBy() != null) {
                dto.setCreatedByUserName(entity.getCreatedBy().getFirstName() + " " +
                        entity.getCreatedBy().getLastName());
            } else {
                dto.setCreatedByUserName(""); // or set to an empty string or null
            }

            // Handle modifiedBy field
            if (entity.getModifiedBy() != null) {
                dto.setModifiedByUserName(entity.getModifiedBy().getFirstName() + " " +
                        entity.getModifiedBy().getLastName());
            } else {
                dto.setModifiedByUserName(""); // or set to an empty string or null
            }
        }

        return dto;
    }



}
