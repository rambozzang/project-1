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

import java.util.HashMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthSvc {

    private final TbCustMasterRepository userRepository;
    private final FireBaseAuthRepo fireBaseAuthRepo;
    private final JwtUtil jwtUtil;
    public String getFirebaseCutomTokenByuid(String uid) throws FirebaseAuthException {
        return fireBaseAuthRepo.getFirebaseCustomToken(uid);
    }
    public SignInResCvo refreshToken(TokenReqSvo tokenRequest) {
        if (jwtUtil.isExpiration(tokenRequest.getRefreshToken())) {
            throw new UnAuthException("EXPIRED_ACCESS_TOKEN");
        }
        String userId = (String) jwtUtil.get(tokenRequest.getRefreshToken()).get("userId");
        String provider = (String) jwtUtil.get(tokenRequest.getRefreshToken()).get("provider");
        if (!userRepository.existsByCustId(userId)) {
            throw new BadRequestException("사용자 정보를 찾을 수 없습니다!");
        }
        String accessToken = jwtUtil.getAccessToken(userId);
        String refreshToken = jwtUtil.getRefreshToken(userId);
        return SignInResCvo.builder()
                .authProvider(AuthProvider.findByCode(provider.toUpperCase()))
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
