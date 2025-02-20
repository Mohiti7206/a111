package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.*;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorGroupRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.entity.User;
import com.idsargus.akpmscommonservice.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminDoctorGroupService {

    private final AdminDoctorGroupRepository adminDoctorGroupRepository;

    public AdminDoctorGroupService(AdminDoctorGroupRepository adminDoctorGroupRepository) {
        this.adminDoctorGroupRepository = adminDoctorGroupRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<DoctorGroupResponse> getAll1(
            String query,
            Boolean enabled,
            Pageable pageable

            ) {
        Page<DoctorGroupResponse> result = adminDoctorGroupRepository.findAll1(
                query,
                enabled,
                pageable
        );


        return result;
    }














    public DoctorGroupResponse addgroup(AdminDoctorGroupRequestDto dto) {
        // Create a new entity from the DTO

        if (adminDoctorGroupRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Group with the name '" + dto.getName() + "' already exists.");
        }
        AdminDoctorGroupMvc doctor = new AdminDoctorGroupMvc();
        doctor.setName(dto.getName());
        doctor.setEnabled(dto.getEnabled());

        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        if (dto.getCompany() != null) {
            doctor.setCompany(dto.getCompany());
        }
        AdminDoctorGroupMvc savedDoctor = adminDoctorGroupRepository.save(doctor);
        return new DoctorGroupResponse(savedDoctor);
    }













    @Transactional
    public DoctorGroupResponse updateTemplate(AdminDoctorGroupRequestDto adminDoctorGroupRequestDto, Integer id) {
        // Fetch the existing entity from the database
        AdminDoctorGroupMvc existingEntity = adminDoctorGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group with provided ID not found."));


        AdminDoctorGroupMvc existingEntity2 = adminDoctorGroupRepository.findByName(adminDoctorGroupRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An Group with the name '" + adminDoctorGroupRequestDto.getName() + "' already exists.");
        }



        System.out.println("1--------------------------------------------------------");

        // Update only non-null fields
         existingEntity.setName(adminDoctorGroupRequestDto.getName());
         existingEntity.setEnabled(adminDoctorGroupRequestDto.getEnabled());

         existingEntity.setCompany(adminDoctorGroupRequestDto.getCompany());
//        if (doctorRequestDTO.getParent() != null) {existingEntity.setParent(doctorRequestDTO.getParent());}


        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminDoctorGroupRequestDto.getModifiedBy() != null && adminDoctorGroupRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminDoctorGroupRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("4=========================================");

        // Save the updated entity
        AdminDoctorGroupMvc updatedEntity = adminDoctorGroupRepository.save(existingEntity);
        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }




    private DoctorGroupResponse convertToDTO(AdminDoctorGroupMvc entity) {
        DoctorGroupResponse dto = new DoctorGroupResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName(): null);
        return dto;
    }





    @Transactional
    public DoctorGroupResponse updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminDoctorGroupMvc existingEntity = adminDoctorGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("group with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);
        existingEntity.setModifiedOn(null);


//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("Principal value: " + principal);
//        String username = adminDoctorGroupRepository.getUserNameByEmailId(principal.toString());
//        User u = new User(username);
//        existingEntity.setModifiedBy(u);


//        existingEntity.setModifiedBy();
        System.out.println("hello3");

        // Save the updated entity back to the database
        AdminDoctorGroupMvc updatedEntity = adminDoctorGroupRepository.save(existingEntity);
        System.out.println("hello4");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }



//    @Transactional
//    public ArTeamsResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
//        // Fetch the existing entity from the database
//        AdminArTeams existingEntity = adminArTeamsRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Template with provided ID not found."));
//
//        // Update only the 'enabled' field and keep all other fields unchanged
//        existingEntity.setEnabled(enabled);
//
//        // Save the updated entity back to the database
//        AdminArTeams updatedEntity = adminArTeamsRepository.save(existingEntity);
//
//        // Convert the updated entity to a DTO and return
//        return convertToDTO(updatedEntity);
//    }








    @Transactional(readOnly = true)
    public List<DoctorGroupResponse> getcompanyGroup(
            Integer cid  ,
            Boolean enabled


    ) {
        List<DoctorGroupResponse> result = adminDoctorGroupRepository.getCompanyGroup(
                cid,
                enabled
        );


        return result;
    }













}