package com.idsargus.akpmsadminservice.ExcelDownloadDto;

//import com.idsargus.akpmsadminservice.entity.AdminHourlyTaskEntity;
//import com.idsargus.akpmsarservice.model.domain.ArHourlyTaskEntity;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminHourlyTaskEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HourlyTasksArDto {


    private Integer id;
    private LocalDateTime created_on;
    private LocalDateTime modified_on;
    private  Integer created_by;
    private  String    created_by_firstname;
    private  String    created_by_lastname;
    private  Integer modified_by;
    private  String    modified_by_firstname;
    private  String    modified_by_lastname;
    private   String    detail;
    private   String    time;
    private   String    hours;
    private   String    minutes;
    private   Integer    task_name;
    private LocalDateTime date_received;
    private   String    hourly_task_name;
    private LocalDateTime date_task_completed;

    public static HourlyTasksArDto fromEntity(AdminHourlyTaskEntity entity) {
        if (entity == null) {
            return null;
        }

        HourlyTasksArDto dto = new HourlyTasksArDto();

        dto.setId(entity.getId());
        dto.setCreated_by(entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null);
        dto.setCreated_on(entity.getCreatedOn() != null ? entity.getCreatedOn() : null);
        dto.setModified_on(entity.getModifiedOn() != null ? entity.getModifiedOn() : null);
        dto.setCreated_by_firstname(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() : null);
        dto.setCreated_by_lastname(entity.getCreatedBy() != null ? entity.getCreatedBy().getLastName() : null);
        dto.setModified_by(entity.getModifiedBy() != null ? entity.getModifiedBy().getId() : null);
        dto.setModified_by_firstname(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() : null);
        dto.setModified_by_lastname(entity.getModifiedBy() != null ? entity.getModifiedBy().getLastName() : null);
        dto.setDetail(entity.getDetails() != null ? entity.getDetails() : "");
        dto.setTime(entity.getTime() != null ? entity.getTime() : null);
        dto.setHours(entity.getHours() != null ? entity.getHours() : null);
        dto.setMinutes(entity.getMinutes() != null ? entity.getMinutes() : null);
        dto.setTask_name(entity.getHourlyTask() != null ? entity.getHourlyTask().getId() : null);
        dto.setDate_received(entity.getDateReceived() != null ? entity.getDateReceived() : null);
        dto.setHourly_task_name(entity.getHourlyTask() != null ? entity.getHourlyTask().getName() : "");
        dto.setDate_task_completed(entity.getTaskCompleted() != null ? entity.getTaskCompleted() : null);



//                "id": 10977,
//                "created_on": "2024-09-10 00:00:00",
//                "modified_on": null,
//                "created_by": 890,
//                "created_by_firstname": "Sushil ",
//                "created_by_lastname": "Thakur",
//                "modified_by": null,
//                "modified_by_firstname": null,
//                "modified_by_lastname": null,
//                "detail": "Setareh-Shenas, Saman (PH) 2\n",
//                "time": 0.17,
//                "hours": 0,
//                "minutes": 10,
//                "task_name": 45,
//                "date_received": "2024-09-10 00:00:00",
//                "hourly_task_name": "Noida_Rejection Log Processing_ Charge Entry",
//                "date_task_completed": "2024-09-10 00:00:00"

        return  dto;
    }

}
