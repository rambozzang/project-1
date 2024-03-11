package com.tigerbk.project1.biz.auth.sns.vo;


import com.tigerbk.project1.enums.AuthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResCvo {
    private AuthProvider authProvider;
    private KakaoUserVo kakaoUserInfo;
    private NaverUserVo naverUserInfo;
    private GoogleUserVo googleUserInfo;
    private String accessToken;
    private String refreshToken;
    private String firebaseToken;
    private String userid;

    @Builder
    public SignInResCvo(
            AuthProvider authProvider
            ,KakaoUserVo kakaoUserInfo
            ,NaverUserVo naverUserInfo
            ,GoogleUserVo googleUserInfo
            ,String accessToken
            ,String refreshToken
            ,String firebaseToken
            ,String userid
    ){
        this.authProvider = authProvider;
        this.kakaoUserInfo = kakaoUserInfo;
        this.naverUserInfo = naverUserInfo;
        this.googleUserInfo = googleUserInfo;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.firebaseToken = firebaseToken;
        this.userid = userid;
    }
}
