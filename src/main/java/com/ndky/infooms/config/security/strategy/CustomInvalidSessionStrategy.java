package com.ndky.infooms.config.security.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:27
 * 自定义无效会话策略
 */
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/invalid_session").forward(request,response);
    }
}
