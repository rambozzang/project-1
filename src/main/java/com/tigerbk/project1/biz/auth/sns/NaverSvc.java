package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.NaverUserVo;
import com.tigerbk.project1.biz.auth.sns.vo.SignInResCvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenReqSvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenResSvo;
import com.tigerbk.project1.dto.TbCustMasterDto;
import com.tigerbk.project1.enums.AuthProvider;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.BadRequestException;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSvc implements RequestSvc<NaverUserVo> {

    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient;
    private final FireBaseAuthRepo fireBaseAuthRepo;

    @Value("${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String USER_INFO_URI;

    /*
     * 클라이언트에서 사용자 정보 및 토큰을 가지고 던졌을때
     */
    @Override
    public SignInResCvo redirectByToken(TokenReqSvo tokenRequest) {

        TokenResSvo tokenResponse = getAccessToken(tokenRequest);
        NaverUserVo naverUserInfo = getUserInfo(tokenResponse.getAccessToken());

        try {

            String accessToken = jwtUtil.getAccessToken(naverUserInfo.getResponse().getId());
            String refreshToken = jwtUtil.getRefreshToken(naverUserInfo.getResponse().getId());

            if (!userRepository.existsByMembId(String.valueOf(naverUserInfo.getResponse().getId()))) {
                // 회원가입 유도 해야 함. 서비스를 호출 하던지 프로트 화면에서 아래 정보로 구현하든지

                var dto = TbCustMasterDto.builder()
                        .membId(naverUserInfo.getResponse().getId())
                        .provider(AuthProvider.NAVER.name())
                        .birthday(naverUserInfo.getResponse().getBirthyear())
                        .email(naverUserInfo.getResponse().getEmail())
                        .membNm(naverUserInfo.getResponse().getName())
                        .nickNm(naverUserInfo.getResponse().getNickname() == "" ? naverUserInfo.getResponse().getName()
                                : naverUserInfo.getResponse().getNickname())
                        .hpNo(naverUserInfo.getResponse().getMobile())
                        .profilePath(naverUserInfo.getResponse().getProfileImage())
                        .role(Role.USER.toString())
                        .build();

                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));

            }
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", naverUserInfo.getResponse().getId());
            uinfo.put("email", naverUserInfo.getResponse().getEmail());
            uinfo.put("nickname", naverUserInfo.getResponse().getNickname());
            uinfo.put("picture", naverUserInfo.getResponse().getProfileImage());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);

            return SignInResCvo.builder()
                    .authProvider(AuthProvider.NAVER)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .firebaseToken(customToken)
                    .naverUserInfo(naverUserInfo)
                    .userid(naverUserInfo.getResponse().getId())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("redirectByCode 서버 자체토큰 생성시 오류가 발생했습니다. ");
        }

    }

    @Override
    public SignInResCvo redirectByCode(TokenReqSvo tokenRequest) {

        TokenResSvo tokenResponse = getAccessToken(tokenRequest);
        NaverUserVo naverUserInfo = getUserInfo(tokenResponse.getAccessToken());
        try {
            String accessToken = jwtUtil.getAccessToken(naverUserInfo.getResponse().getId());
            String refreshToken = jwtUtil.getRefreshToken(naverUserInfo.getResponse().getId());

            if (!userRepository.existsByMembId(String.valueOf(naverUserInfo.getResponse().getId()))) {
                // 회원가입 유도 해야 함. 서비스를 호출 하던지 프로트 화면에서 아래 정보로 구현하든지

                var dto = TbCustMasterDto.builder()
                        .membId(naverUserInfo.getResponse().getId())
                        .provider(AuthProvider.NAVER.name())
                        .birthday(naverUserInfo.getResponse().getBirthyear())
                        .email(naverUserInfo.getResponse().getEmail())
                        .membNm(naverUserInfo.getResponse().getName())
                        .nickNm(naverUserInfo.getResponse().getNickname() == "" ? naverUserInfo.getResponse().getName()
                                : naverUserInfo.getResponse().getNickname())
                        .hpNo(naverUserInfo.getResponse().getMobile())
                        .profilePath(naverUserInfo.getResponse().getProfileImage())
                        .role(Role.USER.toString())
                        .build();

                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));

            }
            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", naverUserInfo.getResponse().getId());
            uinfo.put("email", naverUserInfo.getResponse().getEmail());
            uinfo.put("nickname", naverUserInfo.getResponse().getNickname());
            uinfo.put("picture", naverUserInfo.getResponse().getProfileImage());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);

            return SignInResCvo.builder()
                    .authProvider(AuthProvider.NAVER)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .naverUserInfo(naverUserInfo)
                    .firebaseToken(customToken)
                    .userid(naverUserInfo.getResponse().getId())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("redirectByCode 서버 자체토큰 생성시 오류가 발생했습니다. ");
        }

    }

    @Override
    public TokenResSvo getAccessToken(TokenReqSvo tokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", GRANT_TYPE);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("code", tokenRequest.getCode());
        formData.add("state", tokenRequest.getState());

        return webClient.mutate()
                .baseUrl(TOKEN_URI)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                // .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new
                // BadRequestException()))
                .bodyToMono(TokenResSvo.class)
                .log()
                .block();
    }

    @Override
    public NaverUserVo getUserInfo(String accessToken) {
        return webClient.mutate()
                .baseUrl(USER_INFO_URI)
                .build()
                .get()
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(NaverUserVo.class)
                .log()
                .block();
    }

    @Override
    public TokenResSvo getRefreshToken(String provider, String refreshToken) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", CLIENT_ID);
        formData.add("refresh_token", refreshToken);

        return webClient.mutate()
                .baseUrl(TOKEN_URI)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                // .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new
                // BadRequestException()))
                .bodyToMono(TokenResSvo.class)
                .log()
                .block();
    }
}
