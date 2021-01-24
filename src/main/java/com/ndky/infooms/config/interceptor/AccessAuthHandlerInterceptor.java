package com.ndky.infooms.config.interceptor;

import com.ndky.infooms.common.utils.SecurityUtils;
import com.ndky.infooms.entity.SysMenu;
import com.ndky.infooms.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenqingsheng
 * @date 2021/1/23 21:44
 * url访问权限验证拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessAuthHandlerInterceptor implements HandlerInterceptor {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final SysMenuService sysMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取二级菜单
        List<SysMenu> secondMenu = sysMenuService.getSecondMenu();

        // 获取菜单链接
        List<String> urlList = secondMenu.stream().map(o -> o.getMenuHref()).collect(Collectors.toList());

        // 拼接路径
        String servletPath = StringUtils.replace(request.getServletPath(), "/", "", 1);

        if (urlList.contains(servletPath)) {
            // 获取当前用户
            String username = (String) SecurityUtils.getCurrentUserAuthentication().getPrincipal();
            // 根据用户名获取菜单集合
            List<SysMenu> sysMenuList = sysMenuService.findMenuListByUser(username);
            // 如果在里面就返回true
            if (sysMenuList.stream().anyMatch(o -> servletPath.equals(o.getMenuHref()))) {
                return true;
            }
            response.sendRedirect(contextPath + "/403");
            return false;
        }
        return true;
    }

}
