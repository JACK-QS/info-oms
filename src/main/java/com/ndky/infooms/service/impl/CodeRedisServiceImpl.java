package com.ndky.infooms.service.impl;

import com.ndky.infooms.common.utils.RedisUtils;
import com.ndky.infooms.service.CodeRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author chenqingsheng
 * @date 2021/1/23 17:12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuppressWarnings({"unchecked","all"})
public class CodeRedisServiceImpl implements CodeRedisService {

    private final RedisUtils redisUtils;

    @Value("${loginCode.expiration}")
    private Long expiration;

    /**
     * 查询验证码的值
     *
     * @param key 键
     * @return /
     */
    @Override
    public String getCodeVal(String key) {
        try {
            return Objects.requireNonNull(redisUtils.get(key)).toString();
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 保存验证码
     *
     * @param key 键
     * @param val 值
     */
    @Override
    public void saveCode(String key, Object val) {

        redisUtils.set(key,val,expiration, TimeUnit.MINUTES);
        /*redisTemplate.opsForValue().set(key,val);
        redisTemplate.expire(key,expiration, TimeUnit.MINUTES);*/

    }

    /**
     * delete
     *
     * @param key 键
     */
    @Override
    public void delete(String key) {
        redisUtils.remove(key);
    }
}
