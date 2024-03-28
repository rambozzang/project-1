package com.tigerbk.project1.dto;

import com.tigerbk.project1.entity.TbBoardSingo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link TbBoardSingo}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardSingoDto implements Serializable {
    Long id;
    @NotNull
    Long boardId;
    @NotNull
    @Size(max = 100)
    String custId;
    String singoReason;
    @NotNull
    LocalDateTime crtDtm;
    LocalDateTime chgCtm;
    @Size(max = 100)
    String chgCustId;
}