package com.tigerbk.project1.biz.auth.sns.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenResSvo {
    private String tokenType;
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private Integer refreshTokenExpiresIn;
    private String error;
    private String errorDescription;
}
