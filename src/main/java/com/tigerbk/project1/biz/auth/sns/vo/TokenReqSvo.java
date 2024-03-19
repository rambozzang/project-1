package com.tigerbk.project1.biz.auth.sns.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TokenReqSvo {
    private String registrationId;
    private String code;
    private String state;
    private String accessToken;
    private String refreshToken;
}
