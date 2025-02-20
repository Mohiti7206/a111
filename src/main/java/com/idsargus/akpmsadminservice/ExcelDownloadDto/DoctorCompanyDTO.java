package com.idsargus.akpmsadminservice.ExcelDownloadDto;


//import com.idsargus.akpmsadminservice.entity.AdminDoctorCompanyEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDoctorCompanyEntityMvc;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class DoctorCompanyDTO {
    private Integer id;
    private String name;
    private Integer enabled;
    private Integer created_by;
    private Integer modified_by;
    private LocalDateTime created_on;
    private LocalDateTime modified_on;
    private  String created_by_user_name;
    private  String modified_by_user_name;



    public static DoctorCompanyDTO toDTO(AdminDoctorCompanyEntityMvc entity) {
        if (entity == null) {
            return null;
        }

        DoctorCompanyDTO dto = new DoctorCompanyDTO();

        // Handle id
        dto.setId(entity.getId());

        // Handle name
        dto.setName(entity.getName() != null ? entity.getName() : null);

        // Handle enabled
        dto.setEnabled(entity.getEnabled() != null && entity.getEnabled() ? 1 : 0);

        // Handle created_by
        dto.setCreated_by(entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null);

        // Handle modified_by
        dto.setModified_by(entity.getModifiedBy() != null ? entity.getModifiedBy().getId() : null);

        // Handle created_on
        dto.setCreated_on(entity.getCreatedOn() != null ? entity.getCreatedOn() : null);

        // Handle modified_on
        dto.setModified_on(entity.getModifiedOn() != null ? entity.getModifiedOn() : null);
        dto.setCreated_by_user_name(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName()+" "+ entity.getCreatedBy().getLastName():null);
        dto.setModified_by_user_name(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " "+entity.getModifiedBy().getLastName():null);



        return dto;
    }
}
