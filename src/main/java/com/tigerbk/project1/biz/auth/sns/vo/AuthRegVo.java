package com.tigerbk.project1.biz.auth.sns.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthRegVo {
    private String authProvider;
    private String code;
    private String state;
    private String accessToken;
    private String refreshToken;
}
