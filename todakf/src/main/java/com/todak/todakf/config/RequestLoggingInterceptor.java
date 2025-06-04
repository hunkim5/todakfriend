package com.todak.todakf.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.todak.todakf.common.mapper.CommonMapper;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
	@Autowired
	private CommonMapper commonMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURL().toString();
        String query = request.getQueryString();
        Map<String,String> param = new HashMap<>();
        param.put("url", url);
        param.put("param", query);
        param.put("ip", getClientIP(request));
        commonMapper.insertVisitReport(param);

        return true;
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        return xfHeader == null ? request.getRemoteAddr() : xfHeader.split(",")[0];
    }
}
