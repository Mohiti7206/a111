package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class AdminUserResponseDto {



    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
//    private  String name;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private String email;
    private String password;
    private String contact;
    private String address;
    private Boolean deleted;
    private Integer target;
    private String location;
    private Integer userRoleId;
    private String arTeamName;
    private String createdByUserName;
    private String modifiedByUserName;
//     private List<String> permissionIdList;
     private List<String> permissionIds;
     private List<Integer> departmentIds;
    private List<Integer> emailTemplateIds;

    public AdminUserResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn, String firstName, String lastName, Boolean enabled, String email, String password, String contact, String address, Boolean deleted, Integer target, String location, Integer userRoleId, String arTeamName,
                                String createdByUserName, String modifiedByUserName
//                                List<String> permissionIds, List<Integer> departmentIds, List<Integer> emailTemplateIds
    ) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.deleted = deleted;
        this.target = target;
        this.location = location;
        this.userRoleId = userRoleId;
        this.arTeamName = arTeamName;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
//        this.permissionIds = permissionIds;
//        this.departmentIds = departmentIds;
//        this.emailTemplateIds = emailTemplateIds;
    }

//    public AdminUserResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn,
//                                String name, Boolean enabled, String email, String password,
//                                String contact, String address, Boolean deleted,
//                                Integer target, String location,
//                                Integer userRoleId,
//                                String arTeamName, String createdByUserName, String modifiedByUserName
//     ) {
//        this.id = id;
//        this.createdOn = createdOn;
//        this.modifiedOn = modifiedOn;
//        this.name = name;
//        this.enabled = enabled;
//        this.email = email;
//        this.password = password;
//        this.contact = contact;
//        this.address = address;
//        this.deleted = deleted;
//        this.target = target;
//        this.location = location;
//        this.userRoleId = userRoleId;
//        this.arTeamName = arTeamName;
//        this.createdByUserName = createdByUserName;
//        this.modifiedByUserName = modifiedByUserName;
//    }

    public AdminUserResponseDto() {
    }

    public List<Integer> getEmailTemplateIds() {
        return emailTemplateIds;
    }

    public void setEmailTemplateIds(List<Integer> emailTemplateIds) {
        this.emailTemplateIds = emailTemplateIds;
    }

    public AdminUserResponseDto(AdminUserMvc entity) {
        this.id = entity.getId();
//        this.name = entity.getFirstName() + " "+entity.getLastName();
        this.firstName = entity.getFirstName() ;
        this.lastName = entity.getLastName()  ;
        this.enabled = entity.getEnabled();
        this.createdByUserName = entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName()+""+entity.getCreatedBy().getLastName() : null;
        this.createdOn = entity.getCreatedOn();
        this.modifiedOn = entity.getModifiedOn();
        this.email = entity.getEmail();
        this.contact = entity.getContact();
        this.location = entity.getLocation();
        this.deleted = entity.getDeleted();
        this.userRoleId = entity.getRole() != null ? entity.getRole().getId() : null;
        this.arTeamName = entity.getArTeam() != null ? entity.getArTeam().getName() : null;
    }


//    public AdminUserResponseDto(Integer id, LocalDateTime createdOn, LocalDateTime modifiedOn,
//                                String name, Boolean enabled, String email, String password,
//                                String contact, String address, Boolean deleted,
//                                Integer target, String location, Integer userRoleId,
//                                String createdByUserName, String modifiedByUserName
//    ) {
//        this.id = id;
//        this.createdOn = createdOn;
//        this.modifiedOn = modifiedOn;
//        this.name = name;
//        this.enabled = enabled;
//        this.email = email;
//        this.password = password;
//        this.contact = contact;
//        this.address = address;
//        this.deleted = deleted;
//        this.target = target;
//        this.location = location;
//        this.userRoleId = userRoleId;
//        this.createdByUserName = createdByUserName;
//        this.modifiedByUserName = modifiedByUserName;
//    }

    public String getArTeamName() {
        return arTeamName;
    }

    public void setArTeamName(String arTeamName) {
        this.arTeamName = arTeamName;
    }

//    public List<String> getPermissionIdList() {
//        return permissionIdList;
//    }
//
//    public void setPermissionIdList(List<String> permissionIdList) {
//        this.permissionIdList = permissionIdList;
//    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    public String getModifiedByUserName() {
        return modifiedByUserName;
    }

    public void setModifiedByUserName(String modifiedByUserName) {
        this.modifiedByUserName = modifiedByUserName;
    }
}
