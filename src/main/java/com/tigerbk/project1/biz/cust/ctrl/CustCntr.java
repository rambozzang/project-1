package com.tigerbk.project1.biz.cust.ctrl;


import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.biz.cust.svc.CustSvc;
import com.tigerbk.project1.common.vo.ResData;
import com.tigerbk.project1.exception.ErrorResponse;
import com.tigerbk.project1.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01.사용자정보 조회", description = "토큰에 해당하는 사용자 정보를 조회한다.")
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustCntr {


    private final UserAuthSvc userAuthSvc;

    private final CustSvc custSvc;

    private final JwtUtil jwtUtil;


    @Operation(summary = "01.토큰을 통한 사용자정보조회", description = "\n### 토큰을 통한 사용자 정보를 조회한다." +
            "\n- Input : 온니 인증 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/auth/getuserinfo")
    public ResponseEntity<?> getUserInfo() {
        return ResData.SUCCESS(userAuthSvc.getSessionUser().toString());
    }

    @Operation(
            summary = "02.로그인 by 패스워드",
            description = "\n### custId 와 패스워드를 통한 로그인 서비스 "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody String custId, String passwd) {
        return ResData.SUCCESS(userAuthSvc.getSessionUser().toString());
    }

    @Operation(
            summary = "03. 로그인 by Pin번호",
            description = "\n### custId 와 Pin번호를 통한 로그인 서비스 "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/auth/loginbyPin")
    public ResponseEntity<?> loginbyPin(@Valid @RequestParam("custId") String custId, @RequestParam("pinNo") String pinNo) throws Exception {
        return ResData.SUCCESS(custSvc.LoginByPinPasswd(custId, pinNo));
    }

    @Operation(
            summary = "04. 로그인 by 패턴번호",
            description = "\n### custId 와 패턴번호를 통한 로그인 서비스 "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/auth/loginbyPattern")
    public ResponseEntity<?> loginbyPattern(@Valid @RequestParam("custId") String custId, @RequestParam("pinNo") String pinNo) throws Exception {
        return ResData.SUCCESS(custSvc.LoginByPattern(custId, pinNo));
    }

    @Operation(
            summary = "05. 로그인 by 생체인증",
            description = "\n### custId 와 생체인증을 통한 로그인 서비스 "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/auth/loginbyBio")
    public ResponseEntity<?> loginbyBio(@Valid @RequestBody String custId, String bioInfo) throws Exception {
        return ResData.SUCCESS(custSvc.LoginByBio(custId));
    }

    @Operation(summary = "06. Token 발급(Test AccessToken 발급)", description = " Token 발급 서비스")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token 발급 성공", content = @Content(schema = @Schema(implementation = String.class))),
    })
    @GetMapping("/auth/gettoken/{custId}")
    public ResponseEntity<?> getToken(@PathVariable(name = "custId") String custId) {
        String token = jwtUtil.getAccessToken(custId);
        if(token != null && !"".equals(token)) {
            return ResData.SUCCESS(token, "Token 가져오기 성공");
        }else{
            return ResData.FAIL("Token 가져오기 실패");
        }
    }
}
