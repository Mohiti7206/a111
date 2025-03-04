package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminMoneySource;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.MoneySourceRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DuplicateNameCheckExistsDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.moneysource.MoneySourceResponseDto;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneySourceService {

    private final MoneySourceRepository moneySourceRepository;

    public MoneySourceService(MoneySourceRepository moneySourceRepository) {
        this.moneySourceRepository = moneySourceRepository;
    }

    @Transactional(readOnly = true)
    public Page<MoneySourceResponseDto> getAll1(Pageable pageable) {
        return moneySourceRepository.findAll1(pageable);
    }


    @Autowired
    private AdminUserRepository userRepository;

    public MoneySourceResponseDto add(MoneySourceRequestDto dto) {
        // Create a new entity from the DTO
        if (moneySourceRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An MoneySource with the name '" + dto.getName() + "' already exists.");
        }
        AdminMoneySource template = new AdminMoneySource();

        // Set the properties of the template from the DTO
        template.setName(dto.getName());
        template.setEnabled(dto.getEnabled());
        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            template.setCreatedBy(dto.getCreatedBy());
        }
        // Save the template to the database
        AdminMoneySource savedTemplate = moneySourceRepository.save(template);

        // Convert the saved entity to response DTO
        return new MoneySourceResponseDto(savedTemplate);
    }




    @Transactional
    public MoneySourceResponseDto update(MoneySourceRequestDto moneySourceRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminMoneySource existingEntity = moneySourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MoneySource with provided ID not found."));

        AdminMoneySource existingEntity2 = moneySourceRepository.findByName(moneySourceRequestDto.getName());

        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("A MoneySource with the name '" + moneySourceRequestDto.getName() + "' already exists.");
        }




        System.out.println("1--------------------------------------------------------");

        // Map fields from request DTO to the entity
        existingEntity.setName(moneySourceRequestDto.getName());


        // Only update subscriptionEmail if it's provided in the request
//        if (moneySourceRequestDto.getEnabled() != null) {
            existingEntity.setEnabled(moneySourceRequestDto.getEnabled());
//        }
//
//

        // Fetch the 'modifiedBy' user from the database and set it
        if (moneySourceRequestDto.getModifiedBy() != null && moneySourceRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(moneySourceRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }

        // Save the updated entity
        AdminMoneySource updatedEntity = moneySourceRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


//
    private MoneySourceResponseDto convertToDTO(AdminMoneySource entity) {
        MoneySourceResponseDto dto = new MoneySourceResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setCreatedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
//        dto.setModifiedByUserName(entity.getModifiedBy().getFirstName());
        return dto;
    }



//    @Transactional
//    public MoneySourceResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
//        // Fetch the existing entity from the database
//        AdminMoneySource existingEntity = moneySourceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));
//
//        // Update only the 'enabled' field and keep all other fields unchanged
//        existingEntity.setEnabled(enabled);
//
//        // Save the updated entity back to the database
//        AdminMoneySource updatedEntity = moneySourceRepository.save(existingEntity);
//
//        // Convert the updated entity to a DTO and return
//        return convertToDTO(updatedEntity);
//    }
//






    @Transactional
    public MoneySourceResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminMoneySource existingEntity = moneySourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminMoneySource updatedEntity = moneySourceRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }









    @Transactional(readOnly = true)
    public Boolean getbyname(DuplicateNameCheckExistsDto duplicateNameCheckExistsDto) {

        return moneySourceRepository.findByName(duplicateNameCheckExistsDto.getName()) != null;
    }


}








