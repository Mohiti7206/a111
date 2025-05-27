package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

//public class HourlyTaskDataRequestDto {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class HourlyTaskDataRequestDto {

    private String hourlyTask; // URI to the hourly task resource
    private String details;
    private String time;
    private String hours;
    private String minutes;

    private LocalDateTime dateReceived;
    private LocalDateTime taskCompleted;

    private Boolean enabled;
    private Boolean deleted;

    private String userTimeZone;

    private AdminUserMvc createdBy; // URI to the createdBy user
    private AdminUserMvc modifiedBy; // URI to the modifiedBy user (can be empty)
}
