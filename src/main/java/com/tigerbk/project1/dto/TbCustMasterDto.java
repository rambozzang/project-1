package com.tigerbk.project1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbCustMaster}
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TbCustMasterDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 100)
    String custId;
    @Size(max = 100)
    String nickNm;
    @Size(max = 100)
    String custNm;
    @Size(max = 100)
    String email;
    @Size(max = 100)
    String hpNo;
    @Size(max = 6)
    String birthday;
    @Size(max = 200)
    String fcmId;
    @Size(max = 100)
    String provider;
    @Size(max = 100)
    String profilePath;
    @Size(max = 100)
    String role;
    @Size(max = 20)
    String pattenPasswd;
    @Size(max = 6)
    String pinPasswd;

    LocalDateTime regDate;

    LocalDateTime modDate;
}