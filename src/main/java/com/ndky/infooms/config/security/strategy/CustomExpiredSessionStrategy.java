package com.ndky.infooms.config.security.strategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:30
 * 自定义过期会话策略
 */
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        HttpServletRequest request = sessionInformationExpiredEvent.getRequest();
        request.getRequestDispatcher("/expired").forward(request,response);
    }
}