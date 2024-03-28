package com.tigerbk.project1.dto;

import com.tigerbk.project1.entity.TbBoardFollowId;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardFollow}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardFollowDto implements Serializable {

    TbBoardFollowIdDto id;
    @NotNull
    LocalDateTime crtDtm;
}