package com.tigerbk.project1.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigerbk.project1.exception.ErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *  
 * @Package     : com.withuslaw.common.config.security
 * @name        : JwtExceptionFilter.java
 * @date        : 2023/09/14 9:05 PM
 * @author      : tigerBK
 * @version     : 1.0.0
**/
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        try {
            chain.doFilter(req, res);
        } catch (JwtException ex) {
            log.debug("JwtExceptionFilter() > JwtException !");
            setErrorResponse(HttpStatus.UNAUTHORIZED, res, ex);
        } catch (ServletException e) {
            log.debug("JwtExceptionFilter() > ServletException !");
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, res, e);
        } catch (IOException e) {
            log.debug("JwtExceptionFilter() > IOException !");
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, res, e);
        } catch (Exception e) {
            log.debug("JwtExceptionFilter() > Exception !");
            e.printStackTrace();
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, res, e);
        }
    }
    public void setErrorResponse(HttpStatus status, HttpServletResponse res, Throwable ex) throws IOException {
        res.setStatus(status.value());
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write(new ObjectMapper().writeValueAsString(
                new ErrorResponse(String.valueOf(status.value()), ex.getMessage() , status)));
    }
}