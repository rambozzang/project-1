package com.tigerbk.project1.security;


import com.tigerbk.project1.biz.auth.UserAuthSvc;
import com.tigerbk.project1.enums.Role;
import com.tigerbk.project1.exception.DefaultException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserAuthSvc userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        log.debug("**** SECURITY FILTER > " + request.getRequestURL());
        try {
            
            String authorizationHeader = request.getHeader("Authorization");
            if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.split(" ")[1].trim();
                log.debug("**** SECURITY FILTER > Token: {} ", token);
                setAuthentication(token, request, response);
            }
            filterChain.doFilter(request, response);
        } catch (BadRequestException e) {
            log.debug("**** SECURITY FILTER > BadRequestException !!!");
            e.printStackTrace();
            String errMsg = "검증되지 않은 토큰입니다.";
            if (e.getMessage().equalsIgnoreCase("EXPIRED_ACCESS_TOKEN")) {
                errMsg = "만료된 토큰입니다!";
            } else if (e.getMessage().equalsIgnoreCase("CANNOT_FOUND_USER")) {
                errMsg = "사용자를 찾을 수 없습니다!";
            } else if (e.getMessage().equalsIgnoreCase("NOT_VALIDATE_TOKEN")) {
                errMsg = "인증되지 않는 토큰입니다!";
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            writeErrorLogs("BadRequestException",errMsg , response);
        } catch (NotFoundException e) {
            log.debug("**** SECURITY FILTER > NotFoundException !!!");
            e.printStackTrace();
            response.setStatus(HttpStatus.NOT_FOUND.value());
            writeErrorLogs("NOT_FOUND", e.getMessage(), response);
        } catch (Exception e) {
            log.debug("**** SECURITY FILTER > Exception !!!");
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            writeErrorLogs("Exception", e.getMessage(), response);
        }
    }

    private void setAuthentication(String token, HttpServletRequest request,  HttpServletResponse response) throws BadRequestException {

        if (!jwtUtil.validateToken(token)) {
            throw new BadRequestException("NOT_VALIDATE_TOKEN");
        }
        
        if (jwtUtil.isExpiration(token)) { // 만료되었는지 체크
            throw new BadRequestException("EXPIRED_ACCESS_TOKEN");
        }

        String custId = (String) jwtUtil.get(token).get("custId");

        // 회원권한 - 강제 셋팅
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(Role.USER.toString()));
        UsernamePasswordAuthenticationToken authentication ;

        authentication = new UsernamePasswordAuthenticationToken(
                userService.getUserInfo(custId), null,roles);
        
        log.debug(" ==================================================================================");
        log.debug(" == Security Success  ");
        log.debug(" ==================================================================================");
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void  writeErrorLogs(String exception, String message,  HttpServletResponse response ) {
        log.error("**** " + exception + " ****");
        log.error("**** error message : " + message);
        log.error("**** error message : " + response.getStatus());
        log.error("**** error message : " + response);
        if("BadRequestException".equals(exception)) {
            throw new DefaultException( message);
        } else if("Exception".equals(exception)){
            throw new DefaultException(message);
        } else if("NOT_FOUND".equals(exception)){
            throw new NotFoundException(message);
        }else {
            throw new DefaultException(message);
        }
    }


//    private void errorMsg(String errMsg, String msg, StackTraceElement[] traceMsg, HttpServletResponse response) {
//        writeErrorLogs(errMsg, msg, traceMsg);
//        response.setStatus(UNAUTHORIZED.value());
//        response.setContentType(APPLICATION_JSON_VALUE);
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(
//                    new ErrorResponse( HttpStatus.UNAUTHORIZED.value(), msg , HttpStatus.UNAUTHORIZED));
//            response.getWriter().write(json);
//            response.getWriter().flush();
//            response.getWriter().close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

}
