package com.tigerbk.project1.biz.auth.sns;

import com.google.firebase.auth.FirebaseAuthException;

import com.tigerbk.project1.biz.auth.sns.vo.SignInResCvo;
import com.tigerbk.project1.biz.auth.sns.vo.TokenReqSvo;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "00.OAuth2 인증", description = "Token 인증 서비스.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthCntr {

    private final AuthSvc authService;

    /*
     * 각 제공사 인증화면에서 인증 완료후 호출되는 페이지
     * 호출시 code 값으로 다시 제공사쪽으로 accesstoken을 요청한다.
     */
//    @Operation(summary = "demo 조회", description = "demo 조회 메서드입니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = SignInResCvo.class))),
//    })
//    @GetMapping("/oauth2/web/{registrationId}")
//    public ResponseEntity<?> redirectWEB(
//
//            @PathVariable("registrationId") String registrationId,
//            @RequestParam("code") String code,
//            @RequestParam("state") String state) {
//
//        return ResData.SUCCESS(authService.redirect(TokenReqSvo.builder()
//                .registrationId(registrationId)
//                .code(code)
//                .state(state).build()));
//    }


    /*
     * 각 제공사 인증화면에서 인증 완료후 호출되는 페이지
     * 호출시 code 값으로 다시 제공사쪽으로 accesstoken을 요청한다.
     */
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
//    @Operation(summary = "App 에서 인증후 제공업체로 부터 Redirect URL", description = "Oaut2를 통해 로그인 후 호출되는  서비스")
//    @GetMapping("/oauth2/mobile/{registrationId}")
//    public ResponseEntity<Object> redirectMobliie2(
//
//            @PathVariable("registrationId") String registrationId,
//            @RequestParam("code") String code,
//            @RequestParam("state") String state) {
//
//        try {
//            String location = "auth-callback://success?";
//
//            SignInResCvo vo = authService.redirect(TokenReqSvo.builder()
//                    .registrationId(registrationId)
//                    .code(code)
//                    .state(state).build());
//            String param = "accessToken=".concat(vo.getAccessToken());
//            param = param.concat("&refreshToken=").concat(vo.getRefreshToken());
//            param = param.concat("&firebaseToken=").concat(vo.getFirebaseToken());
//            param = param.concat("&userid=").concat(vo.getUserid());
//
//            HttpHeaders heads = new HttpHeaders();
//            heads.add("Location", location.concat(param));
//            return new ResponseEntity<>(null, heads, HttpStatus.TEMPORARY_REDIRECT);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BadRequestException("oauth2 인증후 서버 자체토큰 생성시 오류가 발생했습니다. ");
//        }
//    }

    /*
     * Client App 에서 이미 이증후 accessToken 까지 받은 상태로
     * Token 를 받아 회원가입,파이어베이스가입 처리후 리
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
    @Operation(summary = "App에서 인증완료시 Token으로 로그인 처리 서비스", description = "Oaut2를 통해 Token 으로 로그인 처리")
    @PostMapping("/auth/joinByToken")
    public ResponseEntity<?> joinByToken(@Valid @RequestBody TokenReqSvo tokenInSVO) {

        return ResData.SUCCESS(authService.redirectByToken(tokenInSVO));
    }

    // 만료된 리플레쉬토큰 재생성
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK !!")})
    @Operation(summary = "만료된 토큰 refreshToken으로 재생성 처리 서비스", description = "refreshToken을 입력받아 accessToken, refreshToken 를 반환")
    @PostMapping("/auth/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid  @RequestBody TokenReqSvo tokenRequest) {
        return ResData.SUCCESS(authService.refreshToken(tokenRequest));
    }

    // firebase clound 저장된 uid 로 customtoken 생성하여 리턴
    @GetMapping("/auth/getFirebaseCustomToken")
    public ResponseEntity<?> getFirebaseCutomTokenByuid(
            @RequestParam("uid") String uid) throws FirebaseAuthException {
        return ResData.SUCCESS(authService.getFirebaseCutomTokenByuid(uid));
    }



}