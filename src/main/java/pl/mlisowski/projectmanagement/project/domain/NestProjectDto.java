package pl.mlisowski.projectmanagement.project.domain;

import lombok.*;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NestProjectDto {

    private ProjectDto nestTo;
    private ProjectDto nested;

}
