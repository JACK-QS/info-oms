package com.ndky.infooms.controller.rest;

import cn.hutool.core.util.IdUtil;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.common.base.Constants;
import com.ndky.infooms.common.utils.RedisUtils;
import com.ndky.infooms.common.utils.SecurityUtils;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.service.CodeRedisService;
import com.ndky.infooms.service.SysUserService;
import com.ndky.infooms.vo.code.ImgResult;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author chenqingsheng
 * @date 2021/1/23 17:01
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysIndexRestController {

    private final CodeRedisService redisService;

    private final RedisUtils redisUtils;

    private final SysUserService sysUserService;

    /**
     * 验证码 宽度
     */
    @Value("${loginCode.width}")
    private Integer width;

    /**
     * 验证码 高度
     */
    @Value("${loginCode.height}")
    private Integer height;

    /**
     * 验证码 运算位数
     */
    @Value("${loginCode.digit}")
    private Integer digit;



    /**
     * 获取验证码
     */
    @GetMapping("/code")
    public ImgResult getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height,digit);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = IdUtil.simpleUUID();
        redisService.saveCode(uuid,result);
        return new ImgResult(captcha.toBase64(),uuid);
    }

    @PostMapping("/updatePassword")
    public ApiResponse updatePassword(@RequestParam("oldPass") String oldPass,
                                      @RequestParam("pass") String pass){
        //获取当前用户
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
        String usernameRedisKey = Constants.PASSWORD_UPDATE + username;
        // 校验用户是否被锁定
        if (redisUtils.exists(usernameRedisKey)) {
            if (redisUtils.sGetSetSize(usernameRedisKey) >= 6L){
                return ApiResponse.fail("旧密码错误次数太多了，请稍后重试");
            }
        }
        // 判断旧密码是否正确
        SysUser sysUser = sysUserService.findByName(username);
        if (sysUser != null){
            //if (!new BCryptPasswordEncoder().matches(oldPass,sysUser.getSysPassword())){
            if (oldPass.equals(sysUser.getSysPassword())){
                //更新密码
                //sysUserService.updatePasswordById(new BCryptPasswordEncoder().encode(pass), sysUser.getSysId());
                sysUserService.updatePasswordById(pass, sysUser.getSysId());
                if (redisUtils.exists(usernameRedisKey)) {
                    redisUtils.remove(usernameRedisKey);
                }
                return ApiResponse.success("更新成功");

            } else {
                redisUtils.sSetAndTime(usernameRedisKey,Constants.PASSWORD_UPDATE_MINUTE, new Date());
                return ApiResponse.fail("旧密码不匹配,还有"+ (6-redisUtils.sGetSetSize(usernameRedisKey)) +"次机会");

            }
        } else {
            return ApiResponse.fail("获取用户信息出错，请稍后重试");
        }
    }
}
