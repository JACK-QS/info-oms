package com.ndky.infooms.service;

/**
 * @author chenqingsheng
 * @date 2021/1/23 17:11
 */
public interface CodeRedisService {

    /**
     * 查询验证码的值
     * @param key 键
     * @return /
     */
    String getCodeVal(String key);

    /**
     * 保存验证码
     * @param key 键
     * @param val 值
     */
    void saveCode(String key, Object val);

    /**
     * delete
     * @param key 键
     */
    void delete(String key);

}
