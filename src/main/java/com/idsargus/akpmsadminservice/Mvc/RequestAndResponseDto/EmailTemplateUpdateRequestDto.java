package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

import java.util.List;
import java.util.Set;

public class EmailTemplateUpdateRequestDto {

    Set<Integer> emailTemplateIds;

    public Set<Integer> getEmailTemplateIds() {
        return emailTemplateIds;
    }

    public void setEmailTemplateIds(Set<Integer> emailTemplateIds) {
        this.emailTemplateIds = emailTemplateIds;
    }
}
