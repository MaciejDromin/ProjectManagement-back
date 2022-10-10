package pl.mlisowski.projectmanagement.group.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@SuperBuilder
public class ProjectGroupDto {

    private Long id;
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();
    private String name;
    @Builder.Default
    private Set<ProjectDto> projects = new HashSet<>();
    @Builder.Default
    private Set<PredefinedGroupStateDto> predefinedGroupStates = new HashSet<>();

}
