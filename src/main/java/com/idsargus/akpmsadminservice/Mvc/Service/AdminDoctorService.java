package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorCompanyRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDoctorGroupRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.DoctorRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminCompanyResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DoctorGroupResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DoctorRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

//public class AdminDoctorService {
//}
@Service
public class AdminDoctorService {


    private final DoctorRepository doctorRepository;

    public AdminDoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<AdminDoctorResponse> getAll1(
            String query,
            Pageable pageable,
            Boolean enabled,
            Boolean deleted
    ) {
        Page<AdminDoctorResponse> result = doctorRepository.findAll1(
                query,
                pageable,
                enabled,
                deleted
        );

        return result;
    }

















    public AdminDoctorResponse adddoctor(DoctorRequestDTO dto) {
        // Create a new entity from the DTO



        if (doctorRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Doctor with the name '" + dto.getName() + "' already exists.");
        }

        AdminDoctorEntityMvc doctor = new AdminDoctorEntityMvc();

        doctor.setName(dto.getName());
        doctor.setCode(dto.getCode());
        doctor.setAccounting(dto.getAccounting());
        doctor.setOperations(dto.getOperations());
        doctor.setPayments(dto.getPayments());
        doctor.setNonDeposit(dto.getNonDeposit());
        doctor.setEnabled(dto.getEnabled());
        doctor.setDeleted(dto.getDeleted());
        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        if (dto.getCompany() != null) {
            doctor.setCompany(dto.getCompany());
        }

        if (dto.getGroup() != null) {
            doctor.setGroup(dto.getGroup());
        }

        if (dto.getParent() != null) {
            doctor.setParent(dto.getParent());
        }
        AdminDoctorEntityMvc savedDoctor = doctorRepository.save(doctor);

        return new AdminDoctorResponse(savedDoctor);
    }








    @Transactional
    public AdminDoctorResponse updateTemplate(DoctorRequestDTO doctorRequestDTO, Integer id) {
        // Fetch the existing entity from the database
        AdminDoctorEntityMvc existingEntity = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with provided ID not found."));



        AdminDoctorEntityMvc existingEntity2 = doctorRepository.findByName(doctorRequestDTO.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An Doctor with the name '" + doctorRequestDTO.getName() + "' already exists.");
        }







         existingEntity.setEnabled(doctorRequestDTO.getEnabled());
         existingEntity.setStatus(doctorRequestDTO.getEnabled());

        System.out.println("1--------------------------------------------------------");

        // Update only non-null fields
         existingEntity.setCode(doctorRequestDTO.getCode());
         existingEntity.setAccounting(doctorRequestDTO.getAccounting());
         existingEntity.setOperations(doctorRequestDTO.getOperations());
         existingEntity.setPayments(doctorRequestDTO.getPayments());
         existingEntity.setNonDeposit(doctorRequestDTO.getNonDeposit());
//         existingEntity.setEnabled(doctorRequestDTO.getEnabled()); //
         existingEntity.setDeleted(doctorRequestDTO.getDeleted());//
         existingEntity.setName(doctorRequestDTO.getName());//
//
////
////        System.out.println("2=========================================");
//////        existingEntity.setName(doctorRequestDTO.getName());
         existingEntity.setCompany(doctorRequestDTO.getCompany());//
         existingEntity.setGroup(doctorRequestDTO.getGroup());//
//        if (doctorRequestDTO.getParent() != null) {existingEntity.setParent(doctorRequestDTO.getParent());}


        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (doctorRequestDTO.getModifiedBy() != null && doctorRequestDTO.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(doctorRequestDTO.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
        System.out.println("4=========================================");

        // Save the updated entity
        AdminDoctorEntityMvc updatedEntity = doctorRepository.save(existingEntity);
        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }










    @Transactional
    public AdminDoctorResponse updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminDoctorEntityMvc existingEntity = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminDoctorEntityMvc updatedEntity = doctorRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }



    private AdminDoctorResponse convertToDTO(AdminDoctorEntityMvc entity) {


        System.out.println("111");
        AdminDoctorResponse dto = new AdminDoctorResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        System.out.println("112");

        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        System.out.println("113");

        return dto;
    }




//    public Object findByDoctorCompanygroup(String name, Integer groupId, Integer companyId) {
//    }






    @Transactional(readOnly = true)
    public List<AdminDoctorResponse> findByAllEnabled(
    ) {
        List<AdminDoctorResponse> result = doctorRepository.findByAllEnabled();
        return result;
    }




    @Autowired
    private AdminDoctorCompanyRepository adminDoctorCompanyRepository;

    @Autowired
    private AdminDoctorGroupRepository adminDoctorGroupRepository;







    @Transactional(readOnly = true)
    public AdminDoctorCompanyEntityMvc findCompanyByDoctorId(Integer doctorId) {
        return doctorRepository.findCompanyByDoctorId(doctorId).orElseThrow(() -> new EntityNotFoundException("Company not found for doctor ID: " + doctorId));
    }

    @Transactional(readOnly = true)
    public AdminDoctorGroupMvc findGroupByDoctorId(Integer doctorId) {
        return doctorRepository.findGroupByDoctorId(doctorId).orElseThrow(() -> new EntityNotFoundException("Group not found for doctor ID: " + doctorId));
    }


// @Transactional(readOnly = true)
//    public AdminDoctorGroupMvc findByCompanyAndGroup(Integer groupId,Integer companyId) {
//        return doctorRepository.findByCompanyAndGroup(groupId,companyId);
//    }


    @Transactional(readOnly = true)
    public List<AdminDoctorResponse> findByCompanyAndGroup(Integer groupId,Integer companyId
    ) {
        List<AdminDoctorResponse> result = doctorRepository.findByCompanyAndGroup(groupId,companyId);
        return result;
    }











}

