package pl.mlisowski.projectmanagement.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NestProject {

    private Project nestTo;
    private Project nested;

}
