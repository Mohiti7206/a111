package com.idsargus.akpmsadminservice.ExcelDownloadDto;

//import com.idsargus.akpmsadminservice.entity.AdminDoctorEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorEntityMvc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DoctorDTO {
    private Integer id;
    private String name;
    private String doctor_code; // Maps to "doctor_code" in JSON
    private Float payments;
    private Float operations;
    private Float accounting;
    private Integer non_deposit; // Changed from Boolean to Integer to match JSON
    private Integer enabled; // Changed from Boolean to Integer to match JSON
    private Integer deleted; // Changed from Boolean to Integer to match JSON
    private LocalDateTime created_on; // Added to match JSON
    private LocalDateTime modified_on; // Added to match JSON
    private Integer group_id; // Maps to "group_id" in JSON
    private Integer company_id; // Maps to "company_id" in JSON
    private String doctor_group_name; // Maps to "doctor_group_name" in JSON
    private String doctor_company_name; // Maps to "doctor_company_name" in JSON
    private Integer status;
    private  String created_by_user_name;
    private  String modified_by_user_name;

    public static DoctorDTO fromEntity(AdminDoctorEntityMvc entity) {
        if (entity == null) {
            return null;
        }

        DoctorDTO dto = new DoctorDTO();
        dto.setId(entity.getId() != null ? entity.getId() : null);
        dto.setName(entity.getName() != null ? entity.getName() : null);
        dto.setDoctor_code(entity.getCode() != null ? entity.getCode() : null);
        dto.setPayments(entity.getPayments() != null ? entity.getPayments() : 0.0f);
        dto.setOperations(entity.getOperations() != null ? entity.getOperations() : 0.0f);
        dto.setAccounting(entity.getAccounting() != null ? entity.getAccounting() : 0.0f);
        dto.setNon_deposit(entity.getNonDeposit() != null && entity.getNonDeposit() ? 1 : 0); // Changed to Integer
        dto.setEnabled(entity.getEnabled() != null && entity.getEnabled() ? 1 : 0); // Changed to Integer
        dto.setDeleted(entity.getDeleted() != null && entity.getDeleted() ? 1 : 0); // Changed to Integer
        dto.setCreated_on(entity.getCreatedOn() != null ? entity.getCreatedOn() : null); // Added
        dto.setModified_on(entity.getModifiedOn() != null ? entity.getModifiedOn() : null); // Added
        dto.setGroup_id(entity.getGroup() != null ? entity.getGroup().getId() : null);
        dto.setCompany_id(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setDoctor_group_name(entity.getGroup() != null ? entity.getGroup().getName() : null);
        dto.setDoctor_company_name(entity.getCompany() != null ? entity.getCompany().getName() : null);
        dto.setStatus(entity.getEnabled() != null && entity.getEnabled() ? 1 : 0 );
//        dto.setCreated_by_user_name(entity.getCreatedByUserName());
        dto.setCreated_by_user_name(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " "+ entity.getCreatedBy().getLastName() : null);
        dto.setModified_by_user_name(entity.getModifiedBy() != null  ? entity.getModifiedBy().getFirstName() + " "+ entity.getModifiedBy().getLastName() : null);
//        dto.setModified_by_user_name(entity.getModifiedByUserName());

        return dto;
    }
}
