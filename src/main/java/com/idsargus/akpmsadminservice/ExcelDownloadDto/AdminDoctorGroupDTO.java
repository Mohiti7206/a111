package com.idsargus.akpmsadminservice.ExcelDownloadDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorGroupMvc;
//import com.idsargus.akpmsadminservice.entity.AdminDoctorGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class AdminDoctorGroupDTO {
    private Integer id;
    private String name;
    private Integer enabled;
    private LocalDateTime created_on;
    private LocalDateTime modified_on;
    private Integer doctor_company_id;
    private String doctor_company_name;
    private  String created_by_user_name;
    private  String modified_by_user_name;

    public static AdminDoctorGroupDTO toDTO(AdminDoctorGroupMvc adminDoctorGroup) {
        if (adminDoctorGroup == null) {
            return null;
        }

        AdminDoctorGroupDTO dto = new AdminDoctorGroupDTO();

        // Handle id
        dto.setId(adminDoctorGroup.getId());
//        System.out.println("in the AdminDoctorGroupDTO 1");

        // Handle name
        dto.setName(adminDoctorGroup.getName() != null ? adminDoctorGroup.getName() : null);

        // Handle enabled
        dto.setEnabled(adminDoctorGroup.getEnabled() != null && adminDoctorGroup.getEnabled() ? 1 : 0);

        // Handle created_on
        dto.setCreated_on(adminDoctorGroup.getCreatedOn() != null ? adminDoctorGroup.getCreatedOn() : null);

        // Handle modified_on
        dto.setModified_on(adminDoctorGroup.getModifiedOn() != null ? adminDoctorGroup.getModifiedOn() : null);

        // Handle doctor_company_id
        dto.setDoctor_company_id(adminDoctorGroup.getCompany() != null ? adminDoctorGroup.getCompany().getId() : null);

        // Handle doctor_company_name
        dto.setDoctor_company_name(adminDoctorGroup.getCompany() != null ? adminDoctorGroup.getCompany().getName() : null);
        dto.setCreated_by_user_name(adminDoctorGroup.getCreatedBy() != null ? adminDoctorGroup.getCreatedBy().getFirstName() + " "+ adminDoctorGroup.getCreatedBy().getLastName() : null);
        dto.setModified_by_user_name(adminDoctorGroup.getModifiedBy() != null ? adminDoctorGroup.getModifiedBy().getFirstName() + " "+adminDoctorGroup.getModifiedBy().getLastName() : null);
        return dto;
    }
}
