package com.idsargus.akpmsadminservice.ExcelDownloadDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private Integer created_by;
    private LocalDateTime created_on;
    private Integer modified_by;
    private LocalDateTime modified_on;
//    private Boolean deleted;
    private Integer deleted;
    private String email;
//    private Boolean enabled;
    private Integer enabled;
    private String first_name;
    private String last_name;
    private String contact;
    private String address;
    private Integer role_id;
    private String role_name;
    private List<String> departments;
    private Integer ar_teams_id;
    private String ar_teams_name;
    private  String created_by_user_name;
    private  String modified_by_user_name;



    // Method to convert entity to DTO
    public static UserDTO toDTO(AdminUserMvc entity) {
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.setId(entity.getId() != null ? entity.getId() : null);
        dto.setAr_teams_name(entity.getArTeam() != null ? entity.getArTeam().getName() : null);
        dto.setRole_name(entity.getRole() != null ? entity.getRole().getName() : null);
        





        dto.setCreated_by(entity.getCreatedBy() != null && entity.getCreatedBy().getId() != null ?
                entity.getCreatedBy().getId() : null);

        dto.setCreated_on(entity.getCreatedOn() != null ? entity.getCreatedOn() : null);

        dto.setModified_by(entity.getModifiedBy() != null && entity.getModifiedBy().getId() != null ?
                entity.getModifiedBy().getId() : null);

        dto.setModified_on(entity.getModifiedOn() != null ? entity.getModifiedOn() : null);

        dto.setDeleted(entity.getDeleted() != null && entity.getDeleted() ? 1 : 0);

        dto.setEmail(entity.getEmail() != null ? entity.getEmail() : null);

        dto.setEnabled(entity.getEnabled() != null && entity.getEnabled() ? 1 : 0);

        dto.setFirst_name(entity.getFirstName() != null ? entity.getFirstName() : null);

        dto.setLast_name(entity.getLastName() != null ? entity.getLastName() : null);

        dto.setContact(entity.getContact() != null ? entity.getContact() : null);

        dto.setAddress(entity.getAddress() != null ? entity.getAddress() : null);

//        dto.setRole_id(entity.getRole() != null && entity.getRole().getId() != null ?
//                entity.getRole().getId() : null);
//
//        dto.setRole_name(entity.getRole() != null && entity.getRole().getName() != null ?
//                entity.getRole().getName() : null);

        List<AdminDepartmentEntityMvc> departmentEntities = entity.getDepartments() != null ?
                new ArrayList<>(entity.getDepartments()) :
                new ArrayList<>();

        List<String> departments = departmentEntities.stream()
                .map(department -> department.getName() != null ? department.getName() : "")
                .collect(Collectors.toList());

        dto.setDepartments(departments.isEmpty() ? Collections.emptyList() : departments);

        dto.setAr_teams_id(entity.getArTeam() != null && entity.getArTeam().getId() != null ?
                entity.getArTeam().getId() : null);


        dto.setCreated_by_user_name(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " "+entity.getCreatedBy().getLastName() : null);
        dto.setModified_by_user_name(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " "+entity.getModifiedBy().getLastName() : null);
        return dto;
    }
}
