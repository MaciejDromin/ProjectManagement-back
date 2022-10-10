package pl.mlisowski.projectmanagement.group.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShareGroupDto {

    @Builder
    public ShareGroupDto(Long groupId, Long userSharing, String userSharedToEmail) {
        this.groupId = groupId;
        this.userSharing = userSharing;
        this.userSharedToEmail = userSharedToEmail;
    }

    Long groupId;
    Long userSharing;
    String userSharedToEmail;

}
