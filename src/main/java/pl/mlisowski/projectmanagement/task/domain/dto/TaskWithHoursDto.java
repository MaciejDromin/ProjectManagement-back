package pl.mlisowski.projectmanagement.task.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class TaskWithHoursDto {

    @Builder
    public TaskWithHoursDto(TaskDto taskDto, int realHours, int estimatedHours) {
        this.taskDto = taskDto;
        this.realHours = realHours;
        this.estimatedHours = estimatedHours;
    }

    @JsonProperty("task")
    TaskDto taskDto;

    int realHours;
    int estimatedHours;

}
