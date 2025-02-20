package com.idsargus.akpmsadminservice.Mvc.Service;


import com.idsargus.akpmsadminservice.Mvc.Entities.AdminCodingProdTypeEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDepartmentRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.CodingGroupTypeRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminCodingGroupTypeService {


    private final CodingGroupTypeRepository codingGroupTypeRepository;

    public AdminCodingGroupTypeService(CodingGroupTypeRepository codingGroupTypeRepository) {
        this.codingGroupTypeRepository = codingGroupTypeRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<AdminCodingGroupTypeResponseDto> getAll1(
             Pageable pageable
     ) {
        Page<AdminCodingGroupTypeResponseDto> result = codingGroupTypeRepository.findAll1(
                pageable
        );

        return result;
    }










//POST http://localhost:5003/v1/adminapi/departments/add
//    {
//        "name": "modnkey",
//            "description": "urywiu",
//            "enabled": true,
//            "deleted": false,
//            "userTimeZone": "IST",
//            "parent": {
//        "id": 8,
//                "name": "abcd"
//    },
//        "createdBy": {
//        "id": 1,
//                "name": "John Doe"
//    }
//    }





//
//
    public AdminCodingGroupTypeResponseDto add (AdminCodingGroupTypeRequestDto dto) {
        // Create a new entity from the DTO

        if (codingGroupTypeRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An CodingGroupType with the name '" + dto.getName() + "' already exists.");
        }

        AdminCodingProdTypeEntity doctor = new AdminCodingProdTypeEntity();

        doctor.setName(dto.getName());
        doctor.setEnabled(dto.getEnabled());
         doctor.setTarget(dto.getTarget());
        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        AdminCodingProdTypeEntity savedDoctor = codingGroupTypeRepository.save(doctor);

        return new AdminCodingGroupTypeResponseDto(savedDoctor);
    }



    @Transactional
    public AdminCodingGroupTypeResponseDto update (AdminCodingGroupTypeRequestDto adminCodingGroupTypeRequestDto, Integer id) {
        // Fetch the existing entity from the database

//        System.out.println("id =="+id);
        AdminCodingProdTypeEntity existingEntity = codingGroupTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("codingGroupType with provided ID not found."));

        AdminCodingProdTypeEntity existingEntity2 = codingGroupTypeRepository.findByName(adminCodingGroupTypeRequestDto.getName());

        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("A codingGroupType with the name '" + adminCodingGroupTypeRequestDto.getName() + "' already exists.");
        }


//        System.out.println("1--------------------------------------------------------");

        // Update only non-null fields
         existingEntity.setName(adminCodingGroupTypeRequestDto.getName());
         existingEntity.setEnabled(adminCodingGroupTypeRequestDto.getEnabled());
         existingEntity.setTarget(adminCodingGroupTypeRequestDto.getTarget());





//        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminCodingGroupTypeRequestDto.getModifiedBy() != null && adminCodingGroupTypeRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminCodingGroupTypeRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
//        System.out.println("4=========================================");

        // Save the updated entity
        AdminCodingProdTypeEntity updatedEntity = codingGroupTypeRepository.save(existingEntity);
//        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }













//
//    @Transactional
//    public AdminDepartmentResponseDto updateTemplate(AdminDepartmentRequestDto adminDepartmentRequestDto, Integer id) {
//        // Fetch the existing entity from the database
//
////        System.out.println("id =="+id);
//        AdminDepartmentEntityMvc existingEntity = adminDepartmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Department with provided ID not found."));
//
//        AdminDepartmentEntityMvc existingEntity2 = adminDepartmentRepository.findByName(adminDepartmentRequestDto.getName());
//
//        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
//            throw new DuplicateNameException("A Department with the name '" + adminDepartmentRequestDto.getName() + "' already exists.");
//        }
//
////        System.out.println("id =="+id);
//
//
//
//
//
////        System.out.println("1--------------------------------------------------------");
//
//        // Update only non-null fields
//        if (adminDepartmentRequestDto.getName() != null) existingEntity.setName(adminDepartmentRequestDto.getName());
//        if (adminDepartmentRequestDto.getEnabled() != null) existingEntity.setEnabled(adminDepartmentRequestDto.getEnabled());
//        if (adminDepartmentRequestDto.getDeleted()!= null) existingEntity.setDeleted(adminDepartmentRequestDto.getDeleted());
//        if (adminDepartmentRequestDto.getDescription() != null) existingEntity.setDescription(adminDepartmentRequestDto.getDescription());
////        if (adminDepartmentRequestDto.getParent() != null)
//        existingEntity.setParent(adminDepartmentRequestDto.getParent());
//
//
//
//
//
////        System.out.println("3=========================================");
//
//        // Fetch the 'modifiedBy' user from the database and set it
//        if (adminDepartmentRequestDto.getModifiedBy() != null && adminDepartmentRequestDto.getModifiedBy().getId() != null) {
//            AdminUserMvc modifiedByUser = userRepository.findById(adminDepartmentRequestDto.getModifiedBy().getId())
//                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
//            existingEntity.setModifiedBy(modifiedByUser);
//        }
////        System.out.println("4=========================================");
//
//        // Save the updated entity
//        AdminDepartmentEntityMvc updatedEntity = adminDepartmentRepository.save(existingEntity);
////        System.out.println("5=========================================");
//
//        // Convert the updated entity to a DTO and return
//        return convertToDTO(updatedEntity);
//    }
//
//
////    PATCH  http://localhost:5003/v1/adminapi/departments/update/27
////{
////    "name": "1",
////        "enabled": "true",
////        "userTimeZone": "IST",
////        "description": "asasa",
////        "deleted": true,
////        "parent": {
////    "id": 2,
////            "name": "mohit compan2y"
////},
////    //  "parent": {
////    // "id": null,
////    // "name": "mohit compan2y"
////    // },
////    "modifiedBy": {
////    "id": 847, // Replace 123 with the actual user ID
////            "firstName": "John" // Optional: Additional fields, if needed
////}
////}
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
////
////
////
////
////
////
////
////
////
////
////
////
//
    @Transactional
    public AdminCodingGroupTypeResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminCodingProdTypeEntity existingEntity = codingGroupTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CodingGroupType with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminCodingProdTypeEntity  updatedEntity = codingGroupTypeRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }
//
//    //
////
    private AdminCodingGroupTypeResponseDto convertToDTO(AdminCodingProdTypeEntity entity) {
        AdminCodingGroupTypeResponseDto dto = new AdminCodingGroupTypeResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
        return dto;
    }

//
////    public Object getparentonly() {
////
////    }
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
//
//
//
//
//
//
//
//
//
//    @Transactional(readOnly = true)
//    public List<AdminDepartmentResponseDto> getparentonly(
//
//    ) {
//        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findParentForAdmin();
//        return result;
//    }
//
//
//
//    @Transactional(readOnly = true)
//    public List<AdminDepartmentResponseDto> getsubdeptonly(
//    ) {
//        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findSubDepartment();
//        return result;
//    }
//
//
//
//
//
//
//
//    @Transactional(readOnly = true)
//    public List<AdminDepartmentResponseDto> getsubdeptbydept(Integer id
//    ) {
//        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findSubDepartmentByDeptId(id);
//        return result;
//    }
//
//
//    @Transactional(readOnly = true)
//    public  AdminDepartmentResponseDto findByNameForAdmin(String name) {
//        AdminDepartmentResponseDto result = adminDepartmentRepository.findByNameForAdmin(name);
//        return result;
//    }














    @Transactional(readOnly = true)
    public List<AdminCodingGroupTypeResponseDto> findByAllEnabled(
    ) {
        List<AdminCodingGroupTypeResponseDto> result = codingGroupTypeRepository.findByAllEnabled();

        return result;
    }










}

