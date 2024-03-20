package com.tigerbk.project1.biz.auth.sns;

import com.google.firebase.auth.FirebaseAuthException;

import com.tigerbk.project1.biz.auth.sns.vo.*;
import com.tigerbk.project1.common.vo.ResData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "00.oauth 인증", description = "SNS 회원가입 서비스.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthCntr {

    private final AuthSvc authService;
    private final KakaoSvc kakaoSvc;
    private final  NaverSvc naverSvc;
    private final GoogleSvc googleSvc;
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
    @Operation(summary = "KAKAO App에서 얻은 정보로 로그인 처리 서비스", description = "KAKAO User 정보로 회원가입한다.")
    @PostMapping("/auth/kakaojoin")
    public ResponseEntity<?> joinBykakao(@Valid @RequestBody KakaoUserVo kakaoUserVo) {
        return ResData.SUCCESS(kakaoSvc.SignInProc(kakaoUserVo));
    }
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
    @Operation(summary = "Naver App에서 얻은 정보로 로그인 처리 서비스", description = "Naver User 정보로 회원가입한다.")
    @PostMapping("/auth/naverjoin")
    public ResponseEntity<?> joinBynaver(@Valid @RequestBody NaverUserVo naverUserVo) {
        return ResData.SUCCESS(naverSvc.SignInProc(naverUserVo));
    }
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
    @Operation(summary = "Google App에서 얻은 정보로 로그인 처리 서비스", description = "Google User 정보로 회원가입한다.")
    @PostMapping("/auth/googlejoin")
    public ResponseEntity<?> joinBygoogle(@Valid @RequestBody GoogleUserVo googleUserVo) {
        return ResData.SUCCESS(googleSvc.SignInProc(googleUserVo));
    }


    // 만료된 리플레쉬토큰 재생성
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK!!")})
    @Operation(summary = "만료된 토큰 refreshToken으로 재생성 처리 서비스", description = "refreshToken을 입력받아 accessToken, refreshToken 를 반환")
    @PostMapping("/auth/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenReqSvo tokenRequest) {
        return ResData.SUCCESS(authService.refreshToken(tokenRequest));
    }
    // firebase clound 저장된 uid 로 customtoken 생성하여 리턴
    @GetMapping("/auth/getfirebasecustomtoken")
    public ResponseEntity<?> getFirebaseCutomTokenByuid(
            @RequestParam("uid") String uid) throws FirebaseAuthException {
        return ResData.SUCCESS(authService.getFirebaseCutomTokenByuid(uid));
    }
    // firebase clound 저장된 uid 로 customtoken 생성하여 리턴
    @GetMapping("/error")
    public ResponseEntity<?> error() throws FirebaseAuthException {
        return ResData.SUCCESS("Error Page !!");
    }


}