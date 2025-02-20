package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminLocations;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRevenueTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminPaymentTypeRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminPaymentTypeResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.PaymentTypeRequestDto;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminPaymentTypeService {

    private final AdminPaymentTypeRepository adminPaymentTypeRepository;

    public AdminPaymentTypeService(AdminPaymentTypeRepository adminPaymentTypeRepository) {
        this.adminPaymentTypeRepository = adminPaymentTypeRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<AdminPaymentTypeResponse> getAll1(
            Pageable pageable
    ) {
        Page<AdminPaymentTypeResponse> result = adminPaymentTypeRepository.findAll1(
//        List<AdminEmailTemplateDTO> result = adminEmailTemplateRepository.findAllBooks(
                pageable
        );

//        logger.info("Fetched books: {}", result.getContent());
//        return bookRepository.findAllBooks(pageable);
        return result;
    }





//
//

    @Transactional
    public AdminPaymentTypeResponse updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminPaymentTypeMvc existingEntity = adminPaymentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentType with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminPaymentTypeMvc updatedEntity = adminPaymentTypeRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

//
//
//
//
//
//



    public AdminPaymentTypeResponse add(PaymentTypeRequestDto dto) {
        // Create a new entity from the DTO

        if (adminPaymentTypeRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An PaymentType with the name '" + dto.getName() + "' already exists.");
        }
        AdminPaymentTypeMvc template = new AdminPaymentTypeMvc();

        // Set properties of the template from the DTO
        template.setName(dto.getName());
        template.setDescription(dto.getDescription());
        template.setEnabled(dto.getEnabled());
        template.setDeleted(false); // Assuming default value for deleted is false


        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }


        System.out.println("1----------------------------------------------------------------------1111111111111111");
        // Save the template to the database
        AdminPaymentTypeMvc savedTemplate = adminPaymentTypeRepository.save(template);

        // Convert the saved entity to response DTO
        return new AdminPaymentTypeResponse(savedTemplate);
    }




//
//
//
//
//    public AdminPaymentTypeResponse add(AdminRevenueTypeRequestDto dto) {
//        // Check for duplicates
//        if (adminPaymentTypeRepository.findByName(dto.getName()) != null) {
//            throw new DuplicateNameException("A Revenue Type with the name '" + dto.getName() + "' already exists.");
//        }
//
//        // Create a new entity from the DTO
//        AdminRevenueTypeMvc template = new AdminRevenueTypeMvc();
//
//        // Set properties from the DTO
//        template.setName(dto.getName());
//        template.setCode(dto.getCode());
//        template.setPayments(dto.getPayments());
//        template.setOperations(dto.getOperations());
//        template.setAccounting(dto.getAccounting());
//        template.setDescription(dto.getDescription());
//        template.setEnabled(dto.getEnabled());
//        template.setDeleted(false); // Default value for deleted
//
//        // Save user ID in the created_by field if provided
//        if (dto.getCreatedBy() != null) {
//            template.setCreatedBy(dto.getCreatedBy());
//        }
//
//        // Save the template to the database
//        AdminRevenueTypeMvc savedTemplate = adminRevenueTypeMvcRepository.save(template);
//
//        // Convert and return the saved entity as DTO
//        return new AdminRevenueTypeMvcResponseDto(savedTemplate);
//    }
//
//
//
//
//
//
//
//
//
//

    @Transactional
    public AdminPaymentTypeResponse update(PaymentTypeRequestDto paymentTypeRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminPaymentTypeMvc existingEntity = adminPaymentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentType with provided ID not found."));


        System.out.println("1--------------------------------------------------------");


        AdminPaymentTypeMvc existingEntity2 = adminPaymentTypeRepository.findByName(paymentTypeRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An PaymentType with the name '" + paymentTypeRequestDto.getName() + "' already exists.");
        }







            existingEntity.setEnabled(paymentTypeRequestDto.getEnabled());

            existingEntity.setName(paymentTypeRequestDto.getName());

             existingEntity.setDescription(paymentTypeRequestDto.getDescription());
             existingEntity.setDeleted(paymentTypeRequestDto.getDeleted());


        System.out.println("2--------------------------------------------------------");


        // Fetch the 'modifiedBy' user from the database and set it
        if (paymentTypeRequestDto.getModifiedBy() != null && paymentTypeRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(paymentTypeRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("3--------------------------------------------------------");

        // Save the updated entity
        AdminPaymentTypeMvc updatedEntity = adminPaymentTypeRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }




    private AdminPaymentTypeResponse convertToDTO(AdminPaymentTypeMvc entity) {
        AdminPaymentTypeResponse dto = new AdminPaymentTypeResponse();

        // Check if the entity is not null
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
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
