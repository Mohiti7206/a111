package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorCompanyRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCompanyResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorCompanyRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DuplicateNameCheckExistsDto;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//public class AdminDoctorService {
//}
@Service
public class AdminDoctorCompanyService {


    private final AdminDoctorCompanyRepository adminDoctorCompanyRepository;

    public AdminDoctorCompanyService(AdminDoctorCompanyRepository adminDoctorCompanyRepository) {
        this.adminDoctorCompanyRepository = adminDoctorCompanyRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<AdminCompanyResponseDto> getAll1(
            String query,
            Pageable pageable,
            Boolean enabled
    ) {
        Page<AdminCompanyResponseDto> result = adminDoctorCompanyRepository.findAll1(
                query,
                pageable,
                enabled
        );

        return result;
    }

















    public AdminCompanyResponseDto addCompany(AdminDoctorCompanyRequestDto dto) {
        // Create a new entity from the DTO

        if (adminDoctorCompanyRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Company with the name '" + dto.getName() + "' already exists.");
        }

        AdminDoctorCompanyEntityMvc doctor = new AdminDoctorCompanyEntityMvc();

        doctor.setName(dto.getName());
        doctor.setEnabled(dto.getEnabled());
        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        AdminDoctorCompanyEntityMvc savedDoctor = adminDoctorCompanyRepository.save(doctor);

        return new AdminCompanyResponseDto(savedDoctor);
    }


//
//
//
//
//

    @Transactional
    public AdminCompanyResponseDto updateTemplate(AdminDoctorCompanyRequestDto adminDoctorCompanyRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminDoctorCompanyEntityMvc existingEntity = adminDoctorCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company with provided ID not found."));

//        if (adminDoctorCompanyRepository.findByName(adminDoctorCompanyRequestDto.getName()) != null) {
//            throw new DuplicateNameException("An Company with the name '" + adminDoctorCompanyRequestDto.getName() + "' already exists.");
//        }

        AdminDoctorCompanyEntityMvc existingEntity2 = adminDoctorCompanyRepository.findByName(adminDoctorCompanyRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An Company with the name '" + adminDoctorCompanyRequestDto.getName() + "' already exists.");
        }
        System.out.println("1--------------------------------------------------------");

        // Update only non-null fields

          existingEntity.setEnabled(adminDoctorCompanyRequestDto.getEnabled());
          existingEntity.setName(adminDoctorCompanyRequestDto.getName());





        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminDoctorCompanyRequestDto.getModifiedBy() != null && adminDoctorCompanyRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminDoctorCompanyRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("4=========================================");

        // Save the updated entity
        AdminDoctorCompanyEntityMvc updatedEntity = adminDoctorCompanyRepository.save(existingEntity);
        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


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

    @Transactional
    public AdminCompanyResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminDoctorCompanyEntityMvc existingEntity = adminDoctorCompanyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminDoctorCompanyEntityMvc  updatedEntity = adminDoctorCompanyRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

//
//
    private AdminCompanyResponseDto convertToDTO(AdminDoctorCompanyEntityMvc entity) {
        AdminCompanyResponseDto dto = new AdminCompanyResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
        return dto;
    }










    @Transactional(readOnly = true)
    public List<AdminCompanyResponseDto> getallCompanies(
             Boolean enabled
    ) {
        List<AdminCompanyResponseDto> result = adminDoctorCompanyRepository.getallCompanies(
                enabled
        );

        return result;
    }






    @Transactional(readOnly = true)
    public Boolean getbyname(DuplicateNameCheckExistsDto duplicateNameCheckExistsDto) {

        return adminDoctorCompanyRepository.findByName(duplicateNameCheckExistsDto.getName()) != null;
    }



}

