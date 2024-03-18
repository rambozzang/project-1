package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.KakaoUserVo;
import com.tigerbk.project1.biz.auth.sns.vo.SignInResCvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenReqSvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenResSvo;
import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.entity.TbCustMaster;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.BadRequestException;
import com.tigerbk.project1.exception.DefaultException;
import com.tigerbk.project1.mapper.TbCustMasterMapper;
import com.tigerbk.project1.repo.FireBaseAuthRepo;
import com.tigerbk.project1.repo.TbCustMasterRepository;
import com.tigerbk.project1.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;


// https://developers.kakao.com/console/app/856419/product/login/advanced
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSvc implements RequestSvc<KakaoUserVo> {

    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient;
    private final FireBaseAuthRepo fireBaseAuthRepo;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;
    // private String REDIRECT_URI = "http://10.0.2.2:8080/login/oauth2/mobile/kakao";

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String USER_INFO_URI;


    @Override
    public SignInResCvo redirectByToken(TokenReqSvo tokenRequest) {
        if (!StringUtils.hasText(tokenRequest.getAccessToken())) {
            throw new BadRequestException("CANNOT_FOUND_AccessToken");
        }
        if (!StringUtils.hasText(tokenRequest.getRefreshToken())) {
            throw new BadRequestException("CANNOT_FOUND_RefreshToken");
        }
        try {

            KakaoUserVo kakaoUserInfo = getUserInfo(tokenRequest.getAccessToken());
            log.debug("kakaoUserInfo : {}", kakaoUserInfo.toString());

            String accessToken = jwtUtil.getAccessToken(String.valueOf(kakaoUserInfo.getId()));

            String refreshToken = jwtUtil.getRefreshToken(String.valueOf(kakaoUserInfo.getId()));

            if (!userRepository.existsByCustId(String.valueOf(kakaoUserInfo.getId()))) {

                var dto = TbCustMasterDto.builder()
                        .custId(Long.toString(kakaoUserInfo.getId()))
                        .provider(AuthProvider.KAKAO.name())
                        .birthday(kakaoUserInfo.getKakaoAccount().getBirthday())
                        .email(kakaoUserInfo.getKakaoAccount().getEmail())
                        .custNm(StringUtils.hasText(kakaoUserInfo.getKakaoAccount().getName()) ? kakaoUserInfo.getKakaoAccount().getName() : kakaoUserInfo.getKakaoAccount().getProfile().getNickname())
                        .nickNm(kakaoUserInfo.getKakaoAccount().getProfile().getNickname())
                        .hpNo(kakaoUserInfo.getKakaoAccount().getPhoneNumber())
                        .profilePath(kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                        .role(Role.USER.toString())
                        .build();

                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));

            }
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", kakaoUserInfo.getId());
            uinfo.put("email", kakaoUserInfo.getKakaoAccount().getEmail());

            uinfo.put("nickname", kakaoUserInfo.getKakaoAccount().getProfile().getNickname());
            uinfo.put("picture", kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl());

            // firebase 로그인 처리
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);

            // 로그인 시
            return SignInResCvo.builder().authProvider(AuthProvider.KAKAO).kakaoUserInfo(kakaoUserInfo).accessToken(accessToken).firebaseToken(customToken).refreshToken(refreshToken).build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException("서비스 처리중오류발생 : " + e.getMessage());
        }
    }

    @Override
    public SignInResCvo redirectByCode(TokenReqSvo tokenRequest) {

        TokenResSvo tokenResponse = getAccessToken(tokenRequest);
        log.debug("tokenResponse : {}", tokenResponse.toString());
        KakaoUserVo kakaoUserInfo = getUserInfo(tokenResponse.getAccessToken());
        log.debug("kakaoUserInfo : {}", kakaoUserInfo.toString());

        try {
            String accessToken = jwtUtil.getAccessToken(String.valueOf(kakaoUserInfo.getId()));

            String refreshToken = jwtUtil.getRefreshToken(String.valueOf(kakaoUserInfo.getId()));

            if (!userRepository.existsByCustId(String.valueOf(kakaoUserInfo.getId()))) {
                // 회원가입 유도 해야 함. 서비스를 호출 하던지 프로트 화면에서 아래 정보로 구현하든지
                var dto = TbCustMasterDto.builder()
                        .custId(Long.toString(kakaoUserInfo.getId()))
                        .provider(AuthProvider.KAKAO.name())
                        .birthday(kakaoUserInfo.getKakaoAccount().getBirthday())
                        .email(kakaoUserInfo.getKakaoAccount().getEmail())
                        .custNm(StringUtils.hasText(kakaoUserInfo.getKakaoAccount().getName()) ? kakaoUserInfo.getKakaoAccount().getName() : kakaoUserInfo.getKakaoAccount().getProfile().getNickname())
                        .nickNm(kakaoUserInfo.getKakaoAccount().getProfile().getNickname())
                        .hpNo(kakaoUserInfo.getKakaoAccount().getPhoneNumber())
                        .profilePath(kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                        .role(Role.USER.toString())
                        .build();

                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));
            }
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", kakaoUserInfo.getId());
            uinfo.put("email", kakaoUserInfo.getKakaoAccount().getEmail());
            uinfo.put("nickname", kakaoUserInfo.getKakaoAccount().getProfile().getNickname());
            uinfo.put("picture", kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);
            log.debug("------------------------------");
            log.debug("customToken : " + customToken);
            log.debug("------------------------------");
            return SignInResCvo.builder().authProvider(AuthProvider.KAKAO).kakaoUserInfo(kakaoUserInfo)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .firebaseToken(customToken)
                    .userid(kakaoUserInfo.getId().toString()).build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("redirectByCode 서버 자체토큰 생성시 오류가 발생했습니다. ");
        }
    }

    @Override
    public TokenResSvo getAccessToken(TokenReqSvo tokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", GRANT_TYPE);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("code", tokenRequest.getCode());

        log.debug("formData : {}", formData);

        log.debug("TOKEN_URI : {}", TOKEN_URI);
        // application/x-www-form-urlencoded;charset=utf-8`,
        return webClient.mutate().baseUrl(TOKEN_URI).build().post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(BodyInserters.fromFormData(formData)).retrieve()
                //.onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException()))
                .bodyToMono(TokenResSvo.class)
                //   .log()
                .block();
    }

    @Override
    public KakaoUserVo getUserInfo(String accessToken) {
        log.debug("kakao 사용자 정보 가져오기 호출!!!  {}", accessToken);
        return webClient.mutate().baseUrl("https://kapi.kakao.com").build().get().uri("/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserVo.class)
                //  .log()
                .block();
    }

    @Override
    public TokenResSvo getRefreshToken(String provider, String refreshToken) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("refresh_token", refreshToken);

        return webClient.mutate().baseUrl("https://kauth.kakao.com").build().post().uri("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                // .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new BadRequestException()))
                .bodyToMono(TokenResSvo.class).log().block();
    }
}
