package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;//package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;//package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;
////
////import lombok.Getter;
////import lombok.Setter;
////import java.time.LocalDateTime;
////import java.util.List;
////
////@Getter
////@Setter
////public class AdminQcPointResponseDto {
////
////    private Integer id;
////    private LocalDateTime createdOn;
////    private LocalDateTime modifiedOn;
////    private String name;
////    private Boolean enabled;
////    private String description;
////    private String subDepartmentId;
////    private Boolean deleted;
////    private Integer parentId;
////    private Integer departmentId;
////    private String parentName;
////    private String departmentName;
////    private Integer codingProdType;
////    private String codingProdTypes;
////    private String createdByUserName;
////    private String modifiedByUserName;
////    private List<Integer> listOfChildQcPointsIds;
////
////
////
////
////    public AdminQcPointResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn,
////                                   String name, Boolean enabled, String description,
////                                   String subDepartmentId, Boolean deleted, Integer parentId,
////                                   Integer departmentId, String parentName,
////                                   String departmentName, Integer codingProdType,
////                                   String codingProdTypes,
////                                   String createdByUserName, String modifiedByUserName
////                                   ,List<Integer> listOfChildQcPointsIds
////    ) {
////        this.id = id;
////        this.createdOn = createdOn;
////        this.modifiedOn = modifiedOn;
////        this.name = name;
////        this.enabled = enabled;
////        this.description = description;
////        this.subDepartmentId = subDepartmentId;
////        this.deleted = deleted;
////        this.parentId = parentId;
////        this.departmentId = departmentId;
////        this.parentName = parentName;
////        this.departmentName = departmentName;
////        this.codingProdType = codingProdType;
////        this.codingProdTypes = codingProdTypes;
////        this.createdByUserName = createdByUserName;
////        this.modifiedByUserName = modifiedByUserName;
////        this.listOfChildQcPointsIds = listOfChildQcPointsIds;
////
////    }
////
////    public Integer getId() {
////        return id;
////    }
////
////    public void setId(Integer id) {
////        this.id = id;
////    }
////
////    public LocalDateTime getCreatedOn() {
////        return createdOn;
////    }
////
////    public void setCreatedOn(LocalDateTime createdOn) {
////        this.createdOn = createdOn;
////    }
////
////    public LocalDateTime getModifiedOn() {
////        return modifiedOn;
////    }
////
////    public void setModifiedOn(LocalDateTime modifiedOn) {
////        this.modifiedOn = modifiedOn;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public Boolean getEnabled() {
////        return enabled;
////    }
////
////    public void setEnabled(Boolean enabled) {
////        this.enabled = enabled;
////    }
////
////    public String getCreatedByUserName() {
////        return createdByUserName;
////    }
////
////    public void setCreatedByUserName(String createdByUserName) {
////        this.createdByUserName = createdByUserName;
////    }
////
////    public String getModifiedByUserName() {
////        return modifiedByUserName;
////    }
////
////    public void setModifiedByUserName(String modifiedByUserName) {
////        this.modifiedByUserName = modifiedByUserName;
////    }
////}
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminDepartmentEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminQcPointEntityMvc;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AdminQcPointResponseDto {

    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private Boolean enabled;
    private String description;
    private String subDepartmentId;
    private Boolean deleted;
    private Integer parentId;
    private Integer departmentId;
    private String parentName;
    private String departmentName;
    private Integer codingProdType;
    private String codingProdTypes;
    private String createdByUserName;
    private String modifiedByUserName;

    private List<String> listOfChildQcPointsIds;
    private List<String> listOfChildQcPointsNames;
    private String codingGroupTypeName;

    public AdminQcPointResponseDto(
            Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn, String name,
            Boolean enabled, String description, String subDepartmentId, Boolean deleted,
            Integer parentId, Integer departmentId, String parentName, String departmentName,
            Integer codingProdType,
            String codingProdTypes,
            String createdByUserName,
            String modifiedByUserName,
            String listOfChildQcPointsIds,
            String listOfChildQcPointsNames,
            String  codingGroupTypeName

            ) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.enabled = enabled;
        this.description = description;
        this.subDepartmentId = subDepartmentId;
        this.deleted = deleted;
        this.parentId = parentId;
        this.departmentId = departmentId;
        this.parentName = parentName;
        this.departmentName = departmentName;
        this.codingProdType = codingProdType;
        this.codingProdTypes = codingProdTypes;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
        this.codingGroupTypeName = codingGroupTypeName;


        // ✅ Java 8 Compatible String to List Conversion
        this.listOfChildQcPointsIds = (listOfChildQcPointsIds != null && !listOfChildQcPointsIds.isEmpty())
                ? Arrays.asList(listOfChildQcPointsIds.split(","))
                : null;


        this.listOfChildQcPointsNames = (listOfChildQcPointsNames != null && !listOfChildQcPointsNames.isEmpty())
                ? Arrays.asList(listOfChildQcPointsNames.split(","))
                : null;
    }

    public AdminQcPointResponseDto() {

    }










    public AdminQcPointResponseDto(AdminQcPointEntityMvc entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.createdByUserName = entity.getCreatedBy().getFirstName()+" "+entity.getCreatedBy().getLastName();
        this.createdOn = entity.getCreatedOn();
        this.modifiedOn = entity.getModifiedOn();
    }
}
