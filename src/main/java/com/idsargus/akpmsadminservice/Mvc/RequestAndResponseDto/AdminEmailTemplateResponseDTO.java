package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminEmailTemplateEntity2;

import java.time.LocalDateTime;

public class AdminEmailTemplateResponseDTO {
    private Integer id;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private String name;
    private String content;
    private Boolean subscriptionEmail;
    private Boolean enabled;
    private Boolean status;
    private Boolean is_deleted;
    private String createdByUserName;
    private String modifiedByUserName;

    public AdminEmailTemplateResponseDTO() {
    }
    // Constructor accepting AdminEmailTemplateEntity2
    public AdminEmailTemplateResponseDTO(AdminEmailTemplateEntity2 entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.subscriptionEmail = entity.getSubscriptionEmail();
        this.enabled = entity.getEnabled();
        this.status = entity.getStatus();
        this.is_deleted = entity.getIs_deleted();
    }
    public AdminEmailTemplateResponseDTO(Integer id,
                                 LocalDateTime createdOn, LocalDateTime modifiedOn,
                                 String name, String content, Boolean subscriptionEmail, Boolean enabled, Boolean status, Boolean is_deleted, String createdByUserName, String modifiedByUserName) {
        this.id = id;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.name = name;
        this.content = content;
        this.subscriptionEmail = subscriptionEmail;
        this.enabled = enabled;
        this.status = status;
        this.is_deleted = is_deleted;
        this.createdByUserName = createdByUserName;
        this.modifiedByUserName = modifiedByUserName;
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

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
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
