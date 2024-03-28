package com.tigerbk.project1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardLikeId}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardLikeIdDto implements Serializable {
    @NotNull
    Long boardId;
    @NotNull
    String custId;
}