package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminMoneySource;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminDepartmentRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDepartmentRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDepartmentResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.DeptResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//public class AdminDoctorService {
//}
@Service
public class AdminDepartmentService {


    private final AdminDepartmentRepository adminDepartmentRepository;

    public AdminDepartmentService(AdminDepartmentRepository adminDepartmentRepository) {
        this.adminDepartmentRepository = adminDepartmentRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<AdminDepartmentResponseDto> getAll1(
            String query,
            Pageable pageable,
            Boolean enabled
    ) {
        Page<AdminDepartmentResponseDto> result = adminDepartmentRepository.findAll1(
                query,
                enabled,
                pageable
        );

        return result;
    }




    @Transactional(readOnly = true)
    public List<DeptResponseDto> getalldepartments(
    ) {
        List<DeptResponseDto> result = adminDepartmentRepository.getalldepartments();
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







    public AdminDepartmentResponseDto addDepartment(AdminDepartmentRequestDto dto) {
        // Create a new entity from the DTO

        if (adminDepartmentRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An Company with the name '" + dto.getName() + "' already exists.");
        }

        AdminDepartmentEntityMvc doctor = new AdminDepartmentEntityMvc();

        doctor.setName(dto.getName());
        doctor.setEnabled(dto.getEnabled());
        doctor.setDeleted(dto.getDeleted());
        doctor.setDescription(dto.getDescription());
        doctor.setParent(dto.getParent());
        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        AdminDepartmentEntityMvc savedDoctor = adminDepartmentRepository.save(doctor);

        return new AdminDepartmentResponseDto(savedDoctor);
    }


    @Transactional
    public AdminDepartmentResponseDto updateTemplate(AdminDepartmentRequestDto adminDepartmentRequestDto, Integer id) {
        // Fetch the existing entity from the database

//        System.out.println("id =="+id);
        AdminDepartmentEntityMvc existingEntity = adminDepartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department with provided ID not found."));

        AdminDepartmentEntityMvc existingEntity2 = adminDepartmentRepository.findByName(adminDepartmentRequestDto.getName());

        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("A Department with the name '" + adminDepartmentRequestDto.getName() + "' already exists.");
        }

//        System.out.println("id =="+id);





//        System.out.println("1--------------------------------------------------------");

        // Update only non-null fields
          existingEntity.setName(adminDepartmentRequestDto.getName());
          existingEntity.setEnabled(adminDepartmentRequestDto.getEnabled());
          existingEntity.setDeleted(adminDepartmentRequestDto.getDeleted());
          existingEntity.setDescription(adminDepartmentRequestDto.getDescription());
//        if (adminDepartmentRequestDto.getParent() != null)
          existingEntity.setParent(adminDepartmentRequestDto.getParent());





//        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminDepartmentRequestDto.getModifiedBy() != null && adminDepartmentRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminDepartmentRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
//        System.out.println("4=========================================");

        // Save the updated entity
        AdminDepartmentEntityMvc updatedEntity = adminDepartmentRepository.save(existingEntity);
//        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


//    PATCH  http://localhost:5003/v1/adminapi/departments/update/27
//{
//    "name": "1",
//        "enabled": "true",
//        "userTimeZone": "IST",
//        "description": "asasa",
//        "deleted": true,
//        "parent": {
//    "id": 2,
//            "name": "mohit compan2y"
//},
//    //  "parent": {
//    // "id": null,
//    // "name": "mohit compan2y"
//    // },
//    "modifiedBy": {
//    "id": 847, // Replace 123 with the actual user ID
//            "firstName": "John" // Optional: Additional fields, if needed
//}
//}











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
    public AdminDepartmentResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminDepartmentEntityMvc existingEntity = adminDepartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminDepartmentEntityMvc  updatedEntity = adminDepartmentRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

//
//
    private AdminDepartmentResponseDto convertToDTO(AdminDepartmentEntityMvc entity) {
        AdminDepartmentResponseDto dto = new AdminDepartmentResponseDto();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setEnabled(entity.getEnabled());
//        dto.setCreatedOn(entity.getCreatedOn());
//        dto.setModifiedOn(entity.getModifiedOn());
//        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
//        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
        return dto;
    }


//    public Object getparentonly() {
//
//    }





















    @Transactional(readOnly = true)
    public List<AdminDepartmentResponseDto> getparentonly(

    ) {
        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findParentForAdmin();
        return result;
    }



    @Transactional(readOnly = true)
    public List<AdminDepartmentResponseDto> getsubdeptonly(
    ) {
        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findSubDepartment();
        return result;
    }







    @Transactional(readOnly = true)
    public List<AdminDepartmentResponseDto> getsubdeptbydept(Integer id
    ) {
        List<AdminDepartmentResponseDto> result = adminDepartmentRepository.findSubDepartmentByDeptId(id);
        return result;
    }


    @Transactional(readOnly = true)
    public  AdminDepartmentResponseDto findByNameForAdmin(String name) {
        AdminDepartmentResponseDto result = adminDepartmentRepository.findByNameForAdmin(name);
        return result;
    }

}

