package com.todak.todakf.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // 로그인한 사용자 정보
    	CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", userDetails.getUser());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String targetUrl="";
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                targetUrl = "/admin/advertisement/list?adType=N"; // Admin's dashboard
                break;
            } else {
                targetUrl = "/index"; // User's profile page
                break;
            }
        }
        // 원하는 페이지로 리다이렉트
        response.sendRedirect(targetUrl);
    }
}

