package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.KakaoUserVo;
import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.mapper.TbCustMasterMapper;
import com.tigerbk.project1.repo.FireBaseAuthRepo;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;


// https://developers.kakao.com/console/app/856419/product/login/advanced
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSvc {

    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient;
    private final FireBaseAuthRepo fireBaseAuthRepo;

    @Transactional
    public String SignInProc(KakaoUserVo kakaoUserVo) {

        try {
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", kakaoUserVo.getId());
            uinfo.put("email", kakaoUserVo.getKakaoAccount().getEmail());
            uinfo.put("nickname", kakaoUserVo.getKakaoAccount().getProfile().getNickname());
            uinfo.put("picture", kakaoUserVo.getKakaoAccount().getProfile().getProfileImageUrl());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);
            log.debug("customToken : " + customToken);
            log.debug("customToken len : " + customToken.length());

            if (!userRepository.existsByCustId(String.valueOf(kakaoUserVo.getId()))) {
                var dto = TbCustMasterDto.builder()
                        .custId(Long.toString(kakaoUserVo.getId()))
                        .provider(AuthProvider.KAKAO.name())
                        .birthday(kakaoUserVo.getKakaoAccount().getBirthday())
                        .email(kakaoUserVo.getKakaoAccount().getEmail())
                        .custNm(StringUtils.hasText(kakaoUserVo.getKakaoAccount().getName()) ? kakaoUserVo.getKakaoAccount().getName() : kakaoUserVo.getKakaoAccount().getProfile().getNickname())
                        .nickNm(kakaoUserVo.getKakaoAccount().getProfile().getNickname())
                 //      .fcmId(customToken)
                        .hpNo(kakaoUserVo.getKakaoAccount().getPhoneNumber())
                        .profilePath(kakaoUserVo.getKakaoAccount().getProfile().getProfileImageUrl())
                        .role(Role.USER.toString())
                        .build();
                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));
            }
            String accessToken = jwtUtil.getAccessToken(String.valueOf(kakaoUserVo.getId()));
            String refreshToken = jwtUtil.getRefreshToken(String.valueOf(kakaoUserVo.getId()));
            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException("SignInProc 처리중오류발생 : " + e.getMessage());
        }
    }

}
