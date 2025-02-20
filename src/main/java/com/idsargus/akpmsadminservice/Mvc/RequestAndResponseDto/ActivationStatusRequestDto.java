package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;


public class ActivationStatusRequestDto {

    private boolean enabled;

    // Default constructor (needed for deserialization)
    public ActivationStatusRequestDto() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
