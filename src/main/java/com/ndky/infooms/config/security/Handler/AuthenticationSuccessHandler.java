package com.ndky.infooms.config.security.Handler;

import com.alibaba.fastjson.JSON;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.common.base.Constants;
import com.ndky.infooms.common.utils.RequestUtils;
import com.ndky.infooms.common.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:31
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    //private final SysLogService sysLogService;


    private static final String LOGIN_SUCCESS = JSON.toJSONString(ApiResponse.success(Constants.LOGIN_SUCCESS));

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (RequestUtils.isAjax(request)) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
            String name = token.getName();
            //保存日志
            //sysLogService.saveLoginLog(request,Constants.LOGIN_SUCCESS,name);
            ResponseUtils.print(response, LOGIN_SUCCESS);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }


}
