package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminRoleEntityMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminUserRequestDto {


//    private String firstName;
//    private Boolean enabled;
//    private AdminUserMvc modifiedBy;
//    private AdminUserMvc createdBy;


    @Schema(example = "John")
    @NotBlank(message = "firstName is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String firstName;



    @Schema(example = "Doe")
    private String lastName;



    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    @Schema(example = "{\"id\": 1 }" )
    private AdminUserMvc createdBy;



    @Schema(example = "{\"id\": 2 }" )
    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;



    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;



    @Schema(example = "john.doe@example.com" )
    @NotNull(message = "email field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String email;


    @Schema(example = "{\"id\": 1}" )
    @NotNull(message = "role field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private AdminRoleEntityMvc role;






    private String password;
    private String contact;
    private String address;
    private String location;


    @Schema(example = "{\"id\": 1 }" )
    private AdminArTeams arTeam;
    private Boolean deleted;
    //    private User modifiedBy;



//    public AdminUserRequestDto(String firstName, String lastName, String email, String password, String contact,
//                               String address, String location, AdminRoleEntityMvc role,
//                               AdminArTeams arTeam, Boolean enabled, Boolean deleted,
////                               User modifiedBy,
//                               AdminUserMvc createdBy ,
////                               User modifiedBy,
//                               AdminUserMvc createdBy
//    ) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.contact = contact;
//        this.address = address;
//        this.location = location;
//        this.role = role;
//        this.arTeam = arTeam;
//        this.enabled = enabled;
//        this.deleted = deleted;
//        this.modifiedBy = modifiedBy;
//        this.createdBy = createdBy;
//    }
//


    public AdminUserRequestDto(String firstName, String lastName, String email, String password, String contact, String address, String location,
                               AdminRoleEntityMvc role, AdminArTeams arTeam,
                               Boolean enabled, Boolean deleted, AdminUserMvc modifiedBy,
                               AdminUserMvc createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.location = location;
        this.role = role;
        this.arTeam = arTeam;
        this.enabled = enabled;
        this.deleted = deleted;
        this.modifiedBy = modifiedBy;
        this.createdBy = createdBy;
    }

    public AdminUserMvc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(AdminUserMvc modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public AdminUserMvc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AdminUserMvc createdBy) {
        this.createdBy = createdBy;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AdminArTeams getArTeam() {
        return arTeam;
    }

    public void setArTeam(AdminArTeams arTeam) {
        this.arTeam = arTeam;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AdminRoleEntityMvc getRole() {
        return role;
    }

    public void setRole(AdminRoleEntityMvc role) {
        this.role = role;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

//    public User getModifiedBy() {
//        return modifiedBy;
//    }

//    public void setModifiedBy(User modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//    public void setModifiedBy(User modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }

//    public User getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(User createdBy) {
//        this.createdBy = createdBy;
//    }
}
