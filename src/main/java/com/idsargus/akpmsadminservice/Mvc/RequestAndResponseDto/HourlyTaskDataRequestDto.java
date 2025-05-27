package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

//public class HourlyTaskDataRequestDto {
//}

import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskName;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class HourlyTaskDataRequestDto {

    private AdminHourlyTaskName hourlyTask; // URI to the hourly task resource
    private String details;
    private String time;
    private String hours;
    private String minutes;

    private LocalDateTime dateReceived;
    private LocalDateTime taskCompleted;

    private Boolean enabled;
    private Boolean deleted;

    private String userTimeZone;

    @NotNull(message = "CreatedBy is mandatory", groups = ValidationGroups.Create.class)
    private AdminUserMvc createdBy;

    @NotNull(message = "ModifiedBy is mandatory", groups = ValidationGroups.Update.class)
    private AdminUserMvc modifiedBy;

    public AdminHourlyTaskName getHourlyTask() {
        return hourlyTask;
    }

    public void setHourlyTask(AdminHourlyTaskName hourlyTask) {
        this.hourlyTask = hourlyTask;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public LocalDateTime getTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(LocalDateTime taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public AdminUserMvc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AdminUserMvc createdBy) {
        this.createdBy = createdBy;
    }

    public AdminUserMvc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(AdminUserMvc modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
