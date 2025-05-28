package com.idsargus.akpmsadminservice.ExcelDownloadDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
//import com.idsargus.akpmsadminservice.entity.AdminQcPointEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminQcPointDTO {
    private Integer id;
    private LocalDateTime created_on;
    private LocalDateTime modified_on;
    private Integer deleted;
    private String description;
    private String name;
    private Integer status;
    private Integer enabled;
    private Integer department_id;
    private Integer parent_id;
    private String sub_department_id;
    private String department_name;
    private String parent_name;
    private String Coding_prod_type;
    private  String created_by_user_name;
    private  String modified_by_user_name;


    public static AdminQcPointDTO toDTO(AdminQcPointEntityMvc adminQcPoint) {
        if (adminQcPoint == null) {
            return null;
        }

        AdminQcPointDTO dto = new AdminQcPointDTO();

        // Handle id
        dto.setId(adminQcPoint.getId());

        // Handle deleted
        dto.setDeleted(adminQcPoint.getDeleted() != null && adminQcPoint.getDeleted() ? 1 : 0);

        // Handle name
        dto.setName(adminQcPoint.getName() != null ? adminQcPoint.getName() : null);

        // Handle description
        dto.setDescription(adminQcPoint.getDescription() != null ? adminQcPoint.getDescription()   : " " );

        // Handle status
        dto.setStatus(adminQcPoint.getEnabled() != null && adminQcPoint.getEnabled() ? 1 : 1);

        // Handle enabled
        dto.setEnabled(adminQcPoint.getEnabled() != null && adminQcPoint.getEnabled() ? 1 : 0);

        // Handle created_on
        dto.setCreated_on(adminQcPoint.getCreatedOn());

        // Handle modified_on
        dto.setModified_on(adminQcPoint.getModifiedOn());

        // Handle department_id
        dto.setDepartment_id(adminQcPoint.getDepartmentId() != null ?  adminQcPoint.getDepartmentId() : null);

        // Handle parent_id
        dto.setParent_id(adminQcPoint.getParentId() != null ? adminQcPoint.getParentId() : null );

        // Handle sub_department_id
        dto.setSub_department_id(adminQcPoint.getSubDepartmentId() != null ? adminQcPoint.getSubDepartmentId() : null);

//        // Handle department_name
        dto.setDepartment_name(adminQcPoint.getDepartment() != null ? adminQcPoint.getDepartment().getName() : null);
//        dto.setCoding_prod_type(adminQcPoint.getCodingProdType() != null ? adminQcPoint.getCodingProdTypes().getName() :null); // changed to latest variable
        Integer codingProdType = adminQcPoint.getCodingProdType();
        String groupName = null;

        if (codingProdType != null) {
            switch (codingProdType) {
                case 1:
                    groupName = "Group A";
                    break;
                case 2:
                    groupName = "Group B";
                    break;
                case 3:
                    groupName = "Group C";
                    break;
                case 4:
                    groupName = "Hourly";
                    break;
                default:
                    groupName = null;
            }
        }

        dto.setCoding_prod_type(groupName);


        // Handle parent_name
        dto.setParent_name(adminQcPoint.getParent() != null ? adminQcPoint.getParent().getName() : null);
        dto.setCreated_by_user_name(adminQcPoint.getCreatedBy() != null  ? adminQcPoint.getCreatedBy().getFirstName() + " "+adminQcPoint.getCreatedBy().getLastName() : null);
        dto.setModified_by_user_name(adminQcPoint.getModifiedBy() != null ? adminQcPoint.getModifiedBy().getFirstName() + " "+adminQcPoint.getModifiedBy().getLastName() : null);
        return dto;
    }
}
