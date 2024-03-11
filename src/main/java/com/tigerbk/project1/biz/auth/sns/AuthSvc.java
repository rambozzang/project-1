package com.tigerbk.project1.biz.auth.sns;

import com.google.firebase.auth.FirebaseAuthException;

import com.tigerbk.project1.biz.auth.sns.vo.SignInResCvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenReqSvo;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.exception.BadRequestException;
import com.tigerbk.project1.exception.UnAuthException;
import com.tigerbk.project1.repo.FireBaseAuthRepo;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthSvc {

    private final KakaoSvc kakaoRequestService;
    private final NaverSvc naverRequestService;

    private final GoogleSvc googleRequestService;

    private final TbCustMasterRepository userRepository;
    private final FireBaseAuthRepo fireBaseAuthRepo;
    private final JwtUtil jwtUtil;


    // 제공사에서 인증완료 후 리다렉트해서 온 프로세스
    public SignInResCvo redirect(TokenReqSvo tokenRequest) {

        // 인증업체명 가져오기
        String provider = tokenRequest.getRegistrationId();
        if (provider.isEmpty()) {
            throw new BadRequestException(provider + "는 지원 하지 않는 인증업체입니다.");
        }

        if (AuthProvider.KAKAO.getAuthProvider().equals(provider.toUpperCase())) {
            return kakaoRequestService.redirectByCode(tokenRequest);
        }

        if (AuthProvider.NAVER.getAuthProvider().equals(provider.toUpperCase())) {
            return naverRequestService.redirectByCode(tokenRequest);
        }

        if (AuthProvider.GOOGLE.getAuthProvider().equals(provider.toUpperCase())) {
            return googleRequestService.redirectByCode(tokenRequest);
        }

        throw new BadRequestException("지원 하지 않는 인증업체인빈다." + provider.toUpperCase());
    }

    public SignInResCvo redirectByToken(TokenReqSvo tokenRequest) {
        String provider = tokenRequest.getRegistrationId();

        if ((provider.isEmpty())) {
            throw new BadRequestException("지원 하지 않는 인증업체인빈다. =>" + provider);
        }

        if (AuthProvider.KAKAO.getAuthProvider().equals(provider.toUpperCase())) {
            return kakaoRequestService.redirectByToken(tokenRequest);
        }

        if (AuthProvider.NAVER.getAuthProvider().equals(provider.toUpperCase())) {
            return naverRequestService.redirectByToken(tokenRequest);
        }

        if (AuthProvider.GOOGLE.getAuthProvider().equals(provider.toUpperCase())) {
            return googleRequestService.redirectByToken(tokenRequest);
        }

        throw new BadRequestException("지원 하지 않는 인증업체인빈다. =>" + provider.toUpperCase());
    }

    public String getFirebaseCutomTokenByuid(String uid) throws FirebaseAuthException {
        return fireBaseAuthRepo.getFirebaseCustomToken(uid);
    }

    public SignInResCvo refreshToken(TokenReqSvo tokenRequest) {
        // refreshToken 으로 accessToken 재발급
        // 1. refreshToken 정상여부 체크
        if (jwtUtil.isExpiration(tokenRequest.getRefreshToken())) {
            throw new UnAuthException("EXPIRED_ACCESS_TOKEN");
        }
        // 2. 정상이면 accessToken, refreshToken 동시 발급한다.
        // 3. refreshToken 조차 만료인 경우 다시 로그인 처리 유도 하기 위해 에러 발급하지 않는다.
        String userId = (String) jwtUtil.get(tokenRequest.getRefreshToken()).get("userId");
        String provider = (String) jwtUtil.get(tokenRequest.getRefreshToken()).get("provider");
        if (!userRepository.existsByMembId(userId)) {
            throw new BadRequestException("사용자 정보를 찾을 수 없습니다!");
        }

        // 굳이 oauth2 제공사 토큰을 앱이 제공해줄 이유는 없음.
        // TokenOutSVO tokenResponse = null;
        // if (AuthProvider.KAKAO.getAuthProvider().equals(provider.toUpperCase())) {
        // tokenResponse = kakaoRequestService.getRefreshToken(provider,
        // oldRefreshToken);
        // } else if
        // (AuthProvider.NAVER.getAuthProvider().equals(provider.toUpperCase())) {
        // tokenResponse = naverRequestService.getRefreshToken(provider,
        // oldRefreshToken);
        // } else if
        // (AuthProvider.GOOGLE.getAuthProvider().equals(provider.toUpperCase())) {
        // tokenResponse = googleRequestService.getRefreshToken(provider,
        // oldRefreshToken);
        // } else {
        // throw new BadRequestException("토큰 정보가 없습니다!");
        // }


        String accessToken = jwtUtil.getAccessToken(userId);
        String refreshToken = jwtUtil.getRefreshToken(userId);

        return SignInResCvo.builder()
                .authProvider(AuthProvider.findByCode(provider.toUpperCase()))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
