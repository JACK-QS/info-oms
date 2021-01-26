package com.ndky.infooms.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.entity.StuInfo;
import com.ndky.infooms.query.UserQuery;
import com.ndky.infooms.service.StuInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenqingsheng
 * @date 2021/1/25 17:06
 */
@RestController
@RequestMapping("/stuInfo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StuInfoRestController {

    private final StuInfoService stuInfoService;

    /**
     * 添加学生信息
     * @param stuInfo 很多值
     * @return ApiResponse
     */
    @PostMapping("/addStuInfo")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestBody StuInfo stuInfo){
        JSONObject jsonObject = new JSONObject();
        // 查询数据库中是否有一样学号的同学
        StuInfo stuInfo1 = stuInfoService.selectStuInfo(stuInfo.getStuNum());
        if (stuInfo1 == null){
            StuInfo stuInfo2 = new StuInfo();
            BeanUtils.copyProperties(stuInfo,stuInfo2);
            if(stuInfoService.insertStuInfo(stuInfo2) > 0){
                jsonObject.put("code", 200);
            }else {
                jsonObject.put("code",500);
            }
        }else {
            // 学生已存在
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }
}
