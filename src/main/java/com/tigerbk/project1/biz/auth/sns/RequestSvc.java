package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.SignInResCvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenReqSvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenResSvo;

public interface RequestSvc<T> {
    // 각 oauth 제공사에서 code를 받아 회원가입 처리시.
    SignInResCvo redirectByCode(TokenReqSvo tokenRequest);

    // 앱에서 토큰까지 받은상태로 서버로 전달하여 회원가입 처리시.
    SignInResCvo redirectByToken(TokenReqSvo tokenRequest);

    // 토큰 가져오기.
    TokenResSvo getAccessToken(TokenReqSvo tokenRequest);

    // 사용자정보 가져오기.
    T getUserInfo(String accessToken);

    // 리플레쉬토큰 가져오기.
    TokenResSvo getRefreshToken(String provider, String refreshToken);
}
