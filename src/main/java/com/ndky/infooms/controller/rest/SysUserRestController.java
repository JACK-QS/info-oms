package com.ndky.infooms.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.infooms.common.base.ApiResponse;
import com.ndky.infooms.entity.SysUser;
import com.ndky.infooms.entity.SysUserRole;
import com.ndky.infooms.query.UserQuery;
import com.ndky.infooms.service.SysRoleService;
import com.ndky.infooms.service.SysUserRoleService;
import com.ndky.infooms.service.SysUserService;
import com.ndky.infooms.vo.user.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenqingsheng
 * @date 2021/1/21 14:52
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserRestController {

    private final SysUserService userService;

    private final SysRoleService sysRoleService;

    private final SysUserRoleService sysUserRoleService;

    /**
     * 添加一个用户
     * @param userQuery 很多值
     * @return ApiResponse
     */
    @PostMapping("/addUser")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestBody UserQuery userQuery){
        System.out.println(userQuery);
        JSONObject jsonObject = new JSONObject();
        // 查看数据库中是否有一样名字的用户
        String sysName = userQuery.getSysName();
        SysUser user = userService.findByName(sysName);
        // 进行判断
        if(user == null){
            // 给用户角色
            SysUserRole sysUserRole = new SysUserRole();
            // 获取传入的角色名
            System.out.println(userQuery);
            // 通过传入的角色名获取角色id,set进入
            Long idByName = sysRoleService.getIdByName(userQuery.getUserRole());
            sysUserRole.setRoleId(idByName);
            // 把query对象与sysUser对象进行转换
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userQuery,sysUser);
            System.out.println(sysUser);
            if(userService.insert(sysUser) > 0){
                // 获取插入用户的id来set进角色表
                sysUserRole.setUserId(sysUser.getSysId());
                if(sysUserRoleService.insert(sysUserRole)>0){
                    jsonObject.put("code", 200);
                }
                else{
                    jsonObject.put("code",500);
                }
            }else {
                jsonObject.put("code",500);
            }
        }else {
            // 用户已存在
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 获取数据库中所有的角色
     * @return 所有角色类型
     */
    @GetMapping("/getAllRoleName")
    public ApiResponse getAllRoleName(){
        JSONObject jsonObject = new JSONObject();
        List<String> allRoleName = sysRoleService.getAllRoleName();
        jsonObject.put("allRoleName", allRoleName);
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 获取所有用户列表
     * @param page 页数
     * @param pageSize 条数
     * @return
     */
    @GetMapping("/getUserInfo")
    public ApiResponse getUserInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        List<UserVO> userList = new ArrayList<>(16);
        IPage<SysUser> sysUserList = userService.getAll(new Page(page, pageSize));
        if (sysUserList.getRecords() != null && sysUserList.getRecords().size() > 0){
            for (SysUser sysUser : sysUserList.getRecords()){
                //根据用户id查询角色名称
                String roleName = sysRoleService.getById(sysUser.getSysId());
                UserVO userVO = new UserVO(roleName);
                BeanUtils.copyProperties(sysUser,userVO);
                userList.add(userVO);
            }
        }
        jsonObject.put("total",sysUserList.getTotal());
        jsonObject.put("page",sysUserList.getCurrent());
        jsonObject.put("page_size",sysUserList.getSize());
        jsonObject.put("sysUserList",userList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 更新用户
     * @param userVO
     * @return
     */
    @PostMapping("/updateUser")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateRole(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userVO,sysUser);
        try {
            sysUserRoleService.deleteByUserId(userVO.getSysId());
            // 根据角色名查询角色id
            Long idByName = sysRoleService.getIdByName(userVO.getUserRole());
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userVO.getSysId());
            sysUserRole.setRoleId(idByName);
            sysUserRoleService.insert(sysUserRole);
            userService.updateById(sysUser);
            jsonObject.put("code",200);
        } catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 重置用户密码
     * @param sysId 用户id
     * @return
     */
    @GetMapping("/editPassword")
    public ApiResponse editPassword(Long sysId){
        JSONObject jsonObject = new JSONObject();
        try{
            if (userService.updatePasswordById("123456",sysId) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 删除用户
     * @param sysId 用户id
     * @return
     */
    @GetMapping("/deleteUser")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteUser(@RequestParam("sysId")Long sysId){
        JSONObject jsonObject = new JSONObject();
        try{
            sysUserRoleService.deleteByUserId(sysId);
            userService.deleteById(sysId);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    /**
     * 首页上面修改个人资料
     * @param userVO
     * @return
     */
    @PostMapping("/editUser")
    @ResponseBody
    public ApiResponse editUser(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userVO,sysUser);
        if (userService.updateById(sysUser)){
            jsonObject.put("code", 200);
            return ApiResponse.ofSuccess(jsonObject);
        } else {
            return ApiResponse.fail("更新基本资料失败");
        }
    }

}
