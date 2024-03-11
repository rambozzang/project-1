package com.tigerbk.project1.biz.auth.sns;


import com.tigerbk.project1.biz.auth.sns.vo.GoogleUserVo;
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
public class GoogleSvc implements RequestSvc<GoogleUserVo> {

    private final TbCustMasterRepository userRepository;
    private final JwtUtil jwtUtil;
    private final WebClient webClient;
    private final FireBaseAuthRepo fireBaseAuthRepo;

    @Value("${spring.security.oauth2.client.registration.google.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String USER_INFO_URI;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String REDIRECT_URI;

    @Override
    public SignInResCvo redirectByToken(TokenReqSvo tokenRequest) {

        TokenResSvo tokenResponse = getAccessToken(tokenRequest);
        GoogleUserVo googleUserInfo = getUserInfo(tokenResponse.getAccessToken());
        String accessToken = jwtUtil.getAccessToken(googleUserInfo.getId());
        String refreshToken = jwtUtil.getRefreshToken(googleUserInfo.getId());

        // 회원가입이 된 경우
        if (!userRepository.existsByMembId(googleUserInfo.getId())) {
            return SignInResCvo.builder()
                    .authProvider(AuthProvider.GOOGLE)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        // 회원가입 유도 해야 함. 서비스를 호출 하던지 프로트 화면에서 아래 정보로 구현하든지
        var dto = TbCustMasterDto.builder()
                .membId(googleUserInfo.getId())
                .provider(AuthProvider.GOOGLE.name())
                .birthday(null)
                .email(googleUserInfo.getEmail())
                .membNm(googleUserInfo.getName())
                .nickNm(googleUserInfo.getName())
                .profilePath(googleUserInfo.getPicture())
                .role(Role.USER.toString())
                .build();

        userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));

        return SignInResCvo.builder()
                .authProvider(AuthProvider.GOOGLE)
                .googleUserInfo(googleUserInfo)
                .build();

    }

    @Override
    public SignInResCvo redirectByCode(TokenReqSvo tokenRequest) {
        TokenResSvo tokenResponse = getAccessToken(tokenRequest);
        GoogleUserVo googleUserInfo = getUserInfo(tokenResponse.getAccessToken());

        try {
            String accessToken = jwtUtil.getAccessToken(googleUserInfo.getId());
            String refreshToken = jwtUtil.getRefreshToken(googleUserInfo.getId());

            // 회원가입이 된 경우
            if (!userRepository.existsByMembId(String.valueOf(googleUserInfo.getId()))) {
                // 회원가입 유도 해야 함. 서비스를 호출 하던지 프로트 화면에서 아래 정보로 구현하든지
//				userRepository.save(TbCustMasterDto.builder()
//					.membId(googleUserInfo.getId())
//					.provider(AuthProvider.GOOGLE.name())
//					.birthday(null)
//					.email(googleUserInfo.getEmail())
//					.membNm(googleUserInfo.getName())
//					.nickNm(googleUserInfo.getName())
//					.profilePath(googleUserInfo.getPicture())
//					.role(Role.USER.name())
//					.build());

                var dto = TbCustMasterDto.builder()
                        .membId(googleUserInfo.getId())
                        .provider(AuthProvider.GOOGLE.name())
                        .birthday(null)
                        .email(googleUserInfo.getEmail())
                        .membNm(googleUserInfo.getName())
                        .nickNm(googleUserInfo.getName())
                        .profilePath(googleUserInfo.getPicture())
                        .role(Role.USER.toString())
                        .build();

                userRepository.save(TbCustMasterMapper.INSTANCE.toEntity(dto));
            }

            // firebase 로그인 처리
            HashMap<String, Object> uinfo = new HashMap<>();
            uinfo.put("id", googleUserInfo.getId());
            uinfo.put("email", googleUserInfo.getEmail());
            uinfo.put("nickname", googleUserInfo.getName());
            uinfo.put("picture", googleUserInfo.getPicture());
            String customToken = fireBaseAuthRepo.createFirebaseCustomToken(uinfo);

            return SignInResCvo.builder()
                    .authProvider(AuthProvider.GOOGLE)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .firebaseToken(customToken)
                    .googleUserInfo(googleUserInfo)
                    .userid(googleUserInfo.getId().toString())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("redirectByCode 서버 자체토큰 생성시 오류가 발생했습니다. ");
        }

    }

    @Override
    public TokenResSvo getAccessToken(TokenReqSvo tokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", tokenRequest.getCode());
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URI);
        formData.add("grant_type", GRANT_TYPE);

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
    public GoogleUserVo getUserInfo(String accessToken) {
        return webClient.mutate()
                .baseUrl(USER_INFO_URI)
                .build()
                .get()
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(GoogleUserVo.class)
                .log()
                .block();
    }

    @Override
    public TokenResSvo getRefreshToken(String provider, String refreshToken) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
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
