package com.idsargus.akpmsadminservice.Mvc.Service;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminEmailTemplateRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminEmailTemplateRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminEmailTemplateResponseDTO;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
//@Transactional(readOnly = true)
public class AdminEmailTemplateService {

    private final AdminEmailTemplateRepository adminEmailTemplateRepository;

    public AdminEmailTemplateService(AdminEmailTemplateRepository adminEmailTemplateRepository) {
        this.adminEmailTemplateRepository = adminEmailTemplateRepository;
    }

    @Autowired
 private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public List<AdminEmailTemplateResponseDTO> getAll1(
                                                Pageable pageable
    ) {
        List<AdminEmailTemplateResponseDTO> result = adminEmailTemplateRepository.findAll1(
//        List<AdminEmailTemplateDTO> result = adminEmailTemplateRepository.findAllBooks(
                pageable
        );

//        logger.info("Fetched books: {}", result.getContent());
//        return bookRepository.findAllBooks(pageable);
        return result;
    }



//
//    @Transactional
//    public AdminEmailTemplateResponseDTO updateTemplate(AdminEmailTemplateEntity2 adminEmailTemplateEntity, Integer id) {
//        // Update the entity in the database
//        int rowsUpdated = adminEmailTemplateRepository.updateTemplate(
//                id,
//                adminEmailTemplateEntity.getName(),
//                adminEmailTemplateEntity.getContent(),
//                adminEmailTemplateEntity.getSubscriptionEmail(),
//                adminEmailTemplateEntity.getEnabled(),
//                adminEmailTemplateEntity.getStatus(),
//                adminEmailTemplateEntity.getIs_deleted(),
//                adminEmailTemplateEntity.getUserModifiedBy().getId()
//        );
//
//        if (rowsUpdated > 0) {
//            // Fetch the updated entity and convert it to a DTO
//            Optional<AdminEmailTemplateEntity2> updatedEntityOptional =
//                    adminEmailTemplateRepository.findById(id);
//
//            if (updatedEntityOptional.isPresent()) {
//                AdminEmailTemplateEntity2 updatedEntity = updatedEntityOptional.get();
//
//                // Convert the entity to a DTO
//                return convertToDTO(updatedEntity);
//            }
//        }
//
//        throw new RuntimeException("Failed to update template , template with provided id not found.");
//    }


















//    @Transactional
//    public AdminEmailTemplateResponseDTO updateTemplate(AdminEmailTemplateRequestDto adminEmailTemplateRequestDto, Integer id) {
//        // Fetch the existing entity from the database
//        Optional<AdminEmailTemplateEntity2> existingEntityOptional = adminEmailTemplateRepository.findById(id);
//
//        if (existingEntityOptional.isPresent()) {
//            AdminEmailTemplateEntity2 existingEntity = existingEntityOptional.get();
//
//            // Map fields from request DTO to the entity
//            existingEntity.setName(adminEmailTemplateRequestDto.getName());
//            existingEntity.setContent(adminEmailTemplateRequestDto.getContent());
//            existingEntity.setSubscriptionEmail(adminEmailTemplateRequestDto.getSubscriptionEmail());
//            existingEntity.setEnabled(adminEmailTemplateRequestDto.getEnabled());
//            existingEntity.setStatus(adminEmailTemplateRequestDto.getStatus());
//            existingEntity.setIs_deleted(adminEmailTemplateRequestDto.getIsDeleted());
//            existingEntity.setModifiedBy(adminEmailTemplateRequestDto.getModifiedBy());
//
//
//            System.out.println("ok till here 2");
//
//            // Save user ID in the modified by field
////            if (adminEmailTemplateRequestDto.getUser() != null) {
////                System.out.println("user is not null");
////                User user = adminEmailTemplateRequestDto.getUser();
////                System.out.println(user.getFirstName());
////                System.out.println(user.getId()+" =  id");
////
////                existingEntity.setModifiedBy(user);
////            }
//            // S
//            System.out.println("ok till here 3");
//
//
//            System.out.println(existingEntity.getId());
//            System.out.println(existingEntity.getCreatedBy().getFirstName());
//            System.out.println(existingEntity.getEnabled());
//            System.out.println(existingEntity.getContent());
//            System.out.println(existingEntity.getName());
//            System.out.println(existingEntity.getIs_deleted());
//            System.out.println("modifiedby name  == " + existingEntity.getModifiedBy().getFirstName());
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
//
//
//            // Save the updated entity
//            AdminEmailTemplateEntity2 updatedEntity = adminEmailTemplateRepository.save(existingEntity);
//
//            // Convert the updated entity to a DTO
//            return convertToDTO(updatedEntity);
//        }
//
//        // If the entity with the provided ID is not found, throw an exception
//        throw new RuntimeException("Failed to update template, template with provided id not found.");
//    }
//
//
//
//










    @Transactional
    public AdminEmailTemplateResponseDTO updateTemplate(AdminEmailTemplateRequestDto adminEmailTemplateRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminEmailTemplateEntity2 existingEntity = adminEmailTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));


        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity
        existingEntity.setName(adminEmailTemplateRequestDto.getName());
        existingEntity.setContent(adminEmailTemplateRequestDto.getContent());

        // Only update subscriptionEmail if it's provided in the request
        if (adminEmailTemplateRequestDto.getSubscriptionEmail() != null) {
            existingEntity.setSubscriptionEmail(adminEmailTemplateRequestDto.getSubscriptionEmail());
        }

        // Only update enabled if it's provided in the request
        if (adminEmailTemplateRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(adminEmailTemplateRequestDto.getEnabled());
        }

        // Only update is_deleted if it's provided in the request
        if (adminEmailTemplateRequestDto.getIsDeleted() != null) {
            existingEntity.setIs_deleted(adminEmailTemplateRequestDto.getIsDeleted());
        }

        // Only update status if it's provided in the request
        if (adminEmailTemplateRequestDto.getStatus() != null) {
            existingEntity.setStatus(adminEmailTemplateRequestDto.getStatus());
        }


        // Fetch the 'modifiedBy' user from the database and set it
        if (adminEmailTemplateRequestDto.getModifiedBy() != null && adminEmailTemplateRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminEmailTemplateRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }

        // Save the updated entity
        AdminEmailTemplateEntity2 updatedEntity = adminEmailTemplateRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }























//
//    @Transactional
//    public AdminEmailTemplateResponseDTO updateTemplateActivationStatus( Integer id , boolean status) {
//        // Update the entity in the database
//        int rowsUpdated = adminEmailTemplateRepository.updateTemplateActivationStatus(
//                id,
//                status
//        );
//
//        if (rowsUpdated > 0) {
//            // Fetch the updated entity and convert it to a DTO
//            Optional<AdminEmailTemplateEntity2> updatedEntityOptional =
//                    adminEmailTemplateRepository.findById(id);
//
//            if (updatedEntityOptional.isPresent()) {
//                AdminEmailTemplateEntity2 updatedEntity = updatedEntityOptional.get();
//
//                // Convert the entity to a DTO
//                return convertToDTO(updatedEntity);
//            }
//        }
//
//        throw new RuntimeException("Failed to update template , template with provided id not found. to update the activation status");
//    }


    @Transactional
    public AdminEmailTemplateResponseDTO updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminEmailTemplateEntity2 existingEntity = adminEmailTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminEmailTemplateEntity2 updatedEntity = adminEmailTemplateRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


































    private AdminEmailTemplateResponseDTO convertToDTO(AdminEmailTemplateEntity2 entity) {
        AdminEmailTemplateResponseDTO dto = new AdminEmailTemplateResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setSubscriptionEmail(entity.getSubscriptionEmail());
        dto.setEnabled(entity.getEnabled());
        dto.setStatus(entity.getStatus());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        return dto;
    }



    public AdminEmailTemplateResponseDTO addEmailTemplate(AdminEmailTemplateRequestDto dto) {
        // Create a new entity from the DTO
        AdminEmailTemplateEntity2 template = new AdminEmailTemplateEntity2();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setContent(dto.getContent());
        template.setSubscriptionEmail(dto.getSubscriptionEmail());
        template.setEnabled(dto.getEnabled());
        template.setStatus(dto.getStatus());
        template.setIs_deleted(dto.getIsDeleted());
        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }
        // Save the template to the database
        AdminEmailTemplateEntity2 savedTemplate = adminEmailTemplateRepository.save(template);

        // Convert the saved entity to response DTO
        return new AdminEmailTemplateResponseDTO(savedTemplate);
    }







}
