package com.tigerbk.project1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.tigerbk.project1.entity.TbCustMaster}
 */
@Value
@Builder
public class TbCustMasterDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 100)
    String membId;
    @Size(max = 100)
    String nickNm;
    @Size(max = 100)
    String membNm;
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
    @NotNull
    Instant regDate;
    @NotNull
    Instant modDate;
}