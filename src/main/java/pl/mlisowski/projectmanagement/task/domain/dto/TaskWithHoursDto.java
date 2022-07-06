package pl.mlisowski.projectmanagement.task.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TaskWithHoursDto {

    @JsonProperty("task")
    TaskDto taskDto;

    int realHours;
    int estimatedHours;

}
