package pl.mlisowski.projectmanagement.project.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProjectCreationDto {

    String name;
    String description;
    Long groupId;

}
