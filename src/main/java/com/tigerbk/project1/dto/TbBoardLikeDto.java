package com.tigerbk.project1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardLike}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardLikeDto implements Serializable {
    TbBoardLikeIdDto id;
    @NotNull
    LocalDateTime crtDtm;
}