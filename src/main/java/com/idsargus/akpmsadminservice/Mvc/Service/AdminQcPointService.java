package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminPaymentTypeMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminQcPointRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import com.idsargus.akpmsadminservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminQcPointService {

    private final AdminQcPointRepository adminQcPointRepository;

    public AdminQcPointService(AdminQcPointRepository adminQcPointRepository) {
        this.adminQcPointRepository = adminQcPointRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

//    @Transactional(readOnly = true)
//    public Page<AdminQcPointResponseDto> getAll1(
//            String query,
//            Pageable pageable,
//            Boolean enabled
//    ) {
//        Page<AdminQcPointResponseDto> result = adminQcPointRepository.findAll1(
////                query,
////                enabled,
//                pageable
//        );
//start
//        public List<AdminQcPointResponseDto> getAllQcPoints(String columnName) {
//
//
//
//
////            public Page<?> getQcPoints(String sortBy, String sortDirection, int page, int size) {
//                 String sortDirection="desc";
//                String sortBy="id";
//                Sort.Direction direction = Sort.Direction.ASC;
//                if (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) {
//                    direction = Sort.Direction.DESC;
//                }
//
//                Sort sort = Sort.by(direction, sortBy);  // Use sortBy directly - Spring Data JPA handles this safely
//                Pageable pageable = PageRequest.of(0, 500, sort);
//
////                return qcPointRepository.findAll(pageable);
//
//
//
//
//
//
//            List<Object[]> results = (List<Object[]>) adminQcPointRepository.findAllQcPointsNative("id",pageable);
//            return results.stream()
//                    .map(obj -> new AdminQcPointResponseDto(
//                            obj[0] != null ? ((BigInteger) obj[0]).intValue() : null,  // id - Handle null
//                            obj[1] != null ? ((Timestamp) obj[1]).toLocalDateTime() : null,  // createdOn - Handle null
//                            obj[2] != null ? ((Timestamp) obj[2]).toLocalDateTime() : null,  // modifiedOn - Handle null
//                            (String) obj[3],  // name
//                            (Boolean) obj[4],  // enabled
//                            (String) obj[5],  // description
//                            (String) obj[6],  // subDepartmentId
//                            (Boolean) obj[7],  // deleted
//                            obj[8] != null ? ((BigInteger) obj[8]).intValue() : null,  // parentId - Handle null
//                            obj[9] != null ? ((BigInteger) obj[9]).intValue() : null,  // departmentId - Handle null
//                            (String) obj[10],  // parentName
//                            (String) obj[11],  // departmentName
//                            obj[12] != null ? ((BigInteger) obj[12]).intValue() : null,  // codingProdType - Handle null
//                            (String) obj[13],  // codingProdTypes
//                            (String) obj[14],  // createdByUserName
//                            (String) obj[15],  // modifiedByUserName
//                            obj[16] != null ? Arrays.asList(((String) obj[16]).split(",")).toString() : null,  // Convert child names to List<String>
//                            obj[17] != null ? Arrays.asList(((String) obj[17]).split(",")).toString() : null,  // Convert child names to List<String>
//                            (String) obj[18]  // codingGroupTypeName
//                    ))
//                    .collect(Collectors.toList());
//
//        }

    //end




    public Page<AdminQcPointResponseDto> getAllQcPoints(String columnName, String sortDirection1 ,Integer page,
                                                        Integer size,
                                                        String query,
                                                        Boolean enabled,
                                                        Integer departmentId) {

        String sortDirection = sortDirection1;
        String sortBy = columnName;
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        System.out.println("columnname = "+ columnName);

        Page<Object[]> resultsPage = adminQcPointRepository.findAllQcPointsNative(columnName,sortDirection, pageable,query,enabled,departmentId);
        List<Object[]> results = resultsPage.getContent();

        List<AdminQcPointResponseDto> dtoList = results.stream()
                .map(obj -> new AdminQcPointResponseDto(
                        obj[0] != null ? ((Number) obj[0]).intValue() : null,
                        obj[1] != null ? ((Timestamp) obj[1]).toLocalDateTime() : null,
                        obj[2] != null ? ((Timestamp) obj[2]).toLocalDateTime() : null,
                        (String) obj[3],
                        (Boolean) obj[4],
                        (String) obj[5],
                        (String) obj[6],
                        (Boolean) obj[7],
                        obj[8] != null ? ((Number) obj[8]).intValue() : null,
                        obj[9] != null ? ((Number) obj[9]).intValue() : null,
                        (String) obj[10],
                        (String) obj[11],
                        obj[12] != null ? ((Number) obj[12]).intValue() : null,
                        (String) obj[13],
                        (String) obj[14],
                        (String) obj[15],
                        obj[16] != null ? Arrays.asList(((String) obj[16]).split(",")).toString() : null,  // Convert child names to List<String>
                        obj[17] != null ? Arrays.asList(((String) obj[17]).split(",")).toString() : null,  // Convert child names to List<String>
                        (String) obj[18]
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, resultsPage.getTotalElements());
    }













    @Transactional
    public AdminQcPointResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminQcPointEntityMvc existingEntity = adminQcPointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QcPoint with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminQcPointEntityMvc  updatedEntity = adminQcPointRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }





    private AdminQcPointResponseDto convertToDTO(AdminQcPointEntityMvc entity) {
        AdminQcPointResponseDto dto = new AdminQcPointResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
        return dto;
    }






    public AdminQcPointResponseDto addQcPoint(AdminQcPointRequestDto dto) {

        if (adminQcPointRepository.findByName(dto.getName()) != null) {
            throw new DuplicateNameException("An QcPoint with the name '" + dto.getName() + "' already exists.");
        }
        AdminQcPointEntityMvc doctor = new AdminQcPointEntityMvc();
        doctor.setName(dto.getName());
        doctor.setDescription(dto.getDescription());

        doctor.setEnabled(dto.getEnabled());
        doctor.setDeleted(dto.getDeleted());
       // Integer cptype = Integer.valueOf(dto.getCodingProdType());
        String codingProdTypeStr = dto.getCodingProdType();
        Integer cptype = null;

        if (codingProdTypeStr != null && !codingProdTypeStr.trim().isEmpty()) {
            try {
                cptype = Integer.valueOf(codingProdTypeStr);
            } catch (NumberFormatException e) {
                // Optional: log this issue or handle it if invalid input should be tracked
                // e.g., logger.warn("Invalid codingProdType: {}", codingProdTypeStr, e);
            }
        }


        doctor.setCodingProdType(cptype);
        doctor.setSubDepartmentId(dto.getSubDepartmentId());
        doctor.setParentId(dto.getParentId());
        doctor.setDepartmentId(dto.getDepartmentId());

        if (dto.getCreatedBy() != null) {
            doctor.setCreatedBy(dto.getCreatedBy());
        }
        AdminQcPointEntityMvc response = adminQcPointRepository.save(doctor);
        return new AdminQcPointResponseDto(response);
    }






    @Transactional(readOnly = true)
    public List<AdminQcPointResponseDto> getparentonly(
    ) {
        List<AdminQcPointResponseDto> result = adminQcPointRepository.findParentForAdmin();
        return result;
    }
















    @Transactional
    public AdminQcPointResponseDto updateTemplate(AdminQcPointRequestDto adminQcPointRequestDto, Integer id) {
        // Fetch the existing entity from the database
//        System.out.println("id =="+id);
        AdminQcPointEntityMvc existingEntity = adminQcPointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QcPoint with provided ID not found."));

//        if (adminQcPointRepository.findByName(adminQcPointRequestDto.getName()) != null) {
//            throw new DuplicateNameException("An QcPoint with the name '" + adminQcPointRequestDto.getName() + "' already exists.");
//        }

        AdminQcPointEntityMvc existingEntity2 = adminQcPointRepository.findByName(adminQcPointRequestDto.getName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An QcPoint with the name '" + adminQcPointRequestDto.getName() + "' already exists.");
        }











        // Update only non-null fields
        existingEntity.setName(adminQcPointRequestDto.getName());
       existingEntity.setEnabled(adminQcPointRequestDto.getEnabled());
        existingEntity.setDeleted(adminQcPointRequestDto.getDeleted());
          existingEntity.setDescription(adminQcPointRequestDto.getDescription());
         existingEntity.setParentId(adminQcPointRequestDto.getParentId());
        Integer cptype = Integer.valueOf(adminQcPointRequestDto.getCodingProdType());
         existingEntity.setCodingProdType(cptype);


//        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminQcPointRequestDto.getModifiedBy() != null && adminQcPointRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminQcPointRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
//        System.out.println("4=========================================");

        // Save the updated entity
        AdminQcPointEntityMvc updatedEntity = adminQcPointRepository.save(existingEntity);
//        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }




    @Transactional(readOnly = true)
    public Boolean getbyname(DuplicateNameCheckExistsDto duplicateNameCheckExistsDto) {

        return adminQcPointRepository.findByName(duplicateNameCheckExistsDto.getName()) != null;
    }





//
//    private AdminQcPointResponseDto convertToDTO(AdminQcPointEntityMvc entity) {
//        AdminQcPointResponseDto dto = new AdminQcPointResponseDto();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setEnabled(entity.getEnabled());
//        dto.setCreatedOn(entity.getCreatedOn());
//        dto.setModifiedOn(entity.getModifiedOn());
//        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
//        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
//        return dto;
//    }


}
//    PATCH 'http://localhost:5003/v1/adminapi/qcpoints/update/18' \
//
//        {
//        "name": "22",
//        "description": "22",
//        "enabled": false,
//        "userTimeZone": "IST",
//        "deleted": true,
//        "codingProdType": "2",
//        "subDepartmentId": "1,2,3",
//        "parentId": 2,
//        "departmentId": 2,
//
//        "modifiedBy": {
//        "id": 2,
//        "name": "John Doe"
//        }
//        }


//PATCH 'http://localhost:5003/v1/adminapi/qcpoints/updateTemplateActivationStatus/18'
//{
//        "enabled": true
//        }


// POST http://localhost:5003/v1/adminapi/qcpoints/add
//       {
//        "name": "kk1",
//        "description": "hhju",
//        "enabled": true,
//        "userTimeZone": "IST",
//        "deleted": false,
//        "codingProdType": "2",
//        "subDepartmentId": "6,7,9",
//        "parentId": 27,
//        "departmentId": 1,
//        "createdBy": {
//        "id": 1,
//        "name": "John Doe"
//        }
//        }






















//public List<AdminQcPointResponseDto> getparentonly() {
//
//
//    List resultsPage = adminQcPointRepository.findParentForAdmin( );
//
//
//    return resultsPage;
//}












