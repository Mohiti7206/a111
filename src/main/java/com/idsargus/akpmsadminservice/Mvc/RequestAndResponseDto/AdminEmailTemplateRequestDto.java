package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.entity.User;
import com.idsargus.akpmscommonservice.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminEmailTemplateRequestDto {

//    private String name;
//
//    @NotNull
//    private Boolean enabled;
//    private AdminUserMvc modifiedBy;
//    private AdminUserMvc createdBy;








    @NotBlank(message = "Name is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private String name;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    @NotNull(message = "Enabled field is mandatory", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    private Boolean enabled;

    private String content;

    @NotNull
    private Boolean subscriptionEmail;



    @NotNull
    private Boolean status;

    @NotNull
    private Boolean isDeleted;

//    private User user; // Add this field
//    private UserEntity user; // Add this field




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

    public @NotNull Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(@NotNull Boolean deleted) {
        isDeleted = deleted;
    }

//    public UserEntity getUser() {
//        return user;
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }

    // Default constructor
    public AdminEmailTemplateRequestDto() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSubscriptionEmail() {
        return subscriptionEmail;
    }

    public void setSubscriptionEmail(Boolean subscriptionEmail) {
        this.subscriptionEmail = subscriptionEmail;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
