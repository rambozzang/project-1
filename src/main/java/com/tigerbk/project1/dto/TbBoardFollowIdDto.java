package com.tigerbk.project1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbBoardFollowId}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TbBoardFollowIdDto implements Serializable {
    @NotNull
    @Size(max = 100)
    String custId;
    @NotNull
    @Size(max = 100)
    String followCustId;
}